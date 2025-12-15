package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Platform;
import model.Student;
import model.Club;
import java.io.InputStream;
import java.net.URL;

public class MainController extends Application {
    
    private Stage primaryStage;
    private Scene scene;
    private Platform platform;
    private Student currentStudent;
    private Club currentClub;
    
    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        this.platform = Platform.getInstance();
        
        // DEBUG: Check resource paths
        System.out.println("=== RESOURCE DEBUG ===");
        debugResourcePaths();
        System.out.println("=== END DEBUG ===");
        
        // Initialize scene
        scene = new Scene(new javafx.scene.layout.StackPane(), 1000, 600);
        
        // Add CSS with multiple fallback attempts
        loadCSS();
        
        // Start with ChoiceUI
        showChoiceUI();
        
        stage.setScene(scene);
        stage.setTitle("Club Activities Platform");
        stage.show();
    }
    
    // ==================== NAVIGATION METHODS ====================
    
    public void showChoiceUI() {
        try {
            view.ChoiceUI choiceUI = new view.ChoiceUI();
            javafx.scene.Parent root = choiceUI.createContent(primaryStage, scene);
            scene.setRoot(root);
            setupChoiceUIController(choiceUI);
        } catch (Exception e) {
            showAlert("Error", "Could not load interface: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void showSignInUI() {
        try {
            view.SignInUI signInUI = new view.SignInUI();
            javafx.scene.Parent root = signInUI.createContent(primaryStage, scene);
            scene.setRoot(root);
            setupSignInUIController(signInUI);
        } catch (Exception e) {
            showAlert("Error", "Could not load sign-in: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void showSignUpUI() {
        try {
            view.SignUpUI signUpUI = new view.SignUpUI();
            javafx.scene.Parent root = signUpUI.createContent(primaryStage, scene);
            scene.setRoot(root);
            setupSignUpUIController(signUpUI);
        } catch (Exception e) {
            showAlert("Error", "Could not load sign-up: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void showAdminSignInUI() {
        try {
            view.AdminSignInUI adminUI = new view.AdminSignInUI();
            javafx.scene.Parent root = adminUI.createContent(primaryStage, scene);
            scene.setRoot(root);
            setupAdminSignInUIController(adminUI);
        } catch (Exception e) {
            showAlert("Error", "Could not load admin sign-in: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void showClubsUI() {
        try {
            view.ClubsUI clubsUI = new view.ClubsUI();
            javafx.scene.Parent root = clubsUI.createContent(primaryStage, scene);
            scene.setRoot(root);
            
            ClubsController clubsController = new ClubsController(this, platform, currentStudent);
            clubsController.setup(clubsUI);
        } catch (Exception e) {
            showAlert("Error", "Could not load clubs: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void showEventsUI(String clubName) {
        try {
            EventsController eventsController = new EventsController(this, platform, currentStudent);
            eventsController.showEventsUI(clubName, scene, primaryStage);
        } catch (Exception e) {
            showAlert("Error", "Could not load events: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void showAdminEventsUI() {
        try {
            AdminEventsController adminController = new AdminEventsController(this, platform, currentClub);
            adminController.showAdminDashboard(primaryStage);
        } catch (Exception e) {
            showAlert("Error", "Could not load admin dashboard: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // ==================== CONTROLLER SETUP METHODS ====================
    
    private void setupChoiceUIController(view.ChoiceUI choiceUI) {
        if (choiceUI.studentContainer != null) {
            choiceUI.studentContainer.setOnMouseClicked(e -> showSignInUI());
        }
        if (choiceUI.adminContainer != null) {
            choiceUI.adminContainer.setOnMouseClicked(e -> showAdminSignInUI());
        }
    }
    
    private void setupSignInUIController(view.SignInUI signInUI) {
        if (signInUI.signInButton != null) {
            signInUI.signInButton.setOnAction(e -> {
                String email = signInUI.emailField.getText();
                String password = signInUI.passwordField.getText();
                
                for (Student student : platform.getStudents()) {
                    if (student.getEmail().equalsIgnoreCase(email) && 
                        student.getPassword().equals(password)) {
                        currentStudent = student;
                        showClubsUI();
                        return;
                    }
                }
                showAlert("Error", "Invalid credentials");
            });
        }
        
        if (signInUI.signUpLink != null) {
            signInUI.signUpLink.setOnAction(e -> showSignUpUI());
        }
        
        if (signInUI.backButton != null) {
            signInUI.backButton.setOnAction(e -> showChoiceUI());
        }
    }
    
    private void setupSignUpUIController(view.SignUpUI signUpUI) {
        if (signUpUI.signUpButton != null) {
            signUpUI.signUpButton.setOnAction(e -> {
                Student newStudent = new Student(
                    signUpUI.fullNameField.getText(),
                    signUpUI.passwordField.getText(),
                    signUpUI.emailField.getText(),
                    signUpUI.universityField.getText(),
                    signUpUI.majorField.getText()
                );
                
                if (platform.addStudent(newStudent)) {
                    currentStudent = newStudent;
                    showAlert("Success", "Registration successful!");
                    showClubsUI();
                } else {
                    showAlert("Error", "Student already exists");
                }
            });
        }
        
        if (signUpUI.signInLink != null) {
            signUpUI.signInLink.setOnAction(e -> showSignInUI());
        }
    }
    
    private void setupAdminSignInUIController(view.AdminSignInUI adminUI) {
        if (adminUI.loginButton != null) {
            adminUI.loginButton.setOnAction(e -> {
                String email = adminUI.emailField.getText();
                String password = adminUI.passwordField.getText();
                
                for (Club club : platform.getClubs()) {
                    if (club.getEmail().equalsIgnoreCase(email) && 
                        club.getpassword().equals(password)) {
                        currentClub = club;
                        showAdminEventsUI();
                        return;
                    }
                }
                showAlert("Error", "Invalid club credentials");
            });
        }
        
        if (adminUI.backButton != null) {
            adminUI.backButton.setOnAction(e -> showChoiceUI());
        }
    }
    
    // ==================== UTILITY METHODS ====================
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    /**
     * Enhanced image loading with multiple fallback paths and debugging
     */
    public static javafx.scene.image.Image loadImage(String filename) {
        // Try multiple paths
        String[] pathsToTry = {
            "/assets/" + filename,
            "assets/" + filename,
            filename,
            "/" + filename
        };
        
        for (String path : pathsToTry) {
            try {
                InputStream stream = MainController.class.getResourceAsStream(path);
                if (stream != null) {
                    System.out.println("Image loaded from: " + path);
                    return new javafx.scene.image.Image(stream);
                }
            } catch (Exception e) {
                // Continue to next path
            }
        }
        
        // If all paths fail, check what resources are available
        System.err.println("Image not found: " + filename);
        debugAvailableResources();
        
        return null;
    }
    
    /**
     * Load CSS with multiple fallback attempts
     */
    private void loadCSS() {
        String[] cssPaths = {
            "/style.css",
            "style.css",
            "/view/style.css",
            "view/style.css"
        };
        
        for (String path : cssPaths) {
            try {
                URL cssUrl = getClass().getResource(path);
                if (cssUrl != null) {
                    scene.getStylesheets().add(cssUrl.toExternalForm());
                    System.out.println("CSS loaded from: " + path);
                    return;
                }
            } catch (Exception e) {
                // Continue to next path
            }
        }
        
        System.out.println("Warning: Could not load CSS from any path");
    }
    
    /**
     * Debug method to check resource paths
     */
    private void debugResourcePaths() {
        try {
            // Check classpath root
            URL rootUrl = getClass().getResource("/");
            System.out.println("Classpath root: " + rootUrl);
            
            // Check specific resources
            System.out.println("CSS file: " + getClass().getResource("/style.css"));
            System.out.println("Assets folder: " + getClass().getResource("/assets/"));
            
            // Try to list assets
            URL assetsUrl = getClass().getResource("/assets/");
            if (assetsUrl != null) {
                System.out.println("Assets URL protocol: " + assetsUrl.getProtocol());
            }
            
        } catch (Exception e) {
            System.out.println("Debug error: " + e.getMessage());
        }
    }
    
    /**
     * Debug method to list available resources
     */
    private static void debugAvailableResources() {
        try {
            System.out.println("=== AVAILABLE RESOURCES ===");
            URL root = MainController.class.getResource("/");
            if (root != null) {
                System.out.println("Root: " + root);
                
                // For file system resources
                if ("file".equals(root.getProtocol())) {
                    java.io.File rootFile = new java.io.File(root.toURI());
                    if (rootFile.exists()) {
                        listFiles(rootFile, "");
                    }
                }
            }
            System.out.println("=== END RESOURCE LIST ===");
        } catch (Exception e) {
            System.out.println("Could not list resources: " + e.getMessage());
        }
    }
    
    /**
     * Helper to list files in directory
     */
    private static void listFiles(java.io.File dir, String indent) {
        if (dir == null || !dir.exists()) return;
        
        java.io.File[] files = dir.listFiles();
        if (files != null) {
            for (java.io.File file : files) {
                System.out.println(indent + file.getName() + 
                    (file.isDirectory() ? "/" : ""));
                if (file.isDirectory()) {
                    listFiles(file, indent + "  ");
                }
            }
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
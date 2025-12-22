// File: SignInUI.java (Updated with safe image loading)
package view;

import controller.MainController;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

/**
 * SignInUI: A **pure View** class in the MVC architecture. 
 * Its sole responsibility is to **construct and display** the UI elements.
 * It contains NO business logic, data manipulation (Model access), or event handling logic (Controller actions).
 * The external Controller class must use the public fields below to bind user interactions.
 */
public class SignInUI {

    private static final double BASE_WIDTH = 1000;
    private static final double BASE_HEIGHT = 600;

    // --- 🌟 Public Elements for Controller Binding 🌟 ---
    public TextField emailField;
    public PasswordField passwordField;
    public Button signInButton;
    public Hyperlink signUpLink;
    public Button backButton;

    public Parent createContent(Stage stage, Scene scene) {

        // ---------- LEFT CONTENT (Form Construction) ----------
        VBox leftContent = new VBox(18);
        leftContent.setPadding(new Insets(100, 60, 40, 60));
        leftContent.setAlignment(Pos.TOP_CENTER);
        leftContent.setPrefWidth(550);

        Label signInLabel = new Label("Sign In");
        signInLabel.setFont(Font.font("Palatino Linotype", 46));
        signInLabel.setTextFill(Color.BLACK);
        signInLabel.setAlignment(Pos.CENTER_LEFT);
        signInLabel.setTranslateX(-30);
        signInLabel.setTranslateY(10);
        VBox.setMargin(signInLabel, new Insets(0, 0, 40, 0));

        // Email Field Setup
        Label emailLabel = new Label("Email:");
        emailLabel.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 15));
        emailLabel.setTextFill(Color.BLACK);
        emailLabel.setTranslateX(-195);
        VBox.setMargin(emailLabel, new Insets(0, 0, -10, 0));

        emailField = new TextField(); 
        emailField.setPromptText("Email");
        emailField.setPrefHeight(45);

        // Password Field Setup
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 15));
        passwordLabel.setTextFill(Color.BLACK);
        passwordLabel.setTranslateX(-180);
        VBox.setMargin(passwordLabel, new Insets(10, 0, -10, 0));

        passwordField = new PasswordField(); 
        passwordField.setPromptText("Password");
        passwordField.setPrefHeight(45);

        // Sign In Button Setup
        signInButton = new Button("Sign In"); 
        signInButton.setPrefHeight(50);
        signInButton.setPrefWidth(260);
        signInButton.setStyle("-fx-background-radius: 12;"
                + "-fx-background-color: linear-gradient(to right, #ff5fa2, #8a2be2);"
                + "-fx-text-fill: white; -fx-font-size: 16; -fx-font-family: 'Segoe UI';");

        // Sign Up link Setup
        Label signUpText = new Label("Don't have an account? ");
        
        signUpLink = new Hyperlink("Sign Up"); 
        signUpLink.setFont(Font.font("Segoe UI", 15));
        signUpLink.setTextFill(Color.web("#8a2be2"));

        HBox signUpBox = new HBox(signUpText, signUpLink);
        signUpBox.setAlignment(Pos.CENTER);
        signUpBox.setSpacing(5);

        leftContent.getChildren().addAll(
                signInLabel,
                emailLabel, emailField,
                passwordLabel, passwordField,
                signInButton,
                signUpBox
        );

        // ---------- RIGHT CONTENT (Aesthetic Panel) ----------
        VBox rightContent = new VBox(25);
        rightContent.setPadding(new Insets(90, 40, 40, 40));
        rightContent.setAlignment(Pos.TOP_LEFT);
        rightContent.setPrefWidth(450);

        Label welcomeTitle = new Label("  Welcome Back!");
        welcomeTitle.setTextFill(Color.WHITE);
        welcomeTitle.setFont(Font.font("Brush Script MT", 60));

        Label motivationText = new Label(
                "Stay connected with your clubs and never miss an event.\n"
                        + "Log in now to keep up with the latest activities and join the fun!"
        );
        motivationText.setTextFill(Color.WHITE);
        motivationText.setFont(Font.font("Segoe UI", 22));
        motivationText.setWrapText(true);
        motivationText.setMaxWidth(380);

        rightContent.getChildren().addAll(welcomeTitle, motivationText);

        // ---------- BACKGROUND and LAYOUT ----------
        HBox backgroundLayer = new HBox();
        Region whiteBackground = new Region();
        whiteBackground.setPrefWidth(550);
        whiteBackground.setStyle("-fx-background-color: white;");
        Region pinkBackground = new Region();
        pinkBackground.setPrefWidth(450);
        pinkBackground.setStyle("-fx-background-color: linear-gradient(to bottom, #e45ba8, #d94b94);");
        backgroundLayer.getChildren().addAll(whiteBackground, pinkBackground);

        HBox contentBox = new HBox(leftContent, rightContent);
        StackPane scaledRoot = new StackPane(backgroundLayer, contentBox);

        // Responsive Scaling
        Scale scale = new Scale();
        scale.xProperty().bind(Bindings.divide(scene.widthProperty(), BASE_WIDTH));
        scale.yProperty().bind(Bindings.divide(scene.heightProperty(), BASE_HEIGHT));
        scaledRoot.getTransforms().add(scale);

        // ---------- IMAGE SETUP (Safe Loading) ----------
        ImageView img = new ImageView();
        try {
            // Use MainController's robust image loader - pass just the filename
            javafx.scene.image.Image image = MainController.loadImage("Copilot_20251115_154313-removebg-preview.png");
            if (image != null) {
                img.setImage(image);
                img.setPreserveRatio(false);
                img.setSmooth(true);
                img.fitWidthProperty().bind(Bindings.min(scene.widthProperty().multiply(0.40), 600.0));
                img.fitHeightProperty().bind(Bindings.min(scene.heightProperty().multiply(0.45), 450.0));
                img.translateXProperty().bind(scene.widthProperty().multiply(0.20));
                img.translateYProperty().bind(scene.heightProperty().multiply(0.25));
                System.out.println("Image loaded successfully in SignInUI");
            } else {
                // Create a placeholder if image not found
                System.out.println("Image not found in SignInUI, using placeholder");
                img = createPlaceholderImageView(scene);
            }
        } catch (Exception e) {
            System.err.println("Error loading image in SignInUI: " + e.getMessage());
            img = createPlaceholderImageView(scene);
        }

        // ---------- BACK BUTTON Setup ----------
        backButton = new Button("←"); 
        backButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #ff5fa2, #8a2be2);" +
                "-fx-text-fill: white;" +
                "-fx-cursor: hand;" +
                "-fx-background-radius: 50;" +
                "-fx-alignment: center;"
        );
        backButton.prefWidthProperty().bind(scene.widthProperty().multiply(0.05));
        backButton.prefHeightProperty().bind(scene.widthProperty().multiply(0.05));

        backButton.styleProperty().bind(
                Bindings.concat(
                        "-fx-background-color: linear-gradient(to right, #ff5fa2, #8a2be2);",
                        "-fx-text-fill: white;",
                        "-fx-cursor: hand;",
                        "-fx-background-radius: 50;",
                        "-fx-alignment: center;",
                        "-fx-font-size: ",
                        backButton.prefWidthProperty().divide(2)
                )
        );

        backButton.setEffect(new javafx.scene.effect.DropShadow(5, Color.rgb(0, 0, 0, 0.3)));
        StackPane.setAlignment(backButton, Pos.TOP_LEFT);
        StackPane.setMargin(backButton, new Insets(20));

        return new StackPane(scaledRoot, img, backButton);
    }
    
    private ImageView createPlaceholderImageView(Scene scene) {
        // Create a simple placeholder with text
        VBox placeholderBox = new VBox(10);
        placeholderBox.setAlignment(Pos.CENTER);
        placeholderBox.setStyle("-fx-background-color: rgba(255,255,255,0.1); -fx-background-radius: 10;");
        placeholderBox.prefWidthProperty().bind(scene.widthProperty().multiply(0.40));
        placeholderBox.prefHeightProperty().bind(scene.heightProperty().multiply(0.45));
        
        Label placeholderText = new Label("Image Preview");
        placeholderText.setFont(Font.font("Segoe UI", 16));
        placeholderText.setTextFill(Color.GRAY);
        
        Label placeholderSubtext = new Label("Student graphic");
        placeholderSubtext.setFont(Font.font("Segoe UI", 12));
        placeholderSubtext.setTextFill(Color.LIGHTGRAY);
        
        placeholderBox.getChildren().addAll(placeholderText, placeholderSubtext);
        
        // Convert to ImageView
        javafx.scene.SnapshotParameters params = new javafx.scene.SnapshotParameters();
        params.setFill(javafx.scene.paint.Color.TRANSPARENT);
        javafx.scene.image.WritableImage image = placeholderBox.snapshot(params, null);
        ImageView imageView = new ImageView(image);
        
        // Bind positioning
        imageView.fitWidthProperty().bind(Bindings.min(scene.widthProperty().multiply(0.40), 600.0));
        imageView.fitHeightProperty().bind(Bindings.min(scene.heightProperty().multiply(0.45), 450.0));
        imageView.translateXProperty().bind(scene.widthProperty().multiply(0.20));
        imageView.translateYProperty().bind(scene.heightProperty().multiply(0.25));
        
        return imageView;
    }
}
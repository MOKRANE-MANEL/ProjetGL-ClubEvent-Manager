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
    // The Controller must access these fields to:
    // 1. Read user input (emailField, passwordField).
    // 2. Attach event handlers (e.g., signInButton.setOnAction, signUpLink.setOnAction, backButton.setOnAction).
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

        // Use public field for Controller access
        emailField = new TextField(); 
        emailField.setPromptText("Email");
        emailField.setPrefHeight(45);

        // Password Field Setup
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 15));
        passwordLabel.setTextFill(Color.BLACK);
        passwordLabel.setTranslateX(-180);
        VBox.setMargin(passwordLabel, new Insets(10, 0, -10, 0));

        // Use public field for Controller access
        passwordField = new PasswordField(); 
        passwordField.setPromptText("Password");
        passwordField.setPrefHeight(45);

        // Sign In Button Setup
        // Use public field for Controller access
        signInButton = new Button("Sign In"); 
        signInButton.setPrefHeight(50);
        signInButton.setPrefWidth(260);
        signInButton.setStyle("-fx-background-radius: 12;"
                + "-fx-background-color: linear-gradient(to right, #ff5fa2, #8a2be2);"
                + "-fx-text-fill: white; -fx-font-size: 16; -fx-font-family: 'Segoe UI';");

        // Sign Up link Setup
        Label signUpText = new Label("Don't have an account? ");
        
        // Use public field for Controller access
        signUpLink = new Hyperlink("Sign Up"); 
        signUpLink.setFont(Font.font("Segoe UI", 15));
        signUpLink.setTextFill(Color.web("#8a2be2"));

        HBox signUpBox = new HBox(signUpText, signUpLink);
        signUpBox.setAlignment(Pos.CENTER);
        signUpBox.setSpacing(5);

        // NOTE TO CONTROLLER: The navigation logic (e.g., scene.setRoot) 
        // for signUpLink must be defined in the Controller class.
        // signUpLink.setOnAction(e -> { ... }); // REMOVED for MVC compliance.

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

        // Image Setup
        ImageView img = new ImageView(new Image(getClass().getResourceAsStream(
                        "yyy.png")));
        img.setPreserveRatio(false);
        img.setSmooth(true);
        img.fitWidthProperty().bind(Bindings.min(scene.widthProperty().multiply(0.40), 600.0));
        img.fitHeightProperty().bind(Bindings.min(scene.heightProperty().multiply(0.45), 450.0));
        img.translateXProperty().bind(scene.widthProperty().multiply(0.20));
        img.translateYProperty().bind(scene.heightProperty().multiply(0.25));

        // ---------- BACK BUTTON Setup ----------
        // Use public field for Controller access
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

        StackPane.setAlignment(backButton, Pos.TOP_LEFT);
        StackPane.setMargin(backButton, new Insets(20));

        // NOTE TO CONTROLLER: The navigation logic for the back button 
        // (e.g., returning to ChoiceUI) must be set via backButton.setOnAction(...) 
        // in the Controller class.

        return new StackPane(scaledRoot, img, backButton);
    }
}

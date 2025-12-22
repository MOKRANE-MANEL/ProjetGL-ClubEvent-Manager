package view;
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
 * AdminSignInUI: A pure View class responsible ONLY for building and designing
 * the user interface (UI). It contains NO business logic and NO event handlers (Controller Logic).
 * The external Controller must use the public fields below to handle all user interactions.
 */
public class AdminSignInUI {

    private static final double BASE_WIDTH = 1000;
    private static final double BASE_HEIGHT = 600;

    // --- 🌟 Public Elements for Controller Binding 🌟 ---
    // The Controller must use these fields to:
    // 1. Get user input (emailField, passwordField).
    // 2. Set event handlers (loginButton.setOnAction, backButton.setOnAction).
    public TextField emailField;
    public PasswordField passwordField;
    public Button loginButton;
    public Button backButton;

    public Parent createContent(Stage stage, Scene scene) {

        // ---------- LEFT CONTENT (Form) ----------
        VBox leftContent = new VBox(18);
        leftContent.setPadding(new Insets(100, 60, 40, 60));
        leftContent.setAlignment(Pos.TOP_CENTER);
        leftContent.setPrefWidth(550);

        Label signInLabel = new Label("Club Admin Login");
        signInLabel.setFont(Font.font("Palatino Linotype", 46));
        signInLabel.setTextFill(Color.BLACK);
        signInLabel.setAlignment(Pos.CENTER_LEFT);
        signInLabel.setTranslateX(-30);
        signInLabel.setTranslateY(25);
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

        // Login Button Setup
        loginButton = new Button("Login"); 
        loginButton.setPrefHeight(50);
        loginButton.setPrefWidth(260);
        loginButton.setStyle("-fx-background-radius: 12;"
                + "-fx-background-color: linear-gradient(to right, #ff5fa2, #8a2be2);"
                + "-fx-text-fill: white; -fx-font-size: 16; -fx-font-family: 'Segoe UI';");
        VBox.setMargin(loginButton, new Insets(20, 0, 0, 0));

        leftContent.getChildren().addAll(
                signInLabel,
                emailLabel, emailField,
                passwordLabel, passwordField,
                loginButton
        );

        // ---------- RIGHT CONTENT (Information Panel) ----------
        VBox rightContent = new VBox(25);
        rightContent.setPadding(new Insets(90, 40, 40, 40));
        rightContent.setAlignment(Pos.TOP_LEFT);
        rightContent.setPrefWidth(450);

        Label welcomeTitle = new Label("Welcome Admin!");
        welcomeTitle.setTextFill(Color.WHITE);
        welcomeTitle.setFont(Font.font("Brush Script MT", 60));

        Label motivationText = new Label(
                "Manage your club activities easily.\n" +
                        "View past events, update details, or create new events."
        );
        motivationText.setTextFill(Color.WHITE);
        motivationText.setFont(Font.font("Segoe UI", 22));
        motivationText.setWrapText(true);
        motivationText.setMaxWidth(380);

        rightContent.getChildren().addAll(welcomeTitle, motivationText);

        // ---------- BACKGROUND AND LAYOUT ----------
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

        // Responsive Scaling Setup
        Scale scale = new Scale();
        scale.xProperty().bind(Bindings.divide(scene.widthProperty(), BASE_WIDTH));
        scale.yProperty().bind(Bindings.divide(scene.heightProperty(), BASE_HEIGHT));
        scaledRoot.getTransforms().add(scale);

        // Image Setup
        ImageView img = null;
        try {
            img = new ImageView(new Image(getClass().getResourceAsStream(
                            "aaa.png")));
            img.setPreserveRatio(true);
            img.setSmooth(true);
            img.fitWidthProperty().bind(
                             Bindings.min(scene.widthProperty().multiply(0.55), 850.0)
            );
            img.fitHeightProperty().bind(
                             Bindings.min(scene.heightProperty().multiply(0.55), 700.0)
            );
            img.translateXProperty().bind(scene.widthProperty().multiply(0.25));
            img.translateYProperty().bind(scene.heightProperty().multiply(0.28));
        } catch (Exception ex) {
            img = new ImageView();
        }

        // Back Button Setup
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

        backButton.setEffect(new javafx.scene.effect.DropShadow(10, Color.rgb(0,0,0,0.3)));
        StackPane.setAlignment(backButton, Pos.TOP_LEFT);
        StackPane.setMargin(backButton, new Insets(30, 20, 20, 20)); 

        // NOTE TO CONTROLLER: The setOnAction logic must be handled in the Controller class.
        // backButton.setOnAction(e -> { ... }); // REMOVED to maintain pure View separation.

        return new StackPane(scaledRoot, img, backButton);
    }
}

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
import javafx.stage.Stage;

/**
 * SignUpUI: A **pure View** class in the MVC architecture. 
 * Its sole responsibility is to construct and display the UI elements.
 * It contains NO application lifecycle logic, NO data manipulation, and NO event handling (Controller actions).
 * The external Controller class must use the public fields below to bind user interactions.
 */
public class SignUpUI {

    private static final double LEFT_RATIO = 0.45;
    private static final double RIGHT_RATIO = 1.0 - LEFT_RATIO;

    // --- 🌟 Public Elements for Controller Binding 🌟 ---
    // The Controller must access these public fields to:
    // 1. Read user input from the fields.
    // 2. Attach action handlers (e.g., signUpButton.setOnAction, signInLink.setOnAction).
    public TextField fullNameField;
    public TextField studentIdField;
    public TextField majorField;
    public TextField universityField;
    public ComboBox<String> yearCombo;
    public TextField emailField;
    public PasswordField passwordField;
    public Button signUpButton;
    public Hyperlink signInLink;

    public Pane createContent(Stage stage, Scene scene) {

        // ---------- LEFT CONTENT (Aesthetic Panel) ----------
        VBox leftContent = new VBox(25);
        leftContent.setPadding(new Insets(90, 40, 40, 40));
        leftContent.setAlignment(Pos.TOP_LEFT);
        leftContent.setMinWidth(320);

        Label title = new Label("Discover Club Activities");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("Brush Script MT", 47));
        title.setWrapText(true);

        Label subText = new Label(
            "Explore events from all clubs in one platform. " +
            "Stay updated with school activities and join university events easily."
        );
        subText.setTextFill(Color.WHITE);
        subText.setFont(Font.font("Segoe UI", 22));
        subText.setWrapText(true);

        leftContent.getChildren().addAll(title, subText);

        // ---------- RIGHT CONTENT (Form Construction) ----------
        VBox rightContent = new VBox(22);
        rightContent.setAlignment(Pos.TOP_CENTER);
        rightContent.setPadding(new Insets(120, 60, 40, 60));
        rightContent.setMinWidth(420);

        Label signUpLabel = new Label("Create Account");
        signUpLabel.setFont(Font.font("Palatino Linotype", 46));
        signUpLabel.setTextFill(Color.BLACK);
        signUpLabel.setAlignment(Pos.CENTER);
        VBox.setMargin(signUpLabel, new Insets(0, 0, 40, 0));

        // ---------- FIELDS (Bound to public fields for Controller access) ----------
        
        // Full Name
        Label fullNameLabel = new Label("Full Name:");
        fullNameField = new TextField(); // Bound to public field
        fullNameField.setPromptText("Full Name");
        fullNameField.setPrefHeight(45);
        fullNameField.getStyleClass().add("input-field");
        VBox fullNameBox = new VBox(5, fullNameLabel, fullNameField);

        // Student ID
        Label studentIdLabel = new Label("Student ID:");
        studentIdField = new TextField(); // Bound to public field
        studentIdField.setPromptText("12-digit ID");
        studentIdField.setPrefHeight(45);
        studentIdField.getStyleClass().add("input-field");
        VBox studentIdBox = new VBox(5, studentIdLabel, studentIdField);

        HBox nameIdBox = new HBox(20, fullNameBox, studentIdBox);
        nameIdBox.setAlignment(Pos.CENTER_LEFT);

        // Major
        Label majorLabel = new Label("Major:");
        majorField = new TextField(); // Bound to public field
        majorField.setPromptText("Specialization");
        majorField.setPrefHeight(45);
        majorField.getStyleClass().add("input-field");
        VBox majorBox = new VBox(5, majorLabel, majorField);

        // University
        Label universityLabel = new Label("University:");
        universityField = new TextField(); // Bound to public field
        universityField.setPromptText("University");
        universityField.setPrefHeight(45);
        universityField.getStyleClass().add("input-field");
        VBox universityBox = new VBox(5, universityLabel, universityField);

        // Year Combo Box
        Label yearLabel = new Label("Year:");
        yearCombo = new ComboBox<>(); // Bound to public field
        yearCombo.getItems().addAll("First", "Second", "Third", "Fourth", "Fifth");
        yearCombo.setPromptText("Year");
        yearCombo.setPrefHeight(45);
        yearCombo.getStyleClass().add("input-field");
        VBox yearBox = new VBox(5, yearLabel, yearCombo);

        HBox academicBox = new HBox(15, majorBox, universityBox, yearBox);
        academicBox.setAlignment(Pos.CENTER_LEFT);

        // Email
        Label emailLabel = new Label("Email:");
        emailField = new TextField(); // Bound to public field
        emailField.setPromptText("Email");
        emailField.setPrefHeight(45);
        emailField.getStyleClass().add("input-field");
        VBox emailBox = new VBox(5, emailLabel, emailField);

        // Password
        Label passwordLabel = new Label("Password:");
        passwordField = new PasswordField(); // Bound to public field
        passwordField.setPromptText("Password");
        passwordField.setPrefHeight(45);
        passwordField.getStyleClass().add("input-field");
        VBox passwordBox = new VBox(5, passwordLabel, passwordField);

        HBox emailPasswordBox = new HBox(20, emailBox, passwordBox);
        emailPasswordBox.setAlignment(Pos.CENTER_LEFT);

        // ---------- SIGN UP BUTTON ----------
        signUpButton = new Button("Sign Up"); // Bound to public field
        signUpButton.setStyle(
            "-fx-background-radius: 12;" +
            "-fx-background-color: linear-gradient(#ff5fa2, #8a2be2);" +
            "-fx-text-fill: white; -fx-font-family: 'Segoe UI';"
        );

        Label loginPrompt = new Label("Already have an account?");
        loginPrompt.setFont(Font.font("Segoe UI", 15));
        
        // Sign In Link (Hyperlink)
        signInLink = new Hyperlink("Sign In"); // Bound to public field
        signInLink.setFont(Font.font("Segoe UI", 15));
        signInLink.setTextFill(Color.web("#8a2be2"));

        HBox loginBox = new HBox(loginPrompt, new Label(" "), signInLink);
        loginBox.setAlignment(Pos.CENTER);
        loginBox.setSpacing(5);

        // NOTE TO CONTROLLER: The action handler for the Sign Up button (submitting the form) 
        // must be attached to 'signUpButton' in the Controller class.
        // NOTE TO CONTROLLER: The navigation logic for the Sign In link 
        // (moving to SignInUI) must be attached to 'signInLink' in the Controller class.

        rightContent.getChildren().addAll(
            signUpLabel,
            nameIdBox,
            academicBox,
            emailPasswordBox,
            signUpButton,
            loginBox
        );

        // ---------- BACKGROUNDS and LAYOUT (Aesthetic & Structure) ----------
        Region pinkBackground = new Region();
        pinkBackground.setStyle("-fx-background-color: linear-gradient(to bottom, #e45ba8, #d94b94);");

        Region whiteBackground = new Region();
        whiteBackground.setStyle("-fx-background-color: white;");

        HBox backgroundLayer = new HBox(pinkBackground, whiteBackground);

        HBox contentBox = new HBox(leftContent, rightContent);

        VBox rootBox = new VBox();
        rootBox.getChildren().add(contentBox);

        Region bottomSpacer = new Region();
        VBox.setVgrow(bottomSpacer, Priority.ALWAYS);
        rootBox.getChildren().add(bottomSpacer);

        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundLayer, rootBox);

        // ---------- IMAGE (Aesthetic) ----------
        ImageView img = new ImageView(new Image(getClass().getResourceAsStream(
            "/assets/ChatGPT_Image_15_nov._2025__13_37_42-removebg-preview.png"
        )));
        img.setPreserveRatio(true);
        img.setSmooth(true);
        img.setMouseTransparent(true);

        img.fitWidthProperty().bind(root.widthProperty().multiply(0.7));
        img.fitHeightProperty().bind(root.heightProperty().multiply(1.5));
        img.translateXProperty().bind(root.widthProperty().multiply(-0.20));
        img.translateYProperty().bind(root.heightProperty().multiply(0.05));
        StackPane.setAlignment(img, Pos.CENTER_RIGHT);

        root.getChildren().add(img);

        // ---------- BINDING (Responsive Design) ----------
        pinkBackground.prefWidthProperty().bind(root.widthProperty().multiply(LEFT_RATIO));
        whiteBackground.prefWidthProperty().bind(root.widthProperty().multiply(RIGHT_RATIO));
        pinkBackground.prefHeightProperty().bind(root.heightProperty());
        whiteBackground.prefHeightProperty().bind(root.heightProperty());

        leftContent.maxWidthProperty().bind(contentBox.widthProperty().multiply(LEFT_RATIO));
        rightContent.maxWidthProperty().bind(contentBox.widthProperty().multiply(RIGHT_RATIO));

        HBox.setHgrow(leftContent, Priority.ALWAYS);
        HBox.setHgrow(rightContent, Priority.ALWAYS);
        HBox.setHgrow(pinkBackground, Priority.ALWAYS);
        HBox.setHgrow(whiteBackground, Priority.ALWAYS);

        pinkBackground.setMinWidth(300);
        whiteBackground.setMinWidth(420);

        // NOTE TO CONTROLLER: Stylesheet loading is typically handled by the App entry point or Controller.
        // scene.getStylesheets().add("file:/C:/sign%20interface/src/style.css");

        DoubleProperty scaleFactor = new SimpleDoubleProperty(1.0);
        scaleFactor.bind(scene.widthProperty().divide(1000));

        // Binding Helpers (UI Utility methods - considered acceptable in the View)
        bindFontScaling(title, scaleFactor, 47);
        bindFontScaling(subText, scaleFactor, 22);
        bindFontScaling(signUpLabel, scaleFactor, 46);
        bindFontScaling(fullNameLabel, scaleFactor, 14);
        bindFontScaling(studentIdLabel, scaleFactor, 14);
        bindFontScaling(majorLabel, scaleFactor, 14);
        bindFontScaling(universityLabel, scaleFactor, 14);
        bindFontScaling(yearLabel, scaleFactor, 14);
        bindFontScaling(emailLabel, scaleFactor, 14);
        bindFontScaling(passwordLabel, scaleFactor, 14);
        bindFontScaling(loginPrompt, scaleFactor, 15);
        bindFontScaling(signInLink, scaleFactor, 15);

        bindFieldScaling(fullNameField, scaleFactor, 45, 14);
        bindFieldScaling(studentIdField, scaleFactor, 45, 14);

        majorField.prefHeightProperty().bind(scaleFactor.multiply(45));
        majorField.prefWidthProperty().bind(Bindings.createDoubleBinding(
            () -> Math.max(120, scene.getWidth() * 0.20), scene.widthProperty()
        ));
        majorField.fontProperty().bind(Bindings.createObjectBinding(
            () -> Font.font("Segoe UI", 14 * scaleFactor.get()), scaleFactor
        ));

        universityField.prefHeightProperty().bind(scaleFactor.multiply(45));
        universityField.prefWidthProperty().bind(Bindings.createDoubleBinding(
            () -> Math.max(120, scene.getWidth() * 0.20), scene.widthProperty()
        ));
        universityField.fontProperty().bind(Bindings.createObjectBinding(
            () -> Font.font("Segoe UI", 14 * scaleFactor.get()), scaleFactor
        ));

        yearCombo.prefHeightProperty().bind(scaleFactor.multiply(45));
        yearCombo.prefWidthProperty().bind(Bindings.createDoubleBinding(
            () -> Math.max(90, scene.getWidth() * 0.15), scene.widthProperty()
        ));
        yearCombo.styleProperty().bind(Bindings.concat("-fx-font-size: ", scaleFactor.multiply(12)));

        bindFieldScaling(emailField, scaleFactor, 45, 14);
        bindFieldScaling(passwordField, scaleFactor, 45, 14);
        bindButtonScaling(signUpButton, scaleFactor, 50, 16);

        bindVBoxSpacing(leftContent, scaleFactor, 25);
        bindVBoxSpacing(rightContent, scaleFactor, 22);
        bindHBoxSpacing(nameIdBox, scaleFactor, 20);
        bindHBoxSpacing(academicBox, scaleFactor, 15);
        bindHBoxSpacing(emailPasswordBox, scaleFactor, 20);
        bindHBoxSpacing(loginBox, scaleFactor, 5);

        bindPadding(leftContent, scaleFactor, new Insets(90, 40, 40, 40));
        bindPadding(rightContent, scaleFactor, new Insets(120, 60, 40, 60));
        VBox.setMargin(signUpLabel, scaledInsetsBinding(scaleFactor, new Insets(0, 0, 40, 0)));

        return root;
    }

    // =========================
    // Helpers: scaling bindings (UI Utility methods - kept as part of View)
    // =========================
    private void bindFontScaling(Labeled labeled, DoubleProperty scale, double baseSize) {
        Font base = labeled.getFont() != null ? labeled.getFont() : Font.getDefault();
        String family = base.getFamily();
        labeled.fontProperty().bind(Bindings.createObjectBinding(
            () -> Font.font(family, baseSize * scale.get()),
            scale
        ));
    }

    private void bindFontScaling(Hyperlink link, DoubleProperty scale, double baseSize) {
        Font base = link.getFont() != null ? link.getFont() : Font.getDefault();
        String family = base.getFamily();
        link.fontProperty().bind(Bindings.createObjectBinding(
            () -> Font.font(family, baseSize * scale.get()),
            scale
        ));
    }

    private void bindFieldScaling(TextInputControl field, DoubleProperty scale, double baseHeight, double baseFont) {
        field.prefHeightProperty().bind(scale.multiply(baseHeight));
        field.prefWidthProperty().bind(scale.multiply(220));
        Font base = field.getFont() != null ? field.getFont() : Font.getDefault();
        String family = base.getFamily();
        field.fontProperty().bind(Bindings.createObjectBinding(
            () -> Font.font(family, baseFont * scale.get()),
            scale
        ));
    }

    private void bindButtonScaling(Button btn, DoubleProperty scale, double baseHeight, double baseFont) {
        btn.prefHeightProperty().bind(scale.multiply(baseHeight));
        btn.prefWidthProperty().bind(scale.multiply(260));
        Font base = btn.getFont() != null ? btn.getFont() : Font.getDefault();
        String family = base.getFamily();
        btn.fontProperty().bind(Bindings.createObjectBinding(
            () -> Font.font(family, baseFont * scale.get()),
            scale
        ));
    }

    private void bindVBoxSpacing(VBox box, DoubleProperty scale, double baseSpacing) {
        box.spacingProperty().bind(scale.multiply(baseSpacing));
    }

    private void bindHBoxSpacing(HBox box, DoubleProperty scale, double baseSpacing) {
        box.spacingProperty().bind(scale.multiply(baseSpacing));
    }

    private void bindPadding(Region region, DoubleProperty scale, Insets baseInsets) {
        region.paddingProperty().bind(Bindings.createObjectBinding(
            () -> new Insets(
                baseInsets.getTop() * scale.get(),
                baseInsets.getRight() * scale.get(),
                baseInsets.getBottom() * scale.get(),
                baseInsets.getLeft() * scale.get()
            ),
            scale
        ));
    }

    private Insets scaledInsetsBinding(DoubleProperty scale, Insets base) {
        return new Insets(
            base.getTop() * scale.get(),
            base.getRight() * scale.get(),
            base.getBottom() * scale.get(),
            base.getLeft() * scale.get()
        );
    }
}

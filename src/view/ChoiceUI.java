package view;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;

public class ChoiceUI {

    private static final double BASE_WIDTH = 1000;
    private static final double BASE_HEIGHT = 600;

    public StackPane studentContainer;
    public StackPane adminContainer;
    public Polygon adminClip;
    public Polygon studentClip;
    public StackPane root;

    public Parent createContent(Stage stage, Scene scene) {

        root = new StackPane();

        Pane backgroundLayer = new Pane();

        Region adminBg = new Region();
        adminBg.setStyle("-fx-background-color: linear-gradient(to top left, #d94b94, #e45ba8);");

        Region studentBg = new Region();
        studentBg.setStyle("-fx-background-color: linear-gradient(to bottom right, #8a2be2, #ff5fa2);");

        adminBg.prefWidthProperty().bind(scene.widthProperty());
        adminBg.prefHeightProperty().bind(scene.heightProperty());
        studentBg.prefWidthProperty().bind(scene.widthProperty());
        studentBg.prefHeightProperty().bind(scene.heightProperty());

        adminClip = new Polygon(0, 0, BASE_WIDTH, 0, 0, BASE_HEIGHT);
        studentClip = new Polygon(BASE_WIDTH, 0, BASE_WIDTH, BASE_HEIGHT, 0, BASE_HEIGHT);

        adminBg.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
            adminClip.getPoints().setAll(
                    0.0, 0.0,
                    newVal.getWidth(), 0.0,
                    0.0, newVal.getHeight()
            );
        });

        studentBg.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
            studentClip.getPoints().setAll(
                    newVal.getWidth(), 0.0,
                    newVal.getWidth(), newVal.getHeight(),
                    0.0, newVal.getHeight()
            );
        });

        adminBg.setClip(adminClip);
        studentBg.setClip(studentClip);

        backgroundLayer.getChildren().addAll(adminBg, studentBg);

        HBox contentLayer = new HBox();
        contentLayer.prefWidthProperty().bind(scene.widthProperty());
        contentLayer.prefHeightProperty().bind(scene.heightProperty());

        VBox studentContent = new VBox(20);
        studentContent.setAlignment(Pos.CENTER_LEFT);

        ImageView studentImg = new ImageView(new Image(getClass().getResourceAsStream(
                "/assets/Copilot_20251115_154313-removebg-preview.png")));
        studentImg.setPreserveRatio(true);

        studentImg.fitWidthProperty().bind(scene.widthProperty().multiply(0.45));
        studentImg.fitHeightProperty().bind(scene.heightProperty().multiply(0.50));
        VBox.setMargin(studentImg, new Insets(50, 0, 0, 40));

        Label studentLabel = new Label("As Student");
        studentLabel.setTextFill(Color.WHITE);
        studentLabel.setFont(Font.font("Brush Script MT", 60));
        studentLabel.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", scene.widthProperty().multiply(0.06), ";")
        );
        studentLabel.setTranslateX(78);

        studentContent.getChildren().addAll(studentImg, studentLabel);

        studentContainer = new StackPane(studentContent);
        studentContainer.prefWidthProperty().bind(scene.widthProperty().divide(2));
        studentContainer.prefHeightProperty().bind(scene.heightProperty());
        studentContainer.setAlignment(Pos.CENTER_LEFT);

        VBox adminContent = new VBox(20);
        adminContent.setAlignment(Pos.CENTER_LEFT);

        ImageView adminImg = new ImageView(new Image(getClass().getResourceAsStream(
                "/assets/ChatGPT Image 17 nov. 2025, 17_57_43.png")));
        adminImg.setPreserveRatio(true);

        adminImg.fitWidthProperty().bind(scene.widthProperty().multiply(0.36));
        adminImg.fitHeightProperty().bind(scene.heightProperty().multiply(0.60));
        VBox.setMargin(adminImg, new Insets(-150, 0, 0, 40));

        Label adminLabel = new Label("As Club");
        adminLabel.setTextFill(Color.WHITE);
        adminLabel.setFont(Font.font("Brush Script MT", 60));
        adminLabel.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", scene.widthProperty().multiply(0.06), ";")
        );
        adminLabel.setTranslateX(80);
        VBox.setMargin(adminLabel, new Insets(-60, 0, 0, 0));

        adminContent.getChildren().addAll(adminImg, adminLabel);

        adminContainer = new StackPane(adminContent);
        adminContainer.prefWidthProperty().bind(scene.widthProperty().divide(2));
        adminContainer.prefHeightProperty().bind(scene.heightProperty());
        adminContainer.setAlignment(Pos.CENTER_LEFT);

        contentLayer.getChildren().addAll(adminContainer, studentContainer);

        root.getChildren().addAll(backgroundLayer, contentLayer);

        return root;
    }
}

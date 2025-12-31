package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class EventsUI {

    public StackPane backButtonContainer;
    public Map<Button, String> joinButtonsMap = new HashMap<>();

    public Pane createContent(Stage stage, Scene scene, String clubName, List<Map<String, String>> eventsData) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: white;");

        backButtonContainer = createBackButtonUI(scene);

        Label title = new Label("Events of " + clubName);
        title.setFont(Font.font("Brush Script MT", 36));
        title.setTextFill(Color.WHITE);

        BorderPane header = new BorderPane();
        header.setLeft(backButtonContainer);
        header.setCenter(title);
        header.setPadding(new Insets(20));
        header.setBackground(new Background(
                new BackgroundFill(
                        Color.web("#ff5fa2"),
                        new CornerRadii(0, 0, 50, 50, false),
                        Insets.EMPTY
                )
        ));
        root.setTop(header);

        if (eventsData == null || eventsData.isEmpty()) {
            Label noEventsLabel = new Label("Unfortunately, no events are available right now 😢");
            noEventsLabel.setFont(Font.font("Segoe UI", 20));
            noEventsLabel.setTextFill(Color.GRAY);

            VBox emptyBox = new VBox(noEventsLabel);
            emptyBox.setAlignment(Pos.CENTER);
            root.setCenter(emptyBox);
        } else {
            GridPane grid = new GridPane();
            grid.setHgap(30);
            grid.setVgap(30);
            grid.setPadding(new Insets(40));
            grid.setAlignment(Pos.TOP_CENTER);

            int col = 0;
            int row = 0;
            for (Map<String, String> ev : eventsData) {
                VBox card = createEventCard(ev, scene, this.joinButtonsMap);
                grid.add(card, col, row);
                col++;
                if (col > 1) {
                    col = 0;
                    row++;
                }
            }

            ColumnConstraints colConstraint = new ColumnConstraints();
            colConstraint.setPercentWidth(50);
            grid.getColumnConstraints().addAll(colConstraint, colConstraint);

            ScrollPane scrollPane = new ScrollPane(grid);
            scrollPane.setFitToWidth(true);
            scrollPane.setStyle("-fx-background-color: transparent;");
            root.setCenter(scrollPane);
        }

        return root;
    }

    private StackPane createBackButtonUI(Scene scene) {
        Circle backCircle = new Circle(20, Color.WHITE);
        Label backLabel = new Label("←");
        backLabel.setFont(Font.font("Segoe UI", 18));
        backLabel.setTextFill(Color.web("#ff5fa2"));

        StackPane backButton = new StackPane(backCircle, backLabel);

        backButton.setOnMouseEntered(e -> {
            backCircle.setFill(Color.web("#ffe9f2"));
        });
        backButton.setOnMouseExited(e -> {
            backCircle.setFill(Color.WHITE);
        });

        StackPane.setMargin(backLabel, new Insets(0, 0, 0, 0));
        return backButton;
    }

    private VBox createEventCard(Map<String, String> ev, Scene scene, Map<Button, String> buttonRegistry) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(20));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setBackground(
                new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), Insets.EMPTY))
        );
        card.setBorder(
                new Border(new BorderStroke(
                        Color.LIGHTGRAY,
                        BorderStrokeStyle.SOLID,
                        new CornerRadii(10),
                        BorderWidths.DEFAULT
                ))
        );

        Label name = new Label(ev.get("name"));
        name.setFont(Font.font("Segoe UI", 18));
        name.setTextFill(Color.web("#ff5fa2"));

        Button join = new Button("Join Event");
        join.setStyle(
                "-fx-background-color: linear-gradient(to right,#ff9acb,#ff5fa2);"
              + "-fx-text-fill:white; -fx-font-weight:bold; -fx-background-radius:10;"
        );
        join.setPrefWidth(120);

        buttonRegistry.put(join, ev.get("id"));

        card.getChildren().addAll(
                name,
                new Label("Type: " + ev.get("type")),
                new Label(ev.getOrDefault("description", "")),
                new Label("📅 " + ev.get("date")),
                new Label("📍 " + ev.get("location")),
                join
        );

        if (scene != null) {
            card.prefWidthProperty().bind(scene.widthProperty().divide(2).subtract(60));
        } else {
            card.setPrefWidth(350);
        }

        return card;
    }
}

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
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ClubsUI {

    public Map<Button, String> eventsButtonsMap = new HashMap<>();

    public Pane createContent(Stage stage, Scene scene) {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: white;");

        Circle leftCircle = new Circle(120, Color.web("#ffe0ef"));
        leftCircle.setOpacity(0.4);
        StackPane.setAlignment(leftCircle, Pos.CENTER_LEFT);

        Circle rightCircle = new Circle(100, Color.web("#e0d4ff"));
        rightCircle.setOpacity(0.4);
        StackPane.setAlignment(rightCircle, Pos.CENTER_RIGHT);

        BorderPane content = new BorderPane();

        Label title = new Label("Clubs & Events");
        title.setFont(Font.font("Brush Script MT", 42));
        title.setTextFill(Color.WHITE);

        HBox header = new HBox(title);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(20));
        header.setBackground(new Background(
                new BackgroundFill(
                        Color.web("#ff5fa2"),
                        new CornerRadii(0, 0, 50, 50, false),
                        Insets.EMPTY
                )
        ));
        content.setTop(header);

        GridPane grid = new GridPane();
        grid.setHgap(30);
        grid.setVgap(30);
        grid.setPadding(new Insets(40));
        grid.setAlignment(Pos.TOP_CENTER);

        List<Map<String, String>> clubs = new ArrayList<>();
        clubs.add(Map.of("id","1","name","🎨 Arts Club","description","Creative students who love painting.","members","25","events","2"));
        clubs.add(Map.of("id","2","name","📸 Photography Club","description","Learn professional photography.","members","40","events","3"));
        clubs.add(Map.of("id","3","name","🏀 Sports Club","description","Friendly matches and tournaments.","members","60","events","1"));
        clubs.add(Map.of("id","4","name","💻 Coding Club","description","Develop new apps and competitive programming.","members","80","events","4"));
        clubs.add(Map.of("id","5","name","📚 Reading Club","description","Discuss books and literary works.","members","35","events","2"));

        int col = 0;
        int row = 0;
        for (Map<String, String> club : clubs) {
            VBox card = createClubCard(club, scene, this.eventsButtonsMap);
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
        content.setCenter(scrollPane);

        root.getChildren().addAll(leftCircle, rightCircle, content);
        return root;
    }

    private VBox createClubCard(Map<String, String> club, Scene scene, Map<Button, String> buttonRegistry) {
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

        Label title = new Label(club.get("name"));
        title.setFont(Font.font("Segoe UI", 20));
        title.setTextFill(Color.web("#ff5fa2"));

        Label description = new Label(club.get("description"));
        description.setFont(Font.font("Segoe UI", 14));
        description.setTextFill(Color.DIMGRAY);
        description.setWrapText(true);

        Label idLabel = new Label("🆔 ID: " + club.get("id"));
        Label membersLabel = new Label("👥 Members: " + club.get("members"));
        Label eventsCountLabel = new Label("📅 Events: " + club.get("events"));

        Button eventsButton = new Button("Club Events");
        eventsButton.setStyle(
                "-fx-background-color: linear-gradient(to right,#ff9acb,#ff5fa2);" +
                "-fx-text-fill:white;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:10;"
        );
        eventsButton.setPrefWidth(150);

        buttonRegistry.put(eventsButton, club.get("name"));

        card.getChildren().addAll(
                title,
                description,
                idLabel,
                membersLabel,
                eventsCountLabel,
                eventsButton
        );

        if (scene != null) {
            card.prefWidthProperty().bind(scene.widthProperty().divide(2).subtract(60));
        } else {
            card.setPrefWidth(350);
        }

        return card;
    }
}

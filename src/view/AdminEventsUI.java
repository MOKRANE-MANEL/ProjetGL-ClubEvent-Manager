package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.List;
import java.util.Map;

public class AdminEventsUI {

    public Button userIconBtn;
    public Button addMemberBtn;
    public Button deleteMemberBtn;
    public Button verifyMemberBtn;
    public Button verifyEventBtn;
    public Button addEventBtn;

    public VBox membersListBox;
    public Label membersCountLabel;
    public GridPane eventsGrid;
    public StackPane rootStack;
    public VBox sidePanel;
    public HBox header;

    public BorderPane createContent(Stage stage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f8f7f7ff;");

        Circle leftCircle = new Circle(120, Color.web("#ffe0ef"));
        leftCircle.setOpacity(0.4);
        StackPane.setAlignment(leftCircle, Pos.CENTER_LEFT);

        Circle rightCircle = new Circle(100, Color.web("#e0d4ff"));
        rightCircle.setOpacity(0.4);
        StackPane.setAlignment(rightCircle, Pos.CENTER_RIGHT);

        StackPane backgroundLayer = new StackPane(leftCircle, rightCircle);
        BorderPane content = new BorderPane();

        Label headerTitle = new Label("Your Club’s Events");
        headerTitle.setFont(Font.font("Brush Script MT", 42));
        headerTitle.setTextFill(Color.WHITE);

        userIconBtn = new Button("👤");
        userIconBtn.setStyle("-fx-background-color: transparent; -fx-font-size: 22px;");

        header = new HBox();
        header.setPadding(new Insets(20));
        header.setBackground(new Background(
                new BackgroundFill(Color.web("#ff5fa2"),
                        new CornerRadii(0, 0, 50, 50, false), Insets.EMPTY)
        ));

        Region spacerLeft = new Region();
        Region spacerRight = new Region();
        HBox.setHgrow(spacerLeft, Priority.ALWAYS);
        HBox.setHgrow(spacerRight, Priority.ALWAYS);

        header.getChildren().addAll(userIconBtn, spacerLeft, headerTitle, spacerRight);
        header.setAlignment(Pos.CENTER);
        content.setTop(header);

        eventsGrid = new GridPane();
        eventsGrid.setHgap(30);
        eventsGrid.setVgap(30);
        eventsGrid.setPadding(new Insets(40));
        eventsGrid.setAlignment(Pos.TOP_CENTER);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        col1.setHgrow(Priority.ALWAYS);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        col2.setHgrow(Priority.ALWAYS);

        eventsGrid.getColumnConstraints().addAll(col1, col2);

        ScrollPane scrollPane = new ScrollPane(eventsGrid);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");
        content.setCenter(scrollPane);

        sidePanel = new VBox(15);
        sidePanel.setPadding(new Insets(20));
        sidePanel.setMinWidth(200);
        sidePanel.setPrefWidth(250);
        sidePanel.setMaxWidth(300);
        sidePanel.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #f8f7f7ff, #cf9ecaff);" +
                "-fx-border-color: #f8f7f7ff;" +
                "-fx-border-radius: 12;" +
                "-fx-background-radius: 12;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 8,0,0,2);"
        );

        membersListBox = new VBox(10);

        membersCountLabel = new Label("Club Members: 0");
        membersCountLabel.setFont(Font.font("Segoe UI Semibold", 18));
        membersCountLabel.setTextFill(Color.web("#333333"));

        addMemberBtn = new Button("➕ Add Member");
        addMemberBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 8;");

        deleteMemberBtn = new Button("➖ Delete Member");
        deleteMemberBtn.setStyle("-fx-background-color: #ff4d4d; -fx-text-fill: white; -fx-background-radius: 8;");

        verifyMemberBtn = new Button("✔️ Verify Member");
        verifyMemberBtn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-background-radius: 8;");

        verifyEventBtn = new Button("✔️ Verify Event");
        verifyEventBtn.setStyle("-fx-background-color: #9C27B0; -fx-text-fill: white; -fx-background-radius: 8;");

        ScrollPane membersScroll = new ScrollPane(membersListBox);
        membersScroll.setFitToWidth(true);
        membersScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        membersScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        sidePanel.getChildren().addAll(
                membersCountLabel,
                addMemberBtn,
                deleteMemberBtn,
                verifyMemberBtn,
                verifyEventBtn,
                membersScroll
        );

        addEventBtn = new Button("+ New Event");
        addEventBtn.setStyle(
                "-fx-background-color: linear-gradient(to right, #8a2be2, #ff5fa2);" +
                "-fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20;"
        );
        addEventBtn.setPrefWidth(160);
        addEventBtn.setPrefHeight(40);

        rootStack = new StackPane(backgroundLayer, content);
        root.setCenter(rootStack);

        rootStack.getChildren().add(addEventBtn);
        StackPane.setAlignment(addEventBtn, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(addEventBtn, new Insets(20));

        StackPane.setAlignment(sidePanel, Pos.TOP_LEFT);
        header.heightProperty().addListener((obs, oldH, newH) -> {
            StackPane.setMargin(sidePanel, new Insets(newH.doubleValue(), 0, 0, 0));
        });

        return root;
    }

    public HBox createMemberLabel(String name, Button clickTarget) {
        Circle circle = new Circle(8, Color.web("#ff5fa2"));
        Label label = new Label(name);
        label.setFont(Font.font(16));
        HBox hBox = new HBox(10, circle, label);
        hBox.setAlignment(Pos.CENTER_LEFT);
        return hBox;
    }

    public VBox createEventCard(Map<String, String> event, List<Button> actionButtons) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(20));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), Insets.EMPTY)));
        card.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID,
                new CornerRadii(10), BorderWidths.DEFAULT)));

        Label title = new Label(event.getOrDefault("name", "Event Title"));
        title.setFont(Font.font("Segoe UI", 20));
        title.setTextFill(Color.web("#ff5fa2"));

        Label typeLabel = new Label("Type: " + event.getOrDefault("type", "Type"));
        typeLabel.setFont(Font.font("Segoe UI", 14));
        typeLabel.setTextFill(Color.DARKMAGENTA);

        Label description = new Label(event.getOrDefault("description", "Description..."));
        description.setFont(Font.font("Segoe UI", 14));
        description.setTextFill(Color.DIMGRAY);
        description.setWrapText(true);

        Label timeLabelDisplay = new Label("🕒 " + event.getOrDefault("time", "Time"));
        timeLabelDisplay.setFont(Font.font("Segoe UI", 13));
        timeLabelDisplay.setTextFill(Color.GRAY);

        Label locationLabelDisplay = new Label("📍 " + event.getOrDefault("location", "Location"));
        locationLabelDisplay.setFont(Font.font("Segoe UI", 13));
        locationLabelDisplay.setTextFill(Color.BLUE);

        HBox adminButtons = new HBox(10);
        adminButtons.setAlignment(Pos.CENTER_LEFT);

        if (actionButtons != null) {
            adminButtons.getChildren().addAll(actionButtons);
        }

        card.getChildren().addAll(
                title,
                typeLabel,
                description,
                timeLabelDisplay,
                locationLabelDisplay,
                adminButtons
        );

        card.setPrefWidth(400);
        return card;
    }

    public Stage createGenericDialogStage(String titleText, String fieldLabel,
                                          Button confirmBtn, Button cancelBtn, TextField idField) {
        Stage dialogStage = new Stage(StageStyle.UNDECORATED);

        Label header = new Label("✦ " + titleText + " ✦");
        header.setFont(Font.font("Segoe Script", 24));
        header.setTextFill(Color.web("#ff5fa2"));
        header.setAlignment(Pos.CENTER);
        header.setMaxWidth(Double.MAX_VALUE);

        idField.setPromptText(fieldLabel);

        confirmBtn.setStyle("-fx-font-size: 16; -fx-padding: 10 20; -fx-background-radius: 12; -fx-background-color:#4CAF50; -fx-text-fill:white;");
        cancelBtn.setStyle("-fx-font-size: 16; -fx-padding: 10 20; -fx-background-radius: 12; -fx-background-color:#f44336; -fx-text-fill:white;");

        HBox buttons = new HBox(15, confirmBtn, cancelBtn);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(10));

        VBox form = new VBox(10,
                header,
                new Label(fieldLabel),
                idField,
                buttons
        );

        form.setPadding(new Insets(20));
        form.setAlignment(Pos.CENTER_LEFT);
        form.setStyle("-fx-background-color: #ffe6f0; -fx-background-radius: 20;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 12,0,0,3);");

        dialogStage.setScene(new Scene(form, 400, 250));
        return dialogStage;
    }
}

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

// This class represents the pure VIEW.
// Its sole function is to build and style the UI (View-Factory pattern).
// All interactive elements and methods are PUBLIC for the Controller to access and bind logic.

public class AdminEventsUI {

    // 🌟 Public Interactive Elements and Containers for Controller Binding 🌟
    // CONTROLLER: These public fields must be used to set event handlers (e.g., setOnAction).
    public Button userIconBtn;
    public Button addMemberBtn;
    public Button deleteMemberBtn;
    public Button verifyMemberBtn;
    public Button verifyEventBtn;
    public Button addEventBtn;

    // 🌟 Public Data Display Containers/Labels 🌟
    // CONTROLLER: Use these containers to display data (e.g., adding VBox children, updating Label text).
    public VBox membersListBox;
    public Label membersCountLabel;
    public GridPane eventsGrid;
    public StackPane rootStack;
    public VBox sidePanel;
    public HBox header;

    // ----------------------------------------------------------------

    public BorderPane createContent(Stage stage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f8f7f7ff;");

        // --- Background Styling ---
        Circle leftCircle = new Circle(120, Color.web("#ffe0ef"));
        leftCircle.setOpacity(0.4);
        StackPane.setAlignment(leftCircle, Pos.CENTER_LEFT);
        Circle rightCircle = new Circle(100, Color.web("#e0d4ff"));
        rightCircle.setOpacity(0.4);
        StackPane.setAlignment(rightCircle, Pos.CENTER_RIGHT);
        StackPane backgroundLayer = new StackPane(leftCircle, rightCircle);

        BorderPane content = new BorderPane();

        // --- Header ---
        Label headerTitle = new Label("Your Club’s Events");
        headerTitle.setFont(Font.font("Brush Script MT", 42));
        headerTitle.setTextFill(Color.WHITE);

        userIconBtn = new Button("👤");
        userIconBtn.setStyle("-fx-background-color: transparent; -fx-font-size: 22px;");
        // CONTROLLER BINDING INSTRUCTION: Set userIconBtn.setOnAction for sidePanel toggle logic.

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

        // --- Events Grid Area ---
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

        // --- Side Panel ---
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
        membersCountLabel = new Label("Club Members: 0"); // Initial state, Controller updates this.
        membersCountLabel.setFont(Font.font("Segoe UI Semibold", 18));
        membersCountLabel.setTextFill(Color.web("#333333"));

        addMemberBtn = new Button("➕ Add Member");
        addMemberBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 8;");
        // CONTROLLER BINDING INSTRUCTION: Set addMemberBtn.setOnAction to trigger the Add Member process, likely by calling a factory method like createGenericDialogStage.

        deleteMemberBtn = new Button("➖ Delete Member");
        deleteMemberBtn.setStyle("-fx-background-color: #ff4d4d; -fx-text-fill: white; -fx-background-radius: 8;");
        // CONTROLLER BINDING INSTRUCTION: Set deleteMemberBtn.setOnAction.

        verifyMemberBtn = new Button("✔️ Verify Member");
        verifyMemberBtn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-background-radius: 8;");
        // CONTROLLER BINDING INSTRUCTION: Set verifyMemberBtn.setOnAction.

        verifyEventBtn = new Button("✔️ Verify Event");
        verifyEventBtn.setStyle("-fx-background-color: #9C27B0; -fx-text-fill: white; -fx-background-radius: 8;");
        // CONTROLLER BINDING INSTRUCTION: Set verifyEventBtn.setOnAction.

        ScrollPane membersScroll = new ScrollPane(membersListBox);
        membersScroll.setFitToWidth(true);
        membersScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        membersScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        sidePanel.getChildren().addAll(
                membersCountLabel, addMemberBtn, deleteMemberBtn, verifyMemberBtn, verifyEventBtn, membersScroll
        );

        // --- Floating Add Event Button ---
        addEventBtn = new Button("+ New Event");
        addEventBtn.setStyle(
                "-fx-background-color: linear-gradient(to right, #8a2be2, #ff5fa2);" +
                        "-fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20;"
        );
        addEventBtn.setPrefWidth(160);
        addEventBtn.setPrefHeight(40);
        // CONTROLLER BINDING INSTRUCTION: Set addEventBtn.setOnAction to open the New Event Dialog using createNewEventDialogStage.

        rootStack = new StackPane(backgroundLayer, content);
        root.setCenter(rootStack);
        rootStack.getChildren().add(addEventBtn);
        StackPane.setAlignment(addEventBtn, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(addEventBtn, new Insets(20));

        StackPane.setAlignment(sidePanel, Pos.TOP_LEFT);
        header.heightProperty().addListener((obs, oldH, newH) -> {
            double topMargin = newH.doubleValue();
            StackPane.setMargin(sidePanel, new Insets(topMargin, 0, 0, 0));
        });
        
        return root;
    }

    // ----------------------------------------------------------------
    // 🏭 View-Factory Methods: Component Building and Styling
    // ----------------------------------------------------------------

    /**
     * Used by the Controller to create a styled member label.
     * @param name Member's name.
     * @param clickTarget The target element (e.g., a Button) for Controller's click binding (this parameter is unused in the current view, but kept for potential future use).
     * @return A styled HBox containing the member's label.
     */
    // CONTROLLER INSTRUCTION: Use this method to create member UI nodes and add them to 'membersListBox'.
    public HBox createMemberLabel(String name, Button clickTarget) {
        Circle circle = new Circle(8, Color.web("#ff5fa2"));
        Label label = new Label(name);
        label.setFont(Font.font(16));
        HBox hBox = new HBox(10, circle, label);
        hBox.setAlignment(Pos.CENTER_LEFT);
        return hBox;
    }

    /**
     * Used by the Controller to create a styled event card with data.
     * @param event Map of event data (Name, Time, etc.) expected from the Model.
     * @param actionButtons List of styled Buttons (Edit, Delete) created and bound by the Controller.
     * @return A styled VBox representing the event card.
     */
    // CONTROLLER INSTRUCTION: Use this method to create event UI cards and add them to 'eventsGrid' using GridPane.add(card, col, row).
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

        card.getChildren().addAll(title, typeLabel, description, timeLabelDisplay, locationLabelDisplay, adminButtons);
        card.setPrefWidth(400);

        return card;
    }

    // ----------------------------------------------------------------
    // 💅 Popup Factory Methods (Fully Styled, No Logic)
    // ----------------------------------------------------------------

    /**
     * Creates the fully styled Generic Dialog Stage (e.g., Add/Delete Member ID dialog).
     * @param titleText The title of the dialog.
     * @param fieldLabel The prompt text for the input field.
     * @param confirmBtn The confirm button object (created by Controller).
     * @param cancelBtn The cancel button object (created by Controller).
     * @param idField The TextField object (created by Controller).
     * @return A fully styled, but un-shown, Stage.
     * CONTROLLER INSTRUCTION: 1. Controller MUST create the Button objects (confirmBtn, cancelBtn) and TextField (idField). 2. Controller MUST SET setOnAction for confirmBtn (to process input) and cancelBtn (to close the dialog). 3. Controller MUST call dialogStage.show().
     */
    public Stage createGenericDialogStage(String titleText, String fieldLabel, Button confirmBtn, Button cancelBtn, TextField idField) {
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
                new Label(fieldLabel){{
                    setAlignment(Pos.CENTER_LEFT);
                    setMaxWidth(Double.MAX_VALUE);
                }},
                idField,
                buttons
        );
        form.setPadding(new Insets(20));
        form.setAlignment(Pos.CENTER_LEFT);
        form.setStyle("-fx-background-color: #ffe6f0; -fx-background-radius: 20;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 12,0,0,3);");

        Scene scene = new Scene(form, 400, 250);
        dialogStage.setScene(scene);
        
        return dialogStage;
    }

    /**
     * Creates the fully styled Message Dialog Stage (e.g., Success/Error notification).
     * @param message The message to display.
     * @param okBtn The OK button object (created by Controller).
     * @return A fully styled, but un-shown, Stage.
     * CONTROLLER INSTRUCTION: 1. Controller MUST create the Button object (okBtn). 2. Controller MUST SET okBtn.setOnAction (to close the dialog). 3. Controller MUST call msgStage.show().
     */
    public Stage createMessageDialogStage(String message, Button okBtn) {
        Stage msgStage = new Stage(StageStyle.UNDECORATED);

        Label label = new Label(message);
        label.setFont(Font.font("Segoe UI Semibold", 18));
        label.setTextFill(Color.web("#333333"));
        label.setWrapText(true);
        label.setAlignment(Pos.CENTER);
        label.setMaxWidth(Double.MAX_VALUE);

        okBtn.setStyle("-fx-font-size: 16; -fx-padding: 10 20; -fx-background-radius: 12; -fx-background-color:#4CAF50; -fx-text-fill:white;");

        VBox box = new VBox(20, label, okBtn);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(20));
        box.setStyle("-fx-background-color: #fff3cd; -fx-background-radius: 20;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 12,0,0,3);");

        Scene scene = new Scene(box, 350, 150);
        msgStage.setScene(scene);
        
        return msgStage;
    }

    /**
     * Creates the fully styled Member Info Dialog Stage.
     * @param memberData Data to populate the fields with (provided by Controller from Model).
     * @param okBtn The OK button object (created by Controller).
     * @return A fully styled, but un-shown, Stage.
     * CONTROLLER INSTRUCTION: 1. Controller MUST create and bind the okBtn action (to close the dialog). 2. Controller MUST pass data from the Model via the memberData Map. 3. Controller MUST call dialogStage.show().
     */
    public Stage createMemberInfoDialogStage(Map<String, String> memberData, Button okBtn) {
        Stage dialogStage = new Stage(StageStyle.UNDECORATED);

        Label header = new Label("✦ Member Info ✦");
        header.setFont(Font.font("Segoe Script", 24));
        header.setTextFill(Color.web("#ff5fa2"));
        header.setAlignment(Pos.CENTER);
        header.setMaxWidth(Double.MAX_VALUE);

        TextField nameField = new TextField(memberData.getOrDefault("name", "N/A"));
        nameField.setEditable(false);
        TextField uniField = new TextField(memberData.getOrDefault("uni", "N/A"));
        uniField.setEditable(false);

        okBtn.setStyle("-fx-font-size: 16; -fx-padding: 10 20; -fx-background-radius: 12; -fx-background-color:#4CAF50; -fx-text-fill:white;");

        VBox form = new VBox(10,
                header,
                new Label("Full Name:"){{ setAlignment(Pos.CENTER_LEFT); setMaxWidth(Double.MAX_VALUE); }}, nameField,
                new Label("University:"){{ setAlignment(Pos.CENTER_LEFT); setMaxWidth(Double.MAX_VALUE); }}, uniField,
                okBtn
        );

        form.setPadding(new Insets(20));
        form.setAlignment(Pos.CENTER_LEFT);
        form.setStyle("-fx-background-color: #ffe6f0; -fx-background-radius: 20;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 12,0,0,3);");

        Scene scene = new Scene(form, 400, 550);
        dialogStage.setScene(scene);
        
        return dialogStage;
    }

    // ----------------------------------------------------------------
    // 🆕 New Function: Event Add/Edit Window
    // ----------------------------------------------------------------

    /**
     * Creates the fully styled New/Edit Event Dialog Stage.
     * @param titleText The title (e.g., "New Event" or "Edit Event").
     * @param saveBtn The save button object (created by Controller).
     * @param cancelBtn The cancel button object (created by Controller).
     * @param nameField TextField for event name (created by Controller).
     * @param descriptionArea TextArea for description (created by Controller).
     * @param datePicker DatePicker for the date (created by Controller).
     * @param locationField TextField for location (created by Controller).
     * @return A fully styled, but un-shown, Stage.
     * CONTROLLER INSTRUCTION: 1. Controller MUST CREATE all input fields and buttons (and optionally pre-populate fields for 'Edit' mode). 2. Controller MUST SET setOnAction for saveBtn (to submit data to Model) and cancelBtn (to close the dialog). 3. Controller MUST call dialogStage.show().
     */
    public Stage createNewEventDialogStage(String titleText, Button saveBtn, Button cancelBtn, 
                                            TextField nameField, TextArea descriptionArea, 
                                            DatePicker datePicker, TextField locationField) {
        Stage dialogStage = new Stage(StageStyle.UNDECORATED);
        
        Label header = new Label("✨ " + titleText + " ✨");
        header.setFont(Font.font("Segoe Script", 28));
        header.setTextFill(Color.web("#8a2be2"));
        header.setAlignment(Pos.CENTER);
        header.setMaxWidth(Double.MAX_VALUE);
        
        // --- 1. Input Fields Layout ---
        GridPane formGrid = new GridPane();
        formGrid.setVgap(15);
        formGrid.setHgap(10);
        formGrid.setPadding(new Insets(10, 0, 10, 0));
        
        // Styling/Hints for fields
        nameField.setPromptText("Event Title");
        descriptionArea.setPromptText("Detailed description of the event...");
        descriptionArea.setWrapText(true);
        locationField.setPromptText("e.g., Room 12 / Sports Field");
        
        // Add labels and fields to the grid
        formGrid.add(new Label("Event Name:"), 0, 0); formGrid.add(nameField, 1, 0);
        formGrid.add(new Label("Description:"), 0, 1); formGrid.add(descriptionArea, 1, 1);
        formGrid.add(new Label("Date:"), 0, 2); formGrid.add(datePicker, 1, 2);
        formGrid.add(new Label("Location:"), 0, 3); formGrid.add(locationField, 1, 3);
        
        GridPane.setHgrow(nameField, Priority.ALWAYS);
        GridPane.setHgrow(descriptionArea, Priority.ALWAYS);
        GridPane.setHgrow(datePicker, Priority.ALWAYS);
        GridPane.setHgrow(locationField, Priority.ALWAYS);

        // --- 2. Button Styling ---
        saveBtn.setStyle("-fx-font-size: 16; -fx-padding: 10 20; -fx-background-radius: 12; -fx-background-color:#9C27B0; -fx-text-fill: white;");
        cancelBtn.setStyle("-fx-font-size: 16; -fx-padding: 10 20; -fx-background-radius: 12; -fx-background-color:#ff4d4d; -fx-text-fill: white;");
        
        HBox buttons = new HBox(20, saveBtn, cancelBtn);
        buttons.setAlignment(Pos.CENTER);

        // --- 3. Outer Layout Styling ---
        VBox fullLayout = new VBox(20, header, formGrid, buttons);
        fullLayout.setPadding(new Insets(30));
        fullLayout.setAlignment(Pos.TOP_CENTER);
        fullLayout.setStyle("-fx-background-color: #f0e6ff; -fx-background-radius: 20;" +
                             "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 12,0,0,3);");

        Scene scene = new Scene(fullLayout, 550, 500);
        dialogStage.setScene(scene);
        
        return dialogStage;
    }
}
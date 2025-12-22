package view;
// EventsUI.java
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

/**
 * EventsUI: A **pure View** class in the MVC architecture. 
 * Its sole responsibility is design and construction.
 * It contains NO logic for navigation, event joining, or hardcoded Model data.
 * * * **CONTROLLER INSTRUCTION:** * The Controller must fetch the 'eventsData' 
 * from the Model and pass it to createContent. It must also bind all interactive elements 
 * using the public fields below.
 */
public class EventsUI {

    // --- 🌟 Public Elements for Controller Binding 🌟 ---
    
    // CONTROLLER: Use this StackPane to attach the action for navigating back to ClubsUI.
    // ACTION: The Controller must set an event handler using backButtonContainer.setOnMouseClicked(...).
    public StackPane backButtonContainer;

    // CONTROLLER: Use this map to attach 'Join Event' actions for each event card.
    // ACTION: The Controller must iterate through this map and apply setOnAction to each Button (Key).
    // VALUE: The value (String) is the event ID needed by the Controller/Model to process the join request.
    public Map<Button, String> joinButtonsMap = new HashMap<>();

    /**
     * Creates the UI content. 
     * NOTE: 'eventsData' must be fetched by the Controller from the Model layer and passed here.
     */
    public Pane createContent(Stage stage, Scene scene, String clubName, List<Map<String, String>> eventsData) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: white;");

        // Assigns the generated back button container to the public field for the Controller
        backButtonContainer = createBackButtonUI(scene); 

        // Header Title
        Label title = new Label("Events of " + clubName);
        title.setFont(Font.font("Brush Script MT", 36));
        title.setTextFill(Color.WHITE);

        BorderPane header = new BorderPane();
        header.setLeft(backButtonContainer);
        header.setCenter(title);
        header.setPadding(new Insets(20));
        header.setBackground(new Background(new BackgroundFill(Color.web("#ff5fa2"),
                new CornerRadii(0, 0, 50, 50, false), Insets.EMPTY)));
        root.setTop(header);

        // --- Data Display Logic ---
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

            int col = 0, row = 0;
            for (Map<String, String> ev : eventsData) { // Iterating over data provided by Controller
                VBox card = createEventCard(ev, scene, this.joinButtonsMap); 
                grid.add(card, col, row);
                col++;
                if (col > 1) { col = 0; row++; }
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

    /**
     * Creates the UI for the Back Button (StackPane). 
     * * CONTROLLER INSTRUCTION: The action handler for navigation MUST be attached 
     * to the public field 'backButtonContainer' outside of this View class.
     */
    private StackPane createBackButtonUI(Scene scene) {
        Circle backCircle = new Circle(20, Color.WHITE);
        Label backLabel = new Label("←");
        backLabel.setFont(Font.font("Segoe UI", 18));
        backLabel.setTextFill(Color.web("#ff5fa2"));

        StackPane backButton = new StackPane(backCircle, backLabel);
        
        // ❌ NAVIGATION LOGIC REMOVED: Controller handles the transition.

        // Hover effect (Aesthetic, remains in View)
        backButton.setOnMouseEntered(e -> {
            backCircle.setFill(Color.web("#ffe9f2"));
        });
        backButton.setOnMouseExited(e -> {
            backCircle.setFill(Color.WHITE);
        });

        StackPane.setMargin(backLabel, new Insets(0,0,0,0));
        return backButton;
    }

    /**
     * Creates an event card UI element and registers its 'Join Event' button.
     * @param ev The event data map (provided by the Controller/Model).
     * @param scene The current scene (for responsive sizing).
     * @param buttonRegistry The public map (joinButtonsMap) for the Controller.
     */
    private VBox createEventCard(Map<String, String> ev, Scene scene, Map<Button, String> buttonRegistry) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(20));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), Insets.EMPTY)));
        card.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID,
                new CornerRadii(10), BorderWidths.DEFAULT)));

        // Event Details
        Label name = new Label(ev.get("name"));
        name.setFont(Font.font("Segoe UI", 18));
        name.setTextFill(Color.web("#ff5fa2"));
        // ... (Other labels for type, desc, date, location) ...

        Button join = new Button("Join Event");
        join.setStyle("-fx-background-color: linear-gradient(to right,#ff9acb,#ff5fa2);"
                + "-fx-text-fill:white; -fx-font-weight:bold; -fx-background-radius:10;");
        join.setPrefWidth(120);

        // REGISTRY FOR CONTROLLER: Map the Join Button to the Event ID/Name.
        // CONTROLLER: Access this map to find the Event ID (value) associated with the clicked Button (key).
        buttonRegistry.put(join, ev.get("id")); 

        // ❌ ACTION LOGIC REMOVED: Controller must handle the 'Join Event' setOnAction here.
        
        card.getChildren().addAll(name, new Label("Type: " + ev.get("type")), new Label(ev.getOrDefault("description","")), new Label("📅 " + ev.get("date")), new Label("📍 " + ev.get("location")), join);

        // Responsive Sizing
        if (scene != null) {
            card.prefWidthProperty().bind(scene.widthProperty().divide(2).subtract(60));
        } else {
            card.setPrefWidth(350);
        }

        return card;
    }
}

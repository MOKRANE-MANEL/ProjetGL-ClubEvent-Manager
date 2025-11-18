import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final double BASE_WIDTH = 1000;
    private static final double BASE_HEIGHT = 600;

    @Override
    public void start(Stage stage) {
        
        Scene scene = new Scene(new javafx.scene.layout.StackPane(), BASE_WIDTH, BASE_HEIGHT);

        ChoiceUI choiceUI = new ChoiceUI();
        scene.setRoot(choiceUI.createContent(stage, scene));

        stage.setScene(scene);
        stage.setTitle("Club Activities - Choose Login");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


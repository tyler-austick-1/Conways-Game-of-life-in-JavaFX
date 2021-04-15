import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int WINDOW_WIDTH = 515;
    private static final int WINDOW_HEIGHT = 515;

    private BorderPane root;
    private GridPanel gridPanel;

    private Simulator simulator;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        simulator = new Simulator();

        root = new BorderPane();
        gridPanel = new GridPanel(simulator, 10,10); // REPLACE WITH SIMULATIONS GRID COLUMNS AND ROWS

        root.setCenter(gridPanel);

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        stage.setScene(scene);
        stage.show();
    }
}

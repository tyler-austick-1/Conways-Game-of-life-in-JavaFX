import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

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
        gridPanel = new GridPanel(simulator, Simulation.ROWS, Simulation.COLUMNS);

        Button stepButton = new Button("Step");
        stepButton.setOnAction(this::stepSimulation);

        root.setCenter(gridPanel);
        root.setBottom(stepButton);

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    private void stepSimulation(ActionEvent actionEvent) {
        simulator.step();
        gridPanel.draw();
    }
}

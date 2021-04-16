import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    private BorderPane root;
    private GridPanel gridPanel;

    private Simulator simulator;
    private Timeline timeline;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        simulator = new Simulator();

        timeline = new Timeline(new KeyFrame(Duration.millis(200), this::stepSimulation));
        timeline.setCycleCount(Timeline.INDEFINITE);

        root = new BorderPane();
        gridPanel = new GridPanel(simulator, Simulation.ROWS, Simulation.COLUMNS);

        ToolBar toolBar = new ToolBar();

        Button stepButton = new Button("Step");
        stepButton.setOnAction(this::stepSimulation);

        Button runButton = new Button("Run");
        runButton.setOnAction(this::runSimulation);

        Button stopButton = new Button("Stop");
        stopButton.setOnAction(this::stopSimulation);

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(this::resetSimulation);

        toolBar.getItems().addAll(stepButton, runButton, stopButton, resetButton);

        root.setCenter(gridPanel);
        root.setBottom(toolBar);

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    private void resetSimulation(ActionEvent actionEvent) {
        simulator.getSimulation().reset();
        gridPanel.reset();
    }

    private void stepSimulation(ActionEvent actionEvent) {
        simulator.step();
        gridPanel.draw();
    }

    private void runSimulation(ActionEvent actionEvent) {
        timeline.play();
    }

    private void stopSimulation(ActionEvent actionEvent) {
        timeline.stop();
    }
}

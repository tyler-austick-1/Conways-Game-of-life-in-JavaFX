import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.List;

public class Main extends Application {

    private BorderPane root;
    private GridPanel gridPanel;

    private Simulator simulator;

    private Timeline timeline;
    private final double simulationSpeed = 200;

    private List<Control> itemsToDisable;
    private Label statusLabel;
    private Slider speedSlider;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        simulator = new Simulator();

        setSimulationSpeed(200);

        root = new BorderPane();
        gridPanel = new GridPanel(simulator, Simulation.ROWS, Simulation.COLUMNS);

        root.setCenter(gridPanel);
        root.setBottom(createToolBar());

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    private ToolBar createToolBar() {
        ToolBar toolBar = new ToolBar();

        Button stepButton = new Button("Step");
        stepButton.setOnAction(this::stepSimulation);

        Button runButton = new Button("Run");
        runButton.setOnAction(this::runSimulation);

        Button stopButton = new Button("Stop");
        stopButton.setOnAction(this::stopSimulation);

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(this::resetSimulation);

        speedSlider = new Slider(30, 500, simulationSpeed);
        speedSlider.valueProperty().addListener(this::sliderChanged);

        Pane paddingPane = new Pane();
        HBox.setHgrow(paddingPane, Priority.ALWAYS);

        statusLabel = new Label();

        toolBar.getItems().addAll(stepButton, runButton, stopButton, resetButton, speedSlider, paddingPane, statusLabel);
        itemsToDisable = Arrays.asList(stepButton, resetButton, speedSlider);

        return toolBar;
    }

    private void sliderChanged(Observable observable) {
        int sliderValue = (int) speedSlider.getValue();
        setSimulationSpeed(sliderValue);
        statusLabel.setText("Speed changed to: " + sliderValue);
    }

    private void setSimulationSpeed(double duration) {
        timeline = new Timeline(new KeyFrame(Duration.millis(duration), this::stepSimulation));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void resetSimulation(ActionEvent actionEvent) {
        simulator.getSimulation().reset();
        gridPanel.reset();
        statusLabel.setText("Simulation reset...");
    }

    private void stepSimulation(ActionEvent actionEvent) {
        simulator.step();
        gridPanel.draw();
    }

    private void runSimulation(ActionEvent actionEvent) {
        timeline.play();
        simulator.setEditable(false);
        statusLabel.setText("Running simulation...");
        itemsToDisable.forEach(b -> b.setDisable(true));
    }

    private void stopSimulation(ActionEvent actionEvent) {
        timeline.stop();
        simulator.setEditable(true);
        statusLabel.setText("Simulation stopped...");
        itemsToDisable.forEach(b -> b.setDisable(false));
    }
}

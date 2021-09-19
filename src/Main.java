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

/**
 * The Main class initialises the application and launches the GUI. It constructs the window of the
 * application to have a GridPanel with a ToolBar at the bottom of the window for the user to interact with.
 * The class sets up what happens when the user interacts with the GUI elements in the ToolBar.
 *
 * @author Tyler Austick
 * @version 2021-09-19
 */
public class Main extends Application {

    private BorderPane root;
    private GridPanel gridPanel;

    // The simulator running the simulation.
    private Simulator simulator;

    // The Timeline used to control the speed of the simulation.
    private Timeline timeline;
    private final double simulationSpeed = 200;

    // The list of GUI elements in the toolbar that should be disabled while the simulation is running.
    private List<Control> itemsToDisable;
    private Label statusLabel;
    private Slider speedSlider;

    /**
     * Main method which launches the application.
     * @param args Optional arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initialises the window by creating the GridPanel and ToolBar.
     * @param stage The stage of the application.
     * @throws Exception
     */
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

    /**
     * @return The ToolBar to be displayed at the bottom of the window.
     */
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

    /**
     * Event listener triggered when the user adjusts the speed slider.
     * @param observable The observed event.
     */
    private void sliderChanged(Observable observable) {
        int sliderValue = (int) speedSlider.getValue();
        setSimulationSpeed(sliderValue);
        statusLabel.setText("Speed changed to: " + sliderValue);
    }

    /**
     * Sets the simulation speed by adjusting the Timeline's animation speed.
     * @param duration The duration of a KeyFrame in the animation.
     */
    private void setSimulationSpeed(double duration) {
        timeline = new Timeline(new KeyFrame(Duration.millis(duration), this::stepSimulation));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Resets the simulation when the reset button is pressed.
     * @param actionEvent The event triggered when the reset button is pressed.
     */
    private void resetSimulation(ActionEvent actionEvent) {
        simulator.getSimulation().reset();
        gridPanel.reset();
        statusLabel.setText("Simulation reset...");
    }

    /**
     * Causes the simulation to step and updates the GUI.
     * @param actionEvent The event triggered when the simulation progresses.
     */
    private void stepSimulation(ActionEvent actionEvent) {
        simulator.step();
        gridPanel.draw();
    }

    /**
     * Runs the simulation by causing the timeline animation to begin.
     * @param actionEvent The event caused by running the simulation.
     */
    private void runSimulation(ActionEvent actionEvent) {
        timeline.play();
        simulator.setEditable(false);
        statusLabel.setText("Running simulation...");
        itemsToDisable.forEach(b -> b.setDisable(true));
    }

    /**
     * Stops the simulation by causing the timeline animation to stop.
     * @param actionEvent The event caused by running the simulation.
     */
    private void stopSimulation(ActionEvent actionEvent) {
        timeline.stop();
        simulator.setEditable(true);
        statusLabel.setText("Simulation stopped...");
        itemsToDisable.forEach(b -> b.setDisable(false));
    }
}

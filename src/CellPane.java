import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The CellPane is the graphical representation of a cell in the simulation's grid. The class
 * acts as part of the view and controller of the application (from the MVC model) that updates
 * the simulation based on the user's input with the GUI.
 *
 * @author Tyler Austick
 * @version 2021-09-19
 */
public class CellPane extends StackPane {

    // The simulator running the simulation.
    private Simulator simulator;

    // The colors of the cells.
    private static final Color aliveColor = Color.BLACK;
    private static final Color deadColor = Color.WHITE;

    // The position that the graphical cell is representing in the simulation grid.
    private int xPosition;
    private int yPosition;

    // Whether the cell being represented is alive.
    private boolean alive;

    // The graphical representation of the cell.
    private Rectangle cell;

    /**
     * Initialises the required fields and sets up the cell to be rendered.
     * @param simulator The simulator running the simulation.
     * @param xPosition The x position of the cell being represented.
     * @param yPosition The y position of the cell being represented.
     */
    public CellPane(Simulator simulator, int xPosition, int yPosition) {
        this.simulator = simulator;
        this.xPosition = xPosition;
        this.yPosition = yPosition;

        alive = false;

        cell = new Rectangle(50, 50);

        setColor();

        getChildren().addAll(cell);

        this.setOnMousePressed(this::handleMouseInput);
        this.setOnMouseEntered(this::handleMouseHover);
        this.setOnMouseExited(this::handleMouseExit);
    }

    /**
     * Sets whether the cell being represented is alive so that the color can be changed.
     * @param state The boolean value to set alive to.
     */
    public void setAlive(boolean state) {
        alive = state;
        setColor();
    }

    /**
     * Runs when the cell is clicked on.
     * @param mouseEvent The MouseEvent triggered by a mouse click.
     */
    private void handleMouseInput(MouseEvent mouseEvent) {
        if (simulator.isEditable()) {
            updateCell();
        }
    }

    /**
     * Changes the appearance of the cell graphically and updates the state
     * of the cell in the simulation.
     */
    public void updateCell() {
        alive = !alive;
        simulator.getSimulation().changeState(xPosition, yPosition);
        setColor();
    }

    /**
     * Runs when the mouse hovers over the cell.
     * @param mouseEvent The MouseEvent triggered by a mouse hover.
     */
    private void handleMouseHover(MouseEvent mouseEvent) {
        if (simulator.isEditable()) {
            if (!alive) {
                cell.setFill(Color.LIGHTGRAY);
            } else {
                cell.setFill(Color.DARKGRAY);
            }
        }
    }

    /**
     * Runs when the mouse exits the cell.
     * @param mouseEvent The MouseEvent triggered by the mouse exiting the cell.
     */
    private void handleMouseExit(MouseEvent mouseEvent) {
        if (simulator.isEditable()) {
            setColor();
        }
    }

    /**
     * Sets the color of the cell depending on the cells state in the simulation.
     */
    private void setColor() {
        cell.setStroke(Color.GRAY);

        if (alive) {
            cell.setFill(aliveColor);
        } else {
            cell.setFill(deadColor);
        }
    }
}


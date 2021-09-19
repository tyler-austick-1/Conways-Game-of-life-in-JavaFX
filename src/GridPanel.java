import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 * The GridPanel holds all the CellPanes in the desired dimensions of the simulation.
 * This class acts as part of the view and controller of the application (from the MVC model) that updates
 * the CellPanes based on the states of the cells in the simulation. This panel is displayed above the toolbar
 * in the GUI.
 *
 * @author Tyler Austick
 * @version 2021-09-19
 */
public class GridPanel extends GridPane {

    // The dimensions of the simulation's grid.
    private int rows;
    private int columns;

    // The simulator running the simulation.
    private Simulator simulator;

    /**
     * Sets up the dimensions for the grid panel and calls method to construct the grid.
     * @param simulator The simulator running the simulation.
     * @param rows The number of rows being represented.
     * @param columns The number of columns being represented.
     */
    public GridPanel(Simulator simulator, int rows, int columns) {
        this.simulator = simulator;
        this.rows = rows;
        this.columns = columns;

        createGrid();
    }

    /**
     * Creates a new CellPane for each cell and adds it to the GridPane.
     */
    private void createGrid() {
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                this.add(new CellPane(simulator, x, y), x, y); // j is column, i is row (they are reversed in the add method)
            }
        }
    }

    /**
     * Resets each CellPane in the grid to represent dead cells.
     */
    public void reset() {
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                CellPane cellPane = (CellPane) getNodeByIndices(x, y);
                cellPane.setAlive(false);
            }
        }
    }

    /**
     * Retrieves the state of each cell in the simulation and updates the
     * graphical representation of the CellPanes.
     */
    public void draw() {
        Simulation simulation = simulator.getSimulation();

        for (int x = 0; x < Simulation.COLUMNS; x++) {
            for (int y = 0; y < Simulation.ROWS; y++) {
                CellPane cellPane = (CellPane) getNodeByIndices(x, y);

                cellPane.setAlive(simulation.getState(x, y));
            }
        }
    }

    /**
     * @param x The x position of the CellPane.
     * @param y The y position of the CellPane.
     * @return The CellPane at position (x,y) in the GridPane.
     */
    private Node getNodeByIndices(int x, int y) {
        Node result = null;
        ObservableList<Node> children = this.getChildren();

        for (Node node : children) {
            if (GridPane.getRowIndex(node) == y && GridPane.getColumnIndex(node) == x) {
                result = node;
                break;
            }
        }

        return result;
    }
}

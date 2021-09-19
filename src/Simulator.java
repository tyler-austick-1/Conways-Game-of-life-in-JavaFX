import java.util.ArrayList;
import java.util.List;

/**
 * The Simulator class sets up a new simulation and runs the logic for changing
 * the state of the cells during each step.
 *
 * @author Tyler Austick
 * @version 2021-09-19
 */
public class Simulator {

    // Current simulation that is running.
    private Simulation simulation;

    // Whether the state of the cells can be edited.
    private boolean editable;

    /**
     * Initialises a new simulation and allows the cells to be edited.
     */
    public Simulator() {
        simulation = new Simulation();
        editable = true;
    }

    /**
     * @return whether the cells' state can be altered.
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * Sets whether the cells' state is editable.
     * @param editable The value for editable to be set to.
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    /**
     * @return The current simulation.
     */
    public Simulation getSimulation() {
        return simulation;
    }

    /**
     * Runs for each step in the simulation. Iterates through each cell and
     * updates its state according to the rules of Conway's Game of Life.
     */
    public void step() {
        List<int[]> cellsToChangeState = new ArrayList<>();

        for (int x = 0; x < Simulation.COLUMNS; x++) {
            for (int y = 0; y < Simulation.ROWS; y++) {
                boolean currentCellAlive = simulation.getState(x, y);
                int aliveNeighbours = simulation.numberOfAliveNeighbours(x, y);

                if (currentCellAlive && (aliveNeighbours < 2 || aliveNeighbours > 3)) {
                    int[] coordinates = {x, y};
                    cellsToChangeState.add(coordinates);
                } else if (!currentCellAlive && (aliveNeighbours == 3)) {
                    int[] coordinates = {x, y};
                    cellsToChangeState.add(coordinates);
                }
            }
        }
        changeCellsToNewState(cellsToChangeState);
    }

    /**
     * Changes the state of each cell in a list of cell coordinates.
     * @param cells The list of cell coordinates to change state.
     */
    private void changeCellsToNewState(List<int[]> cells) {
        for (int[] cell : cells) {
            simulation.changeState(cell[0], cell[1]);
        }
    }
}

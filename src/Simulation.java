import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Simulation class utilises the grid which holds the state of the cells in the simulation.
 * It has methods to alter the state of the grid as well as returning the state of requested cells.
 *
 * @author Tyler Austick
 * @version 2021-09-19
 */
public class Simulation {

    // Dimensions of the grid.
    public static final int COLUMNS = 30;
    public static final int ROWS = 17;

    // A grid of boolean values which represent dead or alive cells.
    private boolean[][] grid;

    /**
     * Initialises a new grid of cells.
     */
    public Simulation() {
        grid = new boolean[COLUMNS][ROWS];
    }

    /**
     * @param x The x coordinate of the cell.
     * @param y The y coordinate of the cell.
     * @return The state of the cell at position (x,y) in the grid.
     */
    public boolean getState(int x, int y) {
        return grid[x][y];
    }

    /**
     * Resets the state of all the cells in the grid.
     */
    public void reset() {
        grid = new boolean[COLUMNS][ROWS];
    }

    /**
     * Changes the state of the cell at (x,y) in the grid from alive to dead or from dead to alive.
     * @param x The x coordinate of the cell.
     * @param y The y coordinate of the cell.
     */
    public void changeState(int x, int y) {
        if (isWithinBounds(x, y)) {
            grid[x][y] = !grid[x][y];
        } else {
            System.out.println("Cell (" + x + ", " + y + ") is out of bounds and could not change state");
        }
    }

    /**
     * @param x The x coordinate of the cell.
     * @param y The y coordinate of the cell.
     * @return The number of alive neighbours a cell at coordinates (x,y) has.
     */
    public int numberOfAliveNeighbours(int x, int y) {
        List<Boolean> neighbours = getNeighbours(x, y);
        int counter = 0;

        for (Boolean neighbour : neighbours) {
            if (neighbour) {
                counter++;
            }
        }

        return counter;
    }

    /**
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @return Whether the coordinates (x,y) is within the grid.
     */
    private boolean isWithinBounds(int x, int y) {
        if (x >= 0 && x < COLUMNS && y >= 0 && y < ROWS) {
            return true;
        }
        return false;
    }

    /**
     * @param x The x coordinate of the cell.
     * @param y The y coordinate of the cell.
     * @return A list of the states of the cell's (at coordinates (x,y)) neighbours.
     */
    private List<Boolean> getNeighbours(int x, int y) {
        List<Boolean> neighbours = new ArrayList<>();

        if (isWithinBounds(x, y)) {
            Boolean topLeft = grid[Math.floorMod((x-1), COLUMNS)][Math.floorMod((y-1), ROWS)];
            Boolean above = grid[Math.floorMod((x-1), COLUMNS)][y];
            Boolean topRight = grid[Math.floorMod((x-1), COLUMNS)][Math.floorMod((y+1), ROWS)];
            Boolean left = grid[x][Math.floorMod((y-1), ROWS)];
            Boolean right = grid[x][Math.floorMod((y+1), ROWS)];
            Boolean bottomLeft = grid[Math.floorMod((x+1), COLUMNS)][Math.floorMod((y-1), ROWS)];
            Boolean below = grid[Math.floorMod((x+1), COLUMNS)][y];
            Boolean bottomRight = grid[Math.floorMod((x+1), COLUMNS)][Math.floorMod((y+1), ROWS)];

            List<Boolean> calculatedCells = Arrays.asList(topLeft, above, topRight, left, right, bottomLeft, below, bottomRight);

            neighbours.addAll(calculatedCells);
        }
        return neighbours;
    }
}
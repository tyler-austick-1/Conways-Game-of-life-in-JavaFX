import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Simulation {

    public static final int COLUMNS = 30;
    public static final int ROWS = 17;

    private final boolean[][] grid;

    public Simulation() {
        grid = new boolean[COLUMNS][ROWS];
    }

    public boolean[][] getGrid() {
        return grid;
    }

    public boolean getState(int x, int y) {
        return grid[x][y];
    }

    public void reset() {
        for(int x = 0; x < COLUMNS; x++) {
            for (int y = 0; y < ROWS; y++) {
                grid[x][y] = false;
            }
        }
    }

    public void changeState(int x, int y) {
        if (isWithinBounds(x, y)) {
            grid[x][y] = !grid[x][y];
        } else {
            System.out.println("Cell (" + x + ", " + y + ") is out of bounds and could not change state");
        }
    }

    public void setAlive(int x, int y) {
        if (isWithinBounds(x, y)) {
            grid[x][y] = true;
        } else {
            System.out.println("Cell (" + x + ", " + y + ") is out of bounds and could not be set alive");
        }
    }

    public void setDead(int x, int y) {
        if (isWithinBounds(x, y)) {
            grid[x][y] = false;
        } else {
            System.out.println("Cell (" + x + ", " + y + ") is out of bounds and could not be set dead");
        }
    }

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

    private boolean isWithinBounds(int x, int y) {
        if (x >= 0 && x < COLUMNS && y >= 0 && y < ROWS) {
            return true;
        }
        return false;
    }

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
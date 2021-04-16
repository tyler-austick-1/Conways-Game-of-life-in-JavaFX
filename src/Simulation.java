import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Simulation {

    public static final int COLUMNS = 20;
    public static final int ROWS = 20;

    private final boolean[][] grid;

    public Simulation() {
        grid = new boolean[COLUMNS][ROWS];
    }

    public boolean getState(int x, int y) {
        return grid[x][y];
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
            Boolean topLeft = grid[Math.floorMod((x-1), ROWS)][Math.floorMod((y-1), COLUMNS)];
            Boolean above = grid[Math.floorMod((x-1), ROWS)][y];
            Boolean topRight = grid[Math.floorMod((x-1), ROWS)][Math.floorMod((y+1), COLUMNS)];
            Boolean left = grid[x][Math.floorMod((y-1), COLUMNS)];
            Boolean right = grid[x][Math.floorMod((y+1), COLUMNS)];
            Boolean bottomLeft = grid[Math.floorMod((x+1), ROWS)][Math.floorMod((y-1), COLUMNS)];
            Boolean below = grid[Math.floorMod((x+1), ROWS)][y];
            Boolean bottomRight = grid[Math.floorMod((x+1), ROWS)][Math.floorMod((y+1), COLUMNS)];

//            System.out.println("Top left: (" + Math.floorMod((i-1), rows) + ", " + Math.floorMod((j-1), columns) + ")");
//            System.out.println("Above: (" + Math.floorMod((i-1), rows) + ", " + j + ")");
//            System.out.println("Top right: (" + Math.floorMod((i-1), rows) + ", " + Math.floorMod((j+1), columns) + ")");
//            System.out.println("Left: (" + i + ", " + Math.floorMod((j-1), columns) + ")");
//            System.out.println("Right: (" + i + ", " + Math.floorMod((j+1), columns) + ")");
//            System.out.println("Bottom Left: (" + Math.floorMod((i+1), rows) + ", " + Math.floorMod((j-1), columns) + ")");
//            System.out.println("Below: (" + Math.floorMod((i+1), rows) + ", " + j + ")");
//            System.out.println("Bottom right: (" + Math.floorMod((i+1), rows) + ", " + Math.floorMod((j+1), columns) + ")");

            List<Boolean> calculatedCells = Arrays.asList(topLeft, above, topRight, left, right, bottomLeft, below, bottomRight);

            neighbours.addAll(calculatedCells);
        }
        return neighbours;
    }
}
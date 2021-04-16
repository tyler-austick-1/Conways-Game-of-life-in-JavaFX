import java.util.ArrayList;
import java.util.List;

public class Simulator {

    private Simulation simulation;

    public Simulator() {
        simulation = new Simulation();
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void step() {
        List<int[]> cellsToChangeState = new ArrayList<>();

        for (int x = 0; x < Simulation.COLUMNS; x++) {
            for (int y = 0; y < Simulation.ROWS; y++) {
                boolean currentCell = simulation.getState(x, y);
                int aliveNeighbours = simulation.numberOfAliveNeighbours(x, y);

                if (currentCell && (aliveNeighbours < 2 || aliveNeighbours > 3)) {
                    int[] coordinates = {x, y};
                    cellsToChangeState.add(coordinates);
                } else if (!currentCell && (aliveNeighbours == 3)) {
                    int[] coordinates = {x, y};
                    cellsToChangeState.add(coordinates);
                }
            }
        }
        changeCellsToNewState(cellsToChangeState);
    }

    private void changeCellsToNewState(List<int[]> cells) {
        for (int[] cell : cells) {
            simulation.changeState(cell[0], cell[1]);
        }
    }
}

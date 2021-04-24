import java.util.ArrayList;
import java.util.List;

public class Simulator {

    private Simulation simulation;

    private boolean editable;

    public Simulator() {
        simulation = new Simulation();
        editable = true;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public Simulation getSimulation() {
        return simulation;
    }

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

    private void changeCellsToNewState(List<int[]> cells) {
        for (int[] cell : cells) {
            simulation.changeState(cell[0], cell[1]);
        }
    }
}

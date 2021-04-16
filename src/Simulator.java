import java.util.ArrayList;
import java.util.List;

public class Simulator {

    private Simulation simulation;

    public Simulator() {
        simulation = new Simulation();
    }

//    private void step() {
//        List<Cell> cellsToChangeState = new ArrayList<>();
//
//        for (int i = 0; i < gridRows; i++) {
//            for (int j = 0; j < gridColumns; j++) {
//                Cell currentCell = grid.getCell(i, j);
//                List<Cell> cellNeighbours = grid.getNeighbours(i, j);
//                int aliveNeighbours = grid.numberOfAliveNeighbours(cellNeighbours);
//
//
//                if (currentCell.isAlive() && (aliveNeighbours < 2 || aliveNeighbours > 3)) {
//                    cellsToChangeState.add(currentCell);
//                } else if (!currentCell.isAlive() && (aliveNeighbours == 3)) {
//                    cellsToChangeState.add(currentCell);
//                }
//            }
//        }
//
//        changeCellsToNewState(cellsToChangeState);
//    }
//
//    private void changeCellsToNewState(List<Cell> cells) {
//        for (Cell cell : cells) {
//            cell.changeState();
//        }
//    }

}

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class GridPanel extends GridPane {

    private Simulator simulator;

    public GridPanel(Simulator simulator, int rows, int columns) {
        this.simulator = simulator;
        createGrid(rows, columns);
    }

    private void createGrid(int rows, int columns) {
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                this.add(new CellPane(simulator, x, y), x, y); // j is column, i is row (they are reversed in the add method)
            }
        }
    }

    public void draw() {
        Simulation simulation = simulator.getSimulation();

        for (int x = 0; x < Simulation.COLUMNS; x++) {
            for (int y = 0; y < Simulation.ROWS; y++) {
                CellPane cellPane = (CellPane) getNodeByIndices(x, y);

                cellPane.setAlive(simulation.getState(x, y));
            }
        }
    }

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

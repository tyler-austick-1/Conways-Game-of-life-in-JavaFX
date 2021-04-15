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
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.add(new CellPane(simulator), j, i); // j is column, i is row (they are reversed in the add method)
            }
        }
    }

    private Node getNodeByIndices(int row, int column) {
        Node result = null;
        ObservableList<Node> children = this.getChildren();

        for (Node node : children) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }
}

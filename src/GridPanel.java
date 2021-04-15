import javafx.scene.layout.GridPane;

public class GridPanel extends GridPane {

    public GridPanel(int rows, int columns) {
        createGrid(rows, columns);
    }

    private void createGrid(int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.add(new CellPane(), j, i); // j is column, i is row (they are reversed in the add method)
            }
        }
    }

}

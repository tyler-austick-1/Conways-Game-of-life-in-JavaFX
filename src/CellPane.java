import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellPane extends StackPane {

    private Rectangle cell;

    public CellPane() {
        cell = new Rectangle(50, 50);

        cell.setFill(null);
        cell.setStroke(Color.BLACK);

        getChildren().addAll(cell);
    }
}


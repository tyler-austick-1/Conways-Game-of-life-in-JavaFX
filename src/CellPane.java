import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellPane extends StackPane {

    private Simulator simulator;

    private static final Color aliveColor = Color.BLACK;
    private static final Color deadColor = Color.WHITE;

    private boolean alive;

    private Rectangle cell;

    public CellPane(Simulator simulator) {
        this.simulator = simulator;
        alive = false;

        cell = new Rectangle(50, 50);

        setColor();

        getChildren().addAll(cell);

        this.setOnMouseClicked(this::handleMouseInput);
        this.setOnMouseEntered(this::handleMouseHover);
        this.setOnMouseExited(this::handleMouseExit);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean state) {
        alive = state;
        setColor();
    }

    private void handleMouseInput(MouseEvent mouseEvent) {
        alive = !alive;
        setColor();
    }

    private void handleMouseHover(MouseEvent mouseEvent) {
        cell.setFill(Color.LIGHTGRAY);
    }

    private void handleMouseExit(MouseEvent mouseEvent) {
        setColor();
    }

    private void setColor() {
        cell.setStroke(Color.GRAY);

        if (alive) {
            cell.setFill(aliveColor);
        } else {
            cell.setFill(deadColor);
        }
    }
}


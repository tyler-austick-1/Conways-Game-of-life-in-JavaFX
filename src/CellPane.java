import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellPane extends StackPane {

    private Simulator simulator;

    private static final Color aliveColor = Color.BLACK;
    private static final Color deadColor = Color.WHITE;

    private int xPosition;
    private int yPosition;

    private boolean alive;

    private Rectangle cell;

    public CellPane(Simulator simulator, int xPosition, int yPosition) {
        this.simulator = simulator;
        this.xPosition = xPosition;
        this.yPosition = yPosition;

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
        simulator.getSimulation().changeState(xPosition, yPosition);
        setColor();
    }

    private void handleMouseHover(MouseEvent mouseEvent) {
        if (!alive) {
            cell.setFill(Color.LIGHTGRAY);
        } else {
            cell.setFill(Color.DARKGRAY);
        }
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


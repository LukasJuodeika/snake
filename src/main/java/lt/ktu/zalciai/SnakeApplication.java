package lt.ktu.zalciai;

import lt.ktu.zalciai.controls.InputActionListener;
import lt.ktu.zalciai.controls.UserInputKeyboard;
import lt.ktu.zalciai.display.DisplayContract;
import lt.ktu.zalciai.display.DisplayJPanel;
import lt.ktu.zalciai.enums.ControlAction;
import lt.ktu.zalciai.enums.Direction;
import lt.ktu.zalciai.food.Food;
import lt.ktu.zalciai.food.FoodFactory;
import lt.ktu.zalciai.snakemap.SnakeMap;
import lt.ktu.zalciai.snakemap.SnakeMapDefault;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.Timer;

public class SnakeApplication implements ActionListener, InputActionListener {

    private final int FRAME_INTERVAL = 80;
    public Timer timer = new Timer(FRAME_INTERVAL, this);
    public LinkedList<Point> snake = new LinkedList<>();

    public static SnakeApplication snakeApp;
    public int tailLength, score;
    private Direction direction = Direction.DOWN;
    public boolean over = false, paused;
    public Color colorGet;

    public Food food;
    private final DisplayContract.View view;
    private final SnakeMap snakeMap;

    public SnakeApplication() {
        snakeMap = new SnakeMapDefault(Constants.SNAKE_GRID_WIDTH, Constants.SNAKE_GRID_HEIGHT);
        view = new DisplayJPanel(
                new UserInputKeyboard(this),
                snakeMap.getWalls()
        );
        startGame();
    }

    public void startGame() {
        over = false;
        score = 0;
        direction = Direction.DOWN;
        paused = false;
        tailLength = 0;
        snake.clear();
        snake.add(new Point(Constants.SNAKE_GRID_SCALE, Constants.SNAKE_GRID_SCALE));
        food = FoodFactory.getInstance().randomFood();
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Point head = snake.getLast();
        if (paused || over)
            return;
        Point next = createNextHead(direction, head);

        if (snake.contains(next) || snakeMap.colides(next)) {
            over = true;
        }
        if (snake.size() > tailLength) {
            snake.remove();
        }
        if (next.equals(food.getPoint())) {
            score += food.getScore();
            tailLength += 1;
            food = FoodFactory.getInstance().randomFood();
        }
        snake.add(next);
        view.refresh();
    }

    public Point createNextHead(Direction direction, Point head) {
        Point next;
        switch (direction) {
            case UP:
                next = new Point(head.x, head.y - 1);
                break;
            case DOWN:
                next = new Point(head.x, head.y + 1);
                break;
            case LEFT:
                next = new Point(head.x - 1, head.y);
                break;
            default: //RIGHT
                next = new Point(head.x + 1, head.y);
                break;
        }
        return next;
    }

    public static void main(String[] args) {
        snakeApp = new SnakeApplication();
    }

    @Override
    public void onControlAction(ControlAction controlAction) {
        switch (controlAction) {
            case PAUSE: {
                if (over) {
                    startGame();
                } else {
                    paused = !paused;
                }
            }
            break;
        }
    }

    @Override
    public void onDirectionAction(Direction direction) {
        if (!direction.isOpposite(this.direction)) {
            this.direction = direction;
        }
    }
}

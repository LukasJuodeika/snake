package lt.ktu.zalciai;

import lt.ktu.zalciai.controls.InputActionListener;
import lt.ktu.zalciai.controls.UserInputWASD;
import lt.ktu.zalciai.display.DisplayContract;
import lt.ktu.zalciai.display.DisplayJPanel;
import lt.ktu.zalciai.enums.ControlAction;
import lt.ktu.zalciai.enums.Direction;
import lt.ktu.zalciai.food.FoodFactory;
import lt.ktu.zalciai.food.entities.Food;
import lt.ktu.zalciai.snakegrid.SnakeGridContract;
import lt.ktu.zalciai.snakemap.SnakeMap;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.Timer;

public class SnakeApplication implements ActionListener, InputActionListener, SnakeGridContract.Controller {

    private final int FRAME_INTERVAL = 80;
    public Timer timer = new Timer(FRAME_INTERVAL, this);
    public LinkedList<Point> snake = new LinkedList<>();
    public int tailLength, score;
    private Direction direction = Direction.DOWN;
    public boolean over = false, paused;

    public Food food;
    private final DisplayContract.View view;
    private final SnakeMap snakeMap;
    private final FoodFactory foodFactory;

    public SnakeApplication(
            FoodFactory foodFactory,
            SnakeMap snakeMap
    ) {
        this.foodFactory = foodFactory;
        this.snakeMap = snakeMap;
        view = new DisplayJPanel(
                new UserInputWASD(this),
                this
        );
    }

    public void startGame() {
        over = false;
        score = 0;
        direction = Direction.DOWN;
        paused = false;
        tailLength = 0;
        snake.clear();
        snake.add(new Point(Constants.SNAKE_GRID_SCALE, Constants.SNAKE_GRID_SCALE));
        food = foodFactory.generateFood();
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
            food = foodFactory.generateFood();
            System.out.println("Score: " + score);
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

    @Override
    public Map<String, Collection<Point>> generateDrawPoints() {
        Map<String, Collection<Point>> colorPoints = new HashMap<>();

        //draw snake
        addToColorPoints(colorPoints, "#FF00FF", snake);

        //draw food
        addToColorPoints(colorPoints, food.getColorHex(), Collections.singletonList(food.getPoint()));

        //draw walls
        addToColorPoints(colorPoints, "#000", snakeMap.getWalls());

        return colorPoints;
    }

    private void addToColorPoints(
            Map<String, Collection<Point>> colorPoints,
            String colorHex,
            Collection<Point> points
    ) {
        colorPoints.merge(colorHex, points, (list1, list2) ->
                Stream.concat(list1.stream(), list2.stream())
                        .collect(Collectors.toList())
        );
    }
}

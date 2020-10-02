package lt.ktu.zalciai;

import lt.ktu.zalciai.controls.InputActionListener;
import lt.ktu.zalciai.controls.UserInputKeyboard;
import lt.ktu.zalciai.display.DisplayContract;
import lt.ktu.zalciai.display.DisplayJPanel;
import lt.ktu.zalciai.enums.ControlAction;
import lt.ktu.zalciai.enums.Direction;
import lt.ktu.zalciai.snakemap.SnakeMap;
import lt.ktu.zalciai.snakemap.SnakeMapDefault;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;

public class SnakeApplication implements ActionListener, InputActionListener {

    private final int FRAME_INTERVAL = 80;
    public Timer timer = new Timer(FRAME_INTERVAL, this);
    public LinkedList<Point> snake = new LinkedList<>();

    public static SnakeApplication snakeApp;
    public int tailLength, score, eaten;
    private Direction direction = Direction.DOWN;
    public boolean over = false, paused;
    public Color colorGet;
    public Random random;

    public Point head, food;
    private final DisplayContract.View view;
    private final SnakeMap snakeMap;

    public SnakeApplication() {
        snakeMap = new SnakeMapDefault(80, 80);
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
        head = new Point(10, 10);
        random = new Random();
        food = new Point(random.nextInt(79), random.nextInt(66));
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.refresh();
        if (paused || over)
            return;
        snake.add(new Point(head.x, head.y));

        if (direction == Direction.UP) {
            head = new Point(head.x, head.y - 1);
        }
        if (direction == Direction.DOWN) {
            head = new Point(head.x, head.y + 1);
        }
        if (direction == Direction.LEFT) {
            head = new Point(head.x - 1, head.y);
        }
        if (direction == Direction.RIGHT) {
            head = new Point(head.x + 1, head.y);
        }

        if(snake.contains(head) || snakeMap.colides(head)) {
            over = true;
        }

        if (snake.size() > tailLength) {
            snake.remove();
        }
        if (head.equals(food)) {
            score += 5;
            tailLength += 1;
            food.setLocation(random.nextInt(79), random.nextInt(66));
            eaten = 1;
        }
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

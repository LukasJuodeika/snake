package lt.ktu.zalciai;

import lt.ktu.zalciai.controls.InputActionListener;
import lt.ktu.zalciai.controls.UserInputKeyboard;
import lt.ktu.zalciai.display.DisplayContract;
import lt.ktu.zalciai.display.DisplayJPanel;
import lt.ktu.zalciai.enums.ControlAction;
import lt.ktu.zalciai.enums.Direction;

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


    public SnakeApplication() {
        view = new DisplayJPanel(new UserInputKeyboard(this));
        startGame();
    }

    public void startGame() {
        over = false;
        score = 0;
        direction = Direction.DOWN;
        paused = false;
        tailLength = 0;
        snake.clear();
        head = new Point(0, 0);
        random = new Random();
        food = new Point(random.nextInt(79), random.nextInt(66));
        timer.start();
    }

    private boolean notContain(Point point, List<Point> points) {
        return !points.contains(point);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.refresh();
        if (paused || over)
            return;
        snake.add(new Point(head.x, head.y));

        if (direction == Direction.UP) {
            if (head.y - 1 >= 0 && notContain(new Point(head.x, head.y - 1), snake)) {
                head = new Point(head.x, head.y - 1);
            } else {
                over = true;
            }
        }
        if (direction == Direction.DOWN) {
            if (head.y + 1 < 67 && notContain(new Point(head.x, head.y + 1), snake)) {
                head = new Point(head.x, head.y + 1);
            } else {
                over = true;
            }
        }
        if (direction == Direction.LEFT) {
            if (head.x - 1 >= 0 && notContain(new Point(head.x - 1, head.y), snake)) {
                head = new Point(head.x - 1, head.y);
            } else {
                over = true;
            }
        }
        if (direction == Direction.RIGHT) {
            if (head.x + 1 < 79 && notContain(new Point(head.x + 1, head.y), snake)) {
                head = new Point(head.x + 1, head.y);
            } else {
                over = true;
            }
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

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
import java.util.Random;
import javax.swing.Timer;

public class Snake implements ActionListener, InputActionListener {

    public static final int SCALE = 10;
    public Timer timer = new Timer(20, this);
    public LinkedList<Point> sParts = new LinkedList<Point>();

    public static Snake snake;
    public int ticks = 0, tailLength, score, time, eaten;
    private Direction direction = Direction.DOWN;
    public boolean over = false, paused;
    public Color colorGet;
    public Random random;

    public Point head, headII, cherry;
    private final DisplayContract.View view;


    public Snake() {
        view = new DisplayJPanel(new UserInputKeyboard(this));
        startGame();
    }

    public void startGame() {
        over = false;
        score = 0;
        direction = Direction.DOWN;
        paused = false;
        time = 0;
        tailLength = 0;
        sParts.clear();
        head = new Point(0, 0);
        headII = new Point(79, 66);
        random = new Random();
        cherry = new Point(random.nextInt(79), random.nextInt(66));
        timer.start();
    }

    public boolean noTailAtFirst(int x, int y) {
        for (Point point : sParts) {
            if (point.equals(new Point(x, y))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ticks++;
        view.refresh();
        //First snake actions
        if (ticks % 4 == 0 && head != null && over != true && !paused) {
            time++;
            //sParts.insert(new Point(head.x, head.y));
            sParts.add(new Point(head.x, head.y));
            if (direction == Direction.UP) {
                if (head.y - 1 >= 0 && noTailAtFirst(head.x, head.y - 1)) {
                    head = new Point(head.x, head.y - 1);
                } else {
                    over = true;
                }
            }
            if (direction == Direction.DOWN) {
                if (head.y + 1 < 67 && noTailAtFirst(head.x, head.y + 1)) {
                    head = new Point(head.x, head.y + 1);
                } else {
                    over = true;
                }
            }
            if (direction == Direction.LEFT) {
                if (head.x - 1 >= 0 && noTailAtFirst(head.x - 1, head.y)) {
                    head = new Point(head.x - 1, head.y);
                } else {
                    over = true;
                }
            }
            if (direction == Direction.RIGHT) {
                if (head.x + 1 < 79 && noTailAtFirst(head.x + 1, head.y)) {
                    head = new Point(head.x + 1, head.y);
                } else {
                    over = true;
                }
            }
            if (sParts.size() > tailLength) {
                sParts.remove();
            }
            if (cherry != null) {
                if (head.equals(cherry)) {
                    score += 5;
                    tailLength += 1;
                    cherry.setLocation(random.nextInt(79), random.nextInt(66));
                    eaten = 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        snake = new Snake();
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
        if(!direction.isOpposite(this.direction)) {
            this.direction = direction;
        }
    }
}

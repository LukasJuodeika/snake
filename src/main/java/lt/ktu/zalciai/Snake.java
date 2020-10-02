package lt.ktu.zalciai;

import lt.ktu.zalciai.controls.InputActionListener;
import lt.ktu.zalciai.controls.UserInputKeyboard;
import lt.ktu.zalciai.enums.ControlAction;
import lt.ktu.zalciai.enums.Direction;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener, InputActionListener {

    public static final int SCALE = 10;
    public JFrame jframe;
    public RenderPanel renderPanel;
    public Timer timer = new Timer(20, this);
    public LinkedList<Point> sParts = new LinkedList<Point>();

    public static Snake snake;
    public int ticks = 0, tailLength, score, time, eaten;
    private Direction direction = Direction.DOWN;
    public boolean over = false, paused;
    public Color colorGet;
    public Random random;

    public Point head, headII, cherry;

    public Dimension dim;

    public Snake() {

        dim = Toolkit.getDefaultToolkit().getScreenSize();
        jframe = new JFrame("Snake");
        jframe.setTitle("Snake žaidimas");
        jframe.setVisible(true);
        jframe.setSize(805, 700);
        jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
        jframe.add(renderPanel = new RenderPanel());
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.addKeyListener(new UserInputKeyboard(this));
        jframe.setResizable(false);
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
        renderPanel.repaint();
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

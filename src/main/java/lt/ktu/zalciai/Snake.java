/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.ktu.zalciai;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener {

    public JFrame jframe;
    public RenderPanel renderPanel;
    public Timer timer = new Timer(20, this);
    public LinkedList<Point> sParts = new LinkedList<Point>();

    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;
    public static Snake snake;
    public int ticks = 0, tailLength, direction = DOWN, score, time, eaten;
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
        jframe.addKeyListener(this);
        jframe.setResizable(false);
        startGame();
    }

    public void startGame() {
        over = false;
        score = 0;
        direction = DOWN;
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
            if (direction == UP) {
                if (head.y - 1 >= 0 && noTailAtFirst(head.x, head.y - 1)) {
                    head = new Point(head.x, head.y - 1);
                } else {
                    over = true;
                }
            }
            if (direction == DOWN) {
                if (head.y + 1 < 67 && noTailAtFirst(head.x, head.y + 1)) {
                    head = new Point(head.x, head.y + 1);
                } else {
                    over = true;
                }
            }
            if (direction == LEFT) {
                if (head.x - 1 >= 0 && noTailAtFirst(head.x - 1, head.y)) {
                    head = new Point(head.x - 1, head.y);
                } else {
                    over = true;
                }
            }
            if (direction == RIGHT) {
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
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        //First snake control
        if (i == KeyEvent.VK_W && direction != DOWN) {
            direction = UP;
        }
        if (i == KeyEvent.VK_S && direction != UP) {
            direction = DOWN;
        }
        if (i == KeyEvent.VK_D && direction != LEFT) {
            direction = RIGHT;
        }
        if (i == KeyEvent.VK_A && direction != RIGHT) {
            direction = LEFT;
        }

        if (i == KeyEvent.VK_SPACE) {
            if (over) {
                startGame();
            } else {
                paused = !paused;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}

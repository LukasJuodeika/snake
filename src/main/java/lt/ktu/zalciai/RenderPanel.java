/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.ktu.zalciai;

import java.awt.*;
import javax.swing.JPanel;
import java.util.Random;
import java.util.Set;

public class RenderPanel extends JPanel {

    public Color cherry;

    private final Set<Point> walls;
    private final int height;
    private final int width;
    private final int scale;

    public RenderPanel(
            int height,
            int width,
            int scale,
            Set<Point> walls
    ) {
        this.height = height;
        this.width = width;
        this.scale = scale;
        this.walls = walls;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Random rand = new Random();
        float r = rand.nextFloat();
        float gg = rand.nextFloat();
        float b = rand.nextFloat();
        Color randomColor = new Color(r, gg, b);

        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, width * scale, height * scale);
        SnakeApplication snake = SnakeApplication.snakeApp;

        g.setColor(Color.MAGENTA);
        for (int i = 0; i < snake.snake.size(); i++) {
            g.fillRect(snake.snake.get(i).x * scale, snake.snake.get(i).y * scale, scale, scale);
        }
        g.fillRect(snake.head.x * scale, snake.head.y * scale, scale, scale);

        g.setColor(Color.BLUE);
        if (snake.eaten == 1) {
            cherry = randomColor;
            snake.colorGet = cherry;
            snake.eaten = 0;
        }
        g.setColor(cherry);
        g.fillRect(snake.food.x * scale, snake.food.y * scale, scale, scale);
        if (snake.over) {
            g.setColor(Color.BLUE);
            Font font = new Font("Verdana", Font.BOLD, 20);
            g.setFont(font);
            if (snake.over) {
                String over = "First snake Lost the game (press space to restart)";
                g.drawString(over, (g.getClipBounds().width / 2 - g.getFontMetrics().stringWidth(over) / 2), getHeight() / 2);
            }
        }
        g.setColor(Color.BLACK);
        walls.forEach(point -> g.fillRect(point.x * 10, point.y * 10, 10, 10));
    }
}

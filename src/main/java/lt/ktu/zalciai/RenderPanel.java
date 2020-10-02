/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.ktu.zalciai;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.Random;

public class RenderPanel extends JPanel {

    public Color cherry;

    @Override
    protected void paintComponent(Graphics g) {
        Random rand = new Random();
        float r = rand.nextFloat();
        float gg = rand.nextFloat();
        float b = rand.nextFloat();
        Color randomColor = new Color(r, gg, b);

        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, 800, 700);
        Snake snake = Snake.snake;

        g.setColor(Color.MAGENTA);
        for (int i = 0; i < snake.sParts.size(); i++) {
            g.fillRect(snake.sParts.get(i).x * Snake.SCALE, snake.sParts.get(i).y * Snake.SCALE,
                    Snake.SCALE, Snake.SCALE);
        }
        g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE,
                Snake.SCALE, Snake.SCALE);

        g.setColor(Color.BLUE);
        if (snake.eaten == 1) {
            cherry = randomColor;
            snake.colorGet = cherry;
            snake.eaten = 0;
        }
        g.setColor(cherry);
        g.fillRect(snake.cherry.x * Snake.SCALE, snake.cherry.y * Snake.SCALE,
                Snake.SCALE, Snake.SCALE);
        String string = "First snake ----- Score : " + snake.score + ", Time: " + snake.time / 20;
        g.setColor(Color.black);
        Font scores = new Font("Verdana", Font.BOLD, 10);
        g.setFont(scores);
        g.drawString(string, 5, 10);
        if (snake.over) {
            g.setColor(Color.BLUE);
            Font font = new Font("Verdana", Font.BOLD, 20);
            g.setFont(font);
            if (snake.over) {
                String over = "First snake Lost the game (press space to restart)";
                g.drawString(over, (getWidth() / 2 - over.length() - 200), getHeight() / 2);
            }
        }
    }

}

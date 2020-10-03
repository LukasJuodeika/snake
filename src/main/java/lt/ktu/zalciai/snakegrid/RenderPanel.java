package lt.ktu.zalciai.snakegrid;

import lt.ktu.zalciai.SnakeApplication;

import java.awt.*;
import javax.swing.JPanel;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class RenderPanel extends JPanel {

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
        //draw background
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, width * scale, height * scale);

        SnakeApplication snake = SnakeApplication.snakeApp;

        //draw snake
        drawColorPoints(g, Map.of("#FF00FF", snake.snake));

        //draw food
        drawColorPoints(g, Map.of("#FFF", Collections.singletonList(snake.food.getPoint())));

        //draw walls
        drawColorPoints(g, Map.of("#000", walls));
    }

    private void drawColorPoints(Graphics graphics, Map<String, Collection<Point>> colorPoints) {
        colorPoints.forEach((color, points) -> {
            graphics.setColor(Color.decode(color));
            drawPoints(graphics, points);
        });
    }

    private void drawPoints(Graphics graphics, Collection<Point> points) {
        points.forEach(point -> graphics.fillRect(
                point.x * scale,
                point.y * scale,
                scale,
                scale
        ));
    }
}

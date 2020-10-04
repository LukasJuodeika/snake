package lt.ktu.zalciai.snakegrid;

import java.awt.*;
import javax.swing.JPanel;
import java.util.Collection;
import java.util.Map;

public class SnakeGridView extends JPanel {

    private final int height;
    private final int width;
    private final int scale;
    private final SnakeGridContract.Controller controller;

    public SnakeGridView(
            int height,
            int width,
            int scale,
            SnakeGridContract.Controller controller
    ) {
        this.height = height;
        this.width = width;
        this.scale = scale;
        this.controller = controller;
    }

    @Override
    protected void paintComponent(Graphics g) {
        //draw background
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, width * scale, height * scale);

        //draw everything
        drawColorPoints(g, controller.generateDrawPoints());
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

package lt.ktu.zalciai.display;

import lt.ktu.zalciai.Constants;
import lt.ktu.zalciai.snakegrid.RenderPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.Set;

public class DisplayJPanel implements DisplayContract.View {

    private final JFrame jframe;
    private final RenderPanel renderPanel;
    private final Dimension dim;

    public DisplayJPanel(
            KeyListener keyListener,
            Set<Point> walls
    ) {
        renderPanel = new RenderPanel(Constants.SNAKE_GRID_HEIGHT, Constants.SNAKE_GRID_WIDTH, Constants.SNAKE_GRID_SCALE, walls);
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        jframe = new JFrame("Snake");
        jframe.setTitle("Žalčio žaidimas");
        jframe.setVisible(true);
        jframe.setSize(Constants.SNAKE_GRID_WIDTH * Constants.SNAKE_GRID_SCALE, Constants.SNAKE_GRID_HEIGHT * Constants.SNAKE_GRID_SCALE + 30);
        jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
        jframe.add(renderPanel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.addKeyListener(keyListener);
        jframe.setResizable(false);
    }

    @Override
    public void refresh() {
        renderPanel.repaint();
    }
}

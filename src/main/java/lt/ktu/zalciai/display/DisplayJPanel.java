package lt.ktu.zalciai.display;

import lt.ktu.zalciai.RenderPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class DisplayJPanel implements DisplayContract.View {

    private final JFrame jframe;
    private final RenderPanel renderPanel;
    private final Dimension dim;

    public DisplayJPanel(
            KeyListener keyListener
    ) {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        renderPanel = new RenderPanel();
        jframe = new JFrame("Snake");
        jframe.setTitle("Snake žaidimas");
        jframe.setVisible(true);
        jframe.setSize(805, 700);
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

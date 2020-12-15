package lt.ktu.zalciai.controls;

import java.awt.event.KeyEvent;

public class UserInputLog extends AbstractKeyListener {

    public UserInputLog() {
        super(null);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Unrecognised key pressed: " + e.getKeyCode());
    }
}

package lt.ktu.zalciai.controls;

import lt.ktu.zalciai.enums.ControlAction;
import lt.ktu.zalciai.enums.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserInputVIM implements KeyListener {

    private final InputActionListener inputActionListener;

    public UserInputVIM(InputActionListener inputActionListener) {
        this.inputActionListener = inputActionListener;
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        switch (i) {
            case KeyEvent.VK_K:
                inputActionListener.onDirectionAction(Direction.UP);
                break;
            case KeyEvent.VK_J:
                inputActionListener.onDirectionAction(Direction.DOWN);
                break;
            case KeyEvent.VK_L:
                inputActionListener.onDirectionAction(Direction.RIGHT);
                break;
            case KeyEvent.VK_H:
                inputActionListener.onDirectionAction(Direction.LEFT);
                break;
            case KeyEvent.VK_SPACE:
                inputActionListener.onControlAction(ControlAction.PAUSE);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}

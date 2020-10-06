package lt.ktu.zalciai.controls;

import lt.ktu.zalciai.enums.ControlAction;
import lt.ktu.zalciai.enums.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserInputWASD implements KeyListener {

    private final InputActionListener inputActionListener;

    public UserInputWASD(InputActionListener inputActionListener) {
        this.inputActionListener = inputActionListener;
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        switch (i) {
            case KeyEvent.VK_W:
                inputActionListener.onDirectionAction(Direction.UP);
                break;
            case KeyEvent.VK_S:
                inputActionListener.onDirectionAction(Direction.DOWN);
                break;
            case KeyEvent.VK_D:
                inputActionListener.onDirectionAction(Direction.RIGHT);
                break;
            case KeyEvent.VK_A:
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

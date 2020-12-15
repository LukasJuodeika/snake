package lt.ktu.zalciai.controls;

import lt.ktu.zalciai.enums.ControlAction;
import lt.ktu.zalciai.enums.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserInputNumPad extends AbstractKeyListener {

    private final InputActionListener inputActionListener;

    public UserInputNumPad(
            AbstractKeyListener abstractKeyListener,
            InputActionListener inputActionListener
    ) {
        super(abstractKeyListener);
        this.inputActionListener = inputActionListener;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        switch (i) {
            case KeyEvent.VK_KP_UP:
                inputActionListener.onDirectionAction(Direction.UP);
                break;
            case KeyEvent.VK_KP_DOWN:
                inputActionListener.onDirectionAction(Direction.DOWN);
                break;
            case KeyEvent.VK_KP_RIGHT:
                inputActionListener.onDirectionAction(Direction.RIGHT);
                break;
            case KeyEvent.VK_KP_LEFT:
                inputActionListener.onDirectionAction(Direction.LEFT);
                break;
            case KeyEvent.VK_SPACE:
                inputActionListener.onControlAction(ControlAction.PAUSE);
                break;
            default:
                nextListener.keyPressed(e);
        }
    }
}

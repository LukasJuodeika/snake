package lt.ktu.zalciai.controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class AbstractKeyListener implements KeyListener {

    protected AbstractKeyListener nextListener;

    AbstractKeyListener(AbstractKeyListener nextListener) {
        this.nextListener = nextListener;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static AbstractKeyListener createListenersChain(InputActionListener inputActionListener) {
        return new UserInputArrows(
                new UserInputWASD(
                        new UserInputNumPad(
                                new UserInputVIM(
                                        new UserInputLog(),
                                        inputActionListener
                                ),
                                inputActionListener
                        ),
                        inputActionListener
                ),
                inputActionListener
        );
    }

}

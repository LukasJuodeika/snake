package lt.ktu.zalciai.display;

import lt.ktu.zalciai.controls.AbstractKeyListener;
import lt.ktu.zalciai.controls.InputActionListener;
import lt.ktu.zalciai.controls.UserInputWASD;
import lt.ktu.zalciai.snakegrid.SnakeGridContract;

import java.awt.event.KeyEvent;

public class DisplayViewFactory {

    public DisplayContract.View createDisplay(
            InputActionListener inputActionListener,
            SnakeGridContract.Controller snakeGridController
    ) {
        return new DisplayJPanel(
                AbstractKeyListener.createListenersChain(inputActionListener),
                snakeGridController
        );
    }
}

package lt.ktu.zalciai.controls;


import lt.ktu.zalciai.enums.ControlAction;
import lt.ktu.zalciai.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.event.KeyEvent;

import static org.mockito.Mockito.*;

public class UserInputArrowsTest {
    @Mock
    public KeyEvent keyEvent;

    @Mock
    public InputActionListener inputActionListener;

    private UserInputArrows userInputArrows;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userInputArrows = new UserInputArrows(inputActionListener);
    }

    @Test
    public void pressedUp() {
        doReturn(KeyEvent.VK_UP).when(keyEvent).getKeyCode();

        userInputArrows.keyPressed(keyEvent);

        verify(inputActionListener).onDirectionAction(Direction.UP);
        verify(inputActionListener, never()).onDirectionAction(Direction.DOWN);
        verify(inputActionListener, never()).onDirectionAction(Direction.RIGHT);
        verify(inputActionListener, never()).onDirectionAction(Direction.LEFT);
        verify(inputActionListener, never()).onControlAction(ControlAction.PAUSE);
    }

    @Test
    public void pressedDown() {
        doReturn(KeyEvent.VK_DOWN).when(keyEvent).getKeyCode();

        userInputArrows.keyPressed(keyEvent);

        verify(inputActionListener, never()).onDirectionAction(Direction.UP);
        verify(inputActionListener).onDirectionAction(Direction.DOWN);
        verify(inputActionListener, never()).onDirectionAction(Direction.RIGHT);
        verify(inputActionListener, never()).onDirectionAction(Direction.LEFT);
        verify(inputActionListener, never()).onControlAction(ControlAction.PAUSE);
    }

    @Test
    public void pressedRight() {
        doReturn(KeyEvent.VK_RIGHT).when(keyEvent).getKeyCode();

        userInputArrows.keyPressed(keyEvent);

        verify(inputActionListener, never()).onDirectionAction(Direction.UP);
        verify(inputActionListener, never()).onDirectionAction(Direction.DOWN);
        verify(inputActionListener).onDirectionAction(Direction.RIGHT);
        verify(inputActionListener, never()).onDirectionAction(Direction.LEFT);
        verify(inputActionListener, never()).onControlAction(ControlAction.PAUSE);
    }

    @Test
    public void pressedLeft() {
        doReturn(KeyEvent.VK_LEFT).when(keyEvent).getKeyCode();

        userInputArrows.keyPressed(keyEvent);

        verify(inputActionListener, never()).onDirectionAction(Direction.UP);
        verify(inputActionListener, never()).onDirectionAction(Direction.DOWN);
        verify(inputActionListener, never()).onDirectionAction(Direction.RIGHT);
        verify(inputActionListener).onDirectionAction(Direction.LEFT);
        verify(inputActionListener, never()).onControlAction(ControlAction.PAUSE);
    }

    @Test
    public void pressedSpace() {
        doReturn(KeyEvent.VK_SPACE).when(keyEvent).getKeyCode();

        userInputArrows.keyPressed(keyEvent);

        verify(inputActionListener, never()).onDirectionAction(Direction.UP);
        verify(inputActionListener, never()).onDirectionAction(Direction.DOWN);
        verify(inputActionListener, never()).onDirectionAction(Direction.RIGHT);
        verify(inputActionListener, never()).onDirectionAction(Direction.LEFT);
        verify(inputActionListener).onControlAction(ControlAction.PAUSE);
    }

}
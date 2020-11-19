package lt.ktu.zalciai.controls;

import lt.ktu.zalciai.enums.ControlAction;
import lt.ktu.zalciai.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.event.KeyEvent;

import static org.mockito.Mockito.*;

public class UserInputWASDTest {

    @Mock
    public KeyEvent keyEvent;

    @Mock
    public InputActionListener inputActionListener;

    private UserInputWASD userInputWASD;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userInputWASD = new UserInputWASD(inputActionListener);
    }

    @Test
    public void pressedUp() {
        doReturn(KeyEvent.VK_W).when(keyEvent).getKeyCode();

        userInputWASD.keyPressed(keyEvent);

        verify(inputActionListener).onDirectionAction(Direction.UP);
        verify(inputActionListener, never()).onDirectionAction(Direction.DOWN);
        verify(inputActionListener, never()).onDirectionAction(Direction.RIGHT);
        verify(inputActionListener, never()).onDirectionAction(Direction.LEFT);
        verify(inputActionListener, never()).onControlAction(ControlAction.PAUSE);
    }

    @Test
    public void pressedDown() {
        doReturn(KeyEvent.VK_S).when(keyEvent).getKeyCode();

        userInputWASD.keyPressed(keyEvent);

        verify(inputActionListener, never()).onDirectionAction(Direction.UP);
        verify(inputActionListener).onDirectionAction(Direction.DOWN);
        verify(inputActionListener, never()).onDirectionAction(Direction.RIGHT);
        verify(inputActionListener, never()).onDirectionAction(Direction.LEFT);
        verify(inputActionListener, never()).onControlAction(ControlAction.PAUSE);
    }

    @Test
    public void pressedRight() {
        doReturn(KeyEvent.VK_D).when(keyEvent).getKeyCode();

        userInputWASD.keyPressed(keyEvent);

        verify(inputActionListener, never()).onDirectionAction(Direction.UP);
        verify(inputActionListener, never()).onDirectionAction(Direction.DOWN);
        verify(inputActionListener).onDirectionAction(Direction.RIGHT);
        verify(inputActionListener, never()).onDirectionAction(Direction.LEFT);
        verify(inputActionListener, never()).onControlAction(ControlAction.PAUSE);
    }

    @Test
    public void pressedLeft() {
        doReturn(KeyEvent.VK_A).when(keyEvent).getKeyCode();

        userInputWASD.keyPressed(keyEvent);

        verify(inputActionListener, never()).onDirectionAction(Direction.UP);
        verify(inputActionListener, never()).onDirectionAction(Direction.DOWN);
        verify(inputActionListener, never()).onDirectionAction(Direction.RIGHT);
        verify(inputActionListener).onDirectionAction(Direction.LEFT);
        verify(inputActionListener, never()).onControlAction(ControlAction.PAUSE);
    }

    @Test
    public void pressedSpace() {
        doReturn(KeyEvent.VK_SPACE).when(keyEvent).getKeyCode();

        userInputWASD.keyPressed(keyEvent);

        verify(inputActionListener, never()).onDirectionAction(Direction.UP);
        verify(inputActionListener, never()).onDirectionAction(Direction.DOWN);
        verify(inputActionListener, never()).onDirectionAction(Direction.RIGHT);
        verify(inputActionListener, never()).onDirectionAction(Direction.LEFT);
        verify(inputActionListener).onControlAction(ControlAction.PAUSE);

    }

}

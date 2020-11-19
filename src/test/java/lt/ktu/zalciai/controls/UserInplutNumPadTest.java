package lt.ktu.zalciai.controls;

import lt.ktu.zalciai.enums.ControlAction;
import lt.ktu.zalciai.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.awt.event.KeyEvent;
import static org.mockito.Mockito.*;


public class UserInplutNumPadTest {

    @Mock
    public KeyEvent keyEvent;

    @Mock
    public InputActionListener inputActionListener;

    private UserInputNumPad userInputNumPad;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userInputNumPad = new UserInputNumPad(inputActionListener);
    }

    @Test
    public void pressedUp() {
        doReturn(KeyEvent.VK_KP_UP).when(keyEvent).getKeyCode();

        userInputNumPad.keyPressed(keyEvent);

        verify(inputActionListener).onDirectionAction(Direction.UP);
        verify(inputActionListener, never()).onDirectionAction(Direction.DOWN);
        verify(inputActionListener, never()).onDirectionAction(Direction.RIGHT);
        verify(inputActionListener, never()).onDirectionAction(Direction.LEFT);
        verify(inputActionListener, never()).onControlAction(ControlAction.PAUSE);
    }

    @Test
    public void pressedDown() {
        doReturn(KeyEvent.VK_KP_DOWN).when(keyEvent).getKeyCode();

        userInputNumPad.keyPressed(keyEvent);

        verify(inputActionListener, never()).onDirectionAction(Direction.UP);
        verify(inputActionListener).onDirectionAction(Direction.DOWN);
        verify(inputActionListener, never()).onDirectionAction(Direction.RIGHT);
        verify(inputActionListener, never()).onDirectionAction(Direction.LEFT);
        verify(inputActionListener, never()).onControlAction(ControlAction.PAUSE);
    }

    @Test
    public void pressedRight() {
        doReturn(KeyEvent.VK_KP_RIGHT).when(keyEvent).getKeyCode();

        userInputNumPad.keyPressed(keyEvent);

        verify(inputActionListener, never()).onDirectionAction(Direction.UP);
        verify(inputActionListener, never()).onDirectionAction(Direction.DOWN);
        verify(inputActionListener).onDirectionAction(Direction.RIGHT);
        verify(inputActionListener, never()).onDirectionAction(Direction.LEFT);
        verify(inputActionListener, never()).onControlAction(ControlAction.PAUSE);
    }

    @Test
    public void pressedLeft() {
        doReturn(KeyEvent.VK_KP_LEFT).when(keyEvent).getKeyCode();

        userInputNumPad.keyPressed(keyEvent);

        verify(inputActionListener, never()).onDirectionAction(Direction.UP);
        verify(inputActionListener, never()).onDirectionAction(Direction.DOWN);
        verify(inputActionListener, never()).onDirectionAction(Direction.RIGHT);
        verify(inputActionListener).onDirectionAction(Direction.LEFT);
        verify(inputActionListener, never()).onControlAction(ControlAction.PAUSE);
    }

    @Test
    public void pressedSpace() {
        doReturn(KeyEvent.VK_SPACE).when(keyEvent).getKeyCode();

        userInputNumPad.keyPressed(keyEvent);

        verify(inputActionListener, never()).onDirectionAction(Direction.UP);
        verify(inputActionListener, never()).onDirectionAction(Direction.DOWN);
        verify(inputActionListener, never()).onDirectionAction(Direction.RIGHT);
        verify(inputActionListener, never()).onDirectionAction(Direction.LEFT);
        verify(inputActionListener).onControlAction(ControlAction.PAUSE);
    }
}

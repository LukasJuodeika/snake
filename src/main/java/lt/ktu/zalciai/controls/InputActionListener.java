package lt.ktu.zalciai.controls;

import lt.ktu.zalciai.enums.ControlAction;
import lt.ktu.zalciai.enums.Direction;

public interface InputActionListener {

    void onControlAction(ControlAction inputAction);

    void onDirectionAction(Direction direction);
}

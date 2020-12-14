package lt.ktu.zalciai.npc.state;

import org.junit.Test;

public class HardStateTest {

    @Test
    public void hardStateIterator() {
        var state = new HardState();
        for (var iterator = state.getIterator(); iterator.hasNext();) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void extremeStateIterator() {
        var state = new ExtremeState();
        for (var iterator = state.getIterator(); iterator.hasNext();) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void advancedStateIterator() {
        var state = new StateTest();
        for (var iterator = state.getIterator(); iterator.hasNext();) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void startState() {
        var state = new StartState();
        for (var iterator = state.getIterator(); iterator.hasNext();) {
            System.out.println(iterator.next());
        }
    }
}

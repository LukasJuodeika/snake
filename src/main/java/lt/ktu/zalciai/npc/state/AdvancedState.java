package lt.ktu.zalciai.npc.state;

import lt.ktu.zalciai.Aggregator;
import lt.ktu.zalciai.npc.*;

import java.awt.Point;
import java.util.*;

public class AdvancedState implements State, Aggregator<NPC> {

    private List<NPC> npcs = new ArrayList<>();

    public void stateAction(Context context, int score) {
        context.setState(score < 10 ? this : new HardState());
    }

    public AdvancedState() {
        SimpleWorm wallWorm = new SimpleWorm(10);
        wallWorm.move(new Point(20, 5));
        var comp = new CompositeNPC(wallWorm, 2, new Point(10, 0));
        npcs.add(comp);
    }

    @Override
    public Iterator<NPC> getIterator() {
        return new Iterator<>() {
            private int cursor = 0;

            public boolean hasNext() {
                return cursor != npcs.size();
            }

            public NPC next() {
                if (cursor >= npcs.size())
                    throw new NoSuchElementException();
                return npcs.get(cursor++);
            }
        };
    }

    public void tickAction() {
        npcs.forEach(NPC::performAction);
    }
}

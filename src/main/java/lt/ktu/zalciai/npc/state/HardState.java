package lt.ktu.zalciai.npc.state;

import lt.ktu.zalciai.npc.*;

import java.awt.Point;
import java.util.*;

public class HardState implements State {

    private Set<NPC> npcs = new HashSet<>();

    public void stateAction(Context context, int score) {
        context.setState(score < 15 ? this : new ExtremeState());
    }

    public HardState() {
        SimpleWorm worm1 = new SimpleWorm(3);
        worm1.move(new Point(20, 5));
        SimpleWorm worm2 = (SimpleWorm) worm1.copy();
        worm2.move(new Point(10, 0));
        SimpleWorm worm3 = (SimpleWorm) worm2.copy();
        worm3.move(new Point(10, 0));

        npcs.add(new FastNPC(worm1));
        npcs.add(new FastNPC(worm2));
        npcs.add(new FastNPC(worm3));

        var factory = CompositeNPCFactory.getInstance();
        npcs.add(factory.getNPC(10, 2));
    }

    @Override
    public Iterator<NPC> getIterator() {
        return new Iterator<>() {
            private NPC[] npcsArray = npcs.<NPC>toArray(new NPC[10]);
            private int cursor = 0;
            private int size = npcs.size();

            public boolean hasNext() {
                return cursor != size;
            }

            public NPC next() {
                if (cursor >= npcs.size())
                    throw new NoSuchElementException();
                return npcsArray[cursor++];
            }
        };
    }


    public void tickAction() {
        npcs.forEach(NPC::performAction);
    }
}

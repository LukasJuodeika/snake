package lt.ktu.zalciai.npc.state;

import lt.ktu.zalciai.npc.*;

import java.util.*;

public class StartState implements State {

    private List<NPC> npcs = new ArrayList<>();

    public void stateAction(Context context, int score) {
       context.setState(score < 5 ? this : new AdvancedState());
    }
    
    public StartState() {

    }

    public Collection<NPC> getNPCs() {
        return npcs;
    }

    public void tickAction() {

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
 }

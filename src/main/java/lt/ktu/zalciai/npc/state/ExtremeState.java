package lt.ktu.zalciai.npc.state;

import lt.ktu.zalciai.npc.*;
import lt.ktu.zalciai.npc.visitor.StateDisplayVisitor;

import java.awt.Point;
import java.util.*;

public class ExtremeState implements State {

    private Queue<NPC> npcs = new LinkedList<>();

    public void stateAction(Context context, int score) {
       context.setState(this);	
    }
    
    public ExtremeState() {
        SimpleWorm worm1 = new SimpleWorm(3);
        worm1.move(new Point(20, 5));
        SimpleWorm worm2 = (SimpleWorm) worm1.copy();
        worm2.move(new Point(10, 0));
        SimpleWorm worm3 = (SimpleWorm) worm2.copy();
        worm3.move(new Point(10, 0));
        SimpleWorm worm4 = (SimpleWorm) worm3.copy();
        worm4.move(new Point(10, 0));

        npcs.add(new RandomMoveNPC(new RandomStopNPC(new FastNPC(worm1))));
        npcs.add(new RandomMoveNPC(new RandomStopNPC(new FastNPC(worm2))));
        npcs.add(new RandomMoveNPC(new RandomStopNPC(new FastNPC(worm3))));
        npcs.add(new RandomMoveNPC(new RandomStopNPC(new FastNPC(worm4))));
    }

    @Override
    public Iterator<NPC> getIterator() {
        return new Iterator<>() {
            private Queue<NPC> npcsCopy = new LinkedList<>(npcs);

            public boolean hasNext() {
                return npcsCopy.peek() != null;
            }

            public NPC next() {
                return npcsCopy.poll();
            }
        };
    }

    @Override
    public String accept(StateDisplayVisitor visitor)  
    { 
        return visitor.visit(this); 
    } 

    public void tickAction() {
        npcs.forEach(NPC::performAction);
    }
}

package lt.ktu.zalciai.npc.state;

import lt.ktu.zalciai.npc.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HardState implements State {

    private List<NPC> npcs = new ArrayList<>();

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
    }

    public Collection<NPC> getNPCs() {
        return npcs;
    }

    public void tickAction() {
        npcs.forEach(NPC::performAction);
    }
 }

package lt.ktu.zalciai.npc.state;

import lt.ktu.zalciai.npc.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdvancedState implements State {

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

    public Collection<NPC> getNPCs() {
        return npcs;
    }

    public void tickAction() {
        npcs.forEach(NPC::performAction);
    }
 }

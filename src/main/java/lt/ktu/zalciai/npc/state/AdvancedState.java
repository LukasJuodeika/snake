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
        SimpleWorm wallWorm = new SimpleWorm(3);
        wallWorm.move(new Point(20, 5));

        npcs.add(wallWorm);
    }

    public Collection<NPC> getNPCs() {
        return npcs;
    }

    public void tickAction() {
        npcs.forEach(NPC::performAction);
    }
 }

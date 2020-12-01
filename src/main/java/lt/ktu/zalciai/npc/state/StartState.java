package lt.ktu.zalciai.npc.state;

import lt.ktu.zalciai.npc.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
 }

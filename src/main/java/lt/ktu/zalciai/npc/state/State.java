package lt.ktu.zalciai.npc.state;

import lt.ktu.zalciai.Aggregator;
import lt.ktu.zalciai.npc.*;
import lt.ktu.zalciai.npc.visitor.StateDisplayVisitor;

public interface State extends Aggregator<NPC> {
    void stateAction(Context context, int score);
    void tickAction();
    String accept(StateDisplayVisitor visitor);
}

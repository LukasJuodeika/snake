package lt.ktu.zalciai.npc.state;

import lt.ktu.zalciai.npc.*;
import java.util.Collection;

public interface State {
    public void stateAction(Context context, int score);
    public Collection<NPC> getNPCs();
    public void tickAction();
}

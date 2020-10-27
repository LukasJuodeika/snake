package lt.ktu.zalciai.npc;

import java.util.Collection;
import java.awt.*;

public abstract class NPCDecorator implements NPC {
    protected NPC npc;

    public NPCDecorator(NPC npc) {
        this.npc = npc;
    }

    @Override
    public Collection<Point> getBodyPoints() {
        return npc.getBodyPoints();
    }

    @Override
    public void performAction() {
        npc.performAction();
    }

    @Override
    public void move(Point delta) {
        npc.move(delta);
    }

    @Override
    public NPC copy() {
        return npc.copy();
    }
}

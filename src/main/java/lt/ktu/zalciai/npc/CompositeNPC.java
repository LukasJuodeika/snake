package lt.ktu.zalciai.npc;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class CompositeNPC implements NPC {
    private List<NPC> npcs = new ArrayList<>();

    public CompositeNPC(NPC npc, int count, Point offset) {
        for (int i = 0; i < count; i++) {
            var n = npc.copy();
            n.move(new Point(offset.x * i, offset.y * i));
            npcs.add(n);
        }
    }

    @Override
    public NPC copy() {
        return this;
    }

    @Override
    public void performAction() {
        npcs.forEach(NPC::performAction);
    }

    @Override
    public void move(Point delta) {
        npcs.forEach(o -> o.move(delta));
    }

    @Override
    public Collection<Point> getBodyPoints() {
        var points = new HashSet<Point>();
        npcs.forEach(o -> points.addAll(o.getBodyPoints()));;
        return points;
    }
    
}

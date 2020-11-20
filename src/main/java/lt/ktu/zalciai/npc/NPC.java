package lt.ktu.zalciai.npc;

import java.util.Collection;
import java.awt.*;

public interface NPC {

    NPC copy();
    
    void performAction();

    void move(Point delta);

    Collection<Point> getBodyPoints();
}

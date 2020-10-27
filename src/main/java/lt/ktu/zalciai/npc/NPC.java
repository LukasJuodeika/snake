package lt.ktu.zalciai.npc;

import java.util.Collection;
import java.awt.*;

public interface NPC {

    public NPC copy(); 
    
    public void performAction();

    public void move(Point delta);

    public Collection<Point> getBodyPoints();
}

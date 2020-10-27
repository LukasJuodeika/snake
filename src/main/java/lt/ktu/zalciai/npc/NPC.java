package lt.ktu.zalciai.npc;

import java.util.Collection;
import java.awt.*;

public abstract class NPC {

    public abstract NPC copy(); 
    
    public abstract void performAction();

    public abstract Collection<Point> getBodyPoints();
}

package lt.ktu.zalciai.npc;

import java.util.ArrayList;
import java.util.Collection;
import java.awt.*;

public class NonPlayerCharacters {

    private ArrayList<NPC> npcs;

    public NonPlayerCharacters() {
        npcs = new ArrayList<NPC>();
        
        WallWorm wallWorm = new WallWorm(3);
        wallWorm.move(new Point(20, 5));
        WallWorm anotherWallWorm = (WallWorm) wallWorm.copy();
        anotherWallWorm.move(new Point(10, 0));

        npcs.add(wallWorm);
        npcs.add(anotherWallWorm);
    }

    public Collection<NPC> getNPCs() {
        return npcs;
    }

    public void performAction() {
        npcs.stream().forEach(x -> x.performAction());
    }
}

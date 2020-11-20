package lt.ktu.zalciai.npc;

import java.util.ArrayList;
import java.util.Collection;
import java.awt.*;
import java.util.List;

public class NonPlayerCharacters {

    private List<NPC> npcs = new ArrayList<>();

    public NonPlayerCharacters() {
        SimpleWorm wallWorm = new SimpleWorm(3);
        wallWorm.move(new Point(20, 5));
        SimpleWorm anotherWallWorm = (SimpleWorm) wallWorm.copy();
        anotherWallWorm.move(new Point(10, 0));

        npcs.add(wallWorm);
        npcs.add(new RandomMoveNPC(new RandomStopNPC(new FastNPC(anotherWallWorm))));
    }

    public Collection<NPC> getNPCs() {
        return npcs;
    }

    public void performAction() {
        npcs.forEach(NPC::performAction);
    }
}

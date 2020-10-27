package lt.ktu.zalciai.npc;

import java.util.Random;
import java.awt.*;

public class RandomMoveNPC extends NPCDecorator {
    
    private final Random random;

    public RandomMoveNPC(NPC npc) {
        super(npc);
        random = new Random();
    }

    public void performAction() {
        super.performAction();
        if (random.nextInt(10) > 2) {
            super.move(new Point(random.nextInt(2), random.nextInt(2)));
        }
    }
}

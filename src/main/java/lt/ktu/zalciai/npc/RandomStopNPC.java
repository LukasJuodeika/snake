package lt.ktu.zalciai.npc;

import java.util.Random;

public class RandomStopNPC extends NPCDecorator {

    private final Random random;
    
    public RandomStopNPC(NPC npc) {
        super(npc);
        random = new Random();
    }

    public void performAction() {
        if (random.nextInt(10) > 2) {
            super.performAction();
        }
    }
}


package lt.ktu.zalciai.npc;

public class FastNPC extends NPCDecorator {
    
    public FastNPC(NPC npc) {
        super(npc);
    }

    public void performAction() {
        super.performAction();
        super.performAction();
    }
}

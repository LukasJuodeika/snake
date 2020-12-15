package lt.ktu.zalciai.npc;

import java.awt.Point;
import java.io.Console;
import java.util.HashMap;


public class CompositeNPCFactory {
    public static CompositeNPCFactory compositeNPCFactory;

    private static HashMap<Integer, NPC> shared;

    public static synchronized CompositeNPCFactory getInstance() {
        if (compositeNPCFactory == null) {
            compositeNPCFactory = new CompositeNPCFactory();
        }
        return compositeNPCFactory;
    }

    public CompositeNPCFactory() {
        shared  = new HashMap<>();
    }

    public NPC getNPC(int size, int count) {
        int code = (size << 16) | (count & 0xffff);
        if (shared.containsKey(code)) {
            System.out.println("Reused CompositeNPC");
            return shared.get(code);
        } else {
            System.out.println("Created CompositeNPC");
            SimpleWorm wallWorm = new SimpleWorm(size);
            wallWorm.move(new Point(20, 5));
            var comp = new CompositeNPC(wallWorm, count, new Point(10, 0));
            shared.put(code, comp);
            return comp;
        }
    }
}

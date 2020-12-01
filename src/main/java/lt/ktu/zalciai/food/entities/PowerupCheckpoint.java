package lt.ktu.zalciai.food.entities;

import java.awt.*;

public class PowerupCheckpoint extends Food {
    public PowerupCheckpoint(Point point) {
        super(point);
    }

    @Override
    public int getScore() {
        return 1;
    }

    @Override
    public String getColorHex() {
        return "#ffffff";
    }
}

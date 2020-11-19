package lt.ktu.zalciai.food.entities;
import lt.ktu.zalciai.collision.CollisionStrategy;


import java.awt.*;

public class PowerupWall extends Food {

    public PowerupWall(Point point) {
        super(point);
        this.strategy = CollisionStrategy.ignoreWall();
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public String getColorHex() {
        return "#00AA00";
    }
}

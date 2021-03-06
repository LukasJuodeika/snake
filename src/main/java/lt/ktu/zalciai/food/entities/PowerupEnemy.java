package lt.ktu.zalciai.food.entities;
import lt.ktu.zalciai.collision.CollisionStrategy;


import java.awt.*;

public class PowerupEnemy extends Food {

    public PowerupEnemy(Point point) {
        super(point);
        this.strategy = CollisionStrategy.ignoreEnemy();
    }

    @Override
    public int getScore() {
        return 2;
    }

    @Override
    public String getColorHex() {
        return "#0000AA";
    }
}

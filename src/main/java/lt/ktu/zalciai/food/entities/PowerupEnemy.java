package lt.ktu.zalciai.food.entities;
import lt.ktu.zalciai.collision.CollisionStrategy;


import java.awt.*;

public class PowerupEnemy extends Food {

    public PowerupEnemy(Point point) {
        super(point);
        this.strategy = CollisionStrategy.ignoreEnemyStrategy();
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public String getColorHex() {
        return "#0000AA";
    }
}

package lt.ktu.zalciai.food.entities;

import lt.ktu.zalciai.collision.CollisionStrategy;


import java.awt.*;

public abstract class Food {

    protected Point point;
    protected CollisionStrategy strategy;

    public Food(Point point) {
        this.point = point;
        strategy = CollisionStrategy.normalStrategy();
    }

    public Point getPoint() {
        return point;
    }

    public int getScore() {
        return 5;
    }

    public CollisionStrategy getStrategy() {
        return strategy;
    }

    public String getColorHex() {
        return "#7CFC00";
    }
}

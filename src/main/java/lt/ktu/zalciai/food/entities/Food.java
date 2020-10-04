package lt.ktu.zalciai.food.entities;

import java.awt.*;

public abstract class Food {

    protected Point point;

    public Food(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public int getScore() {
        return 5;
    }

    public String getColorHex() {
        return "#7CFC00";
    }
}

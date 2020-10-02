package lt.ktu.zalciai.food;

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
}

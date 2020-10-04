package lt.ktu.zalciai.food;

import java.awt.*;

public class FoodL2 extends Food {
    public FoodL2(Point point) {
        super(point);
    }

    @Override
    public int getScore() {
        return 10;
    }

    @Override
    public String getColorHex() {
        return "#DC143C";
    }
}

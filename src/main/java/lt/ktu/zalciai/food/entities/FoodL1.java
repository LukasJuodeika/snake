package lt.ktu.zalciai.food.entities;

import lt.ktu.zalciai.food.entities.Food;

import java.awt.*;

public class FoodL1 extends Food {
    public FoodL1(Point point) {
        super(point);
    }

    @Override
    public int getScore() {
        return 8;
    }

    @Override
    public String getColorHex() {
        return "#4682B4";
    }
}

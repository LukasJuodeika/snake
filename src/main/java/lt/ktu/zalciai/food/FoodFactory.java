package lt.ktu.zalciai.food;

import lt.ktu.zalciai.Constants;

import java.awt.*;
import java.util.Random;

public final class FoodFactory {

    private static FoodFactory foodFactory;

    private final Random random;

    public static synchronized FoodFactory getInstance() {
        if (foodFactory == null) {
            foodFactory = new FoodFactory();
        }
        return foodFactory;
    }

    private FoodFactory() {
        random = new Random();
    }

    public Food randomFood() {
        return new FoodBasic(
                new Point(
                        random.nextInt(Constants.SNAKE_GRID_WIDTH - 1),
                        random.nextInt(Constants.SNAKE_GRID_HEIGHT - 1)
                )
        );

    }

}

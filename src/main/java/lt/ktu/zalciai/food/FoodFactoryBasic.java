package lt.ktu.zalciai.food;

import lt.ktu.zalciai.Constants;
import lt.ktu.zalciai.food.entities.Food;
import lt.ktu.zalciai.food.entities.FoodBasic;

import java.awt.*;
import java.util.Random;

public class FoodFactoryBasic implements FoodFactory {

    private static FoodFactoryBasic foodFactory;

    private final Random random;

    public static synchronized FoodFactoryBasic getInstance() {
        if (foodFactory == null) {
            foodFactory = new FoodFactoryBasic();
        }
        return foodFactory;
    }

    private FoodFactoryBasic() {
        random = new Random();
    }

    @Override
    public Food generateFood() {
        return new FoodBasic(
                new Point(
                        random.nextInt(Constants.SNAKE_GRID_WIDTH - 1),
                        random.nextInt(Constants.SNAKE_GRID_HEIGHT - 1)
                )
        );
    }
}

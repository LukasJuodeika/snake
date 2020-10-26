package lt.ktu.zalciai.food;

import lt.ktu.zalciai.Constants;
import lt.ktu.zalciai.food.entities.Food;
import lt.ktu.zalciai.food.entities.FoodBasic;
import lt.ktu.zalciai.food.entities.Powerup;
import lt.ktu.zalciai.food.entities.FoodL1;

import java.awt.*;
import java.util.Random;

public class FoodFactoryModern implements FoodFactory {

    private static FoodFactoryModern foodFactory;

    private final Random random;

    public static synchronized FoodFactoryModern getInstance() {
        if (foodFactory == null) {
            foodFactory = new FoodFactoryModern();
        }
        return foodFactory;
    }

    private FoodFactoryModern() {
        random = new Random();
    }

    @Override
    public Food generateFood() {
        int classNumber = random.nextInt(3);
        Food randomFood;
        Point randomPoint = new Point(
                random.nextInt(Constants.SNAKE_GRID_WIDTH - 1),
                random.nextInt(Constants.SNAKE_GRID_HEIGHT - 1)
        );

        switch (classNumber) {
            case 0:
                randomFood = new FoodL1(randomPoint);
                break;
            case 1:
                randomFood = new Powerup(randomPoint);
                break;
            default:
                randomFood = new FoodBasic(randomPoint);
                break;
        }
        return randomFood;
    }
}

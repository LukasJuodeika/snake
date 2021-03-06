package lt.ktu.zalciai.food;

import lt.ktu.zalciai.Constants;
import lt.ktu.zalciai.food.entities.Food;
import lt.ktu.zalciai.food.entities.FoodBasic;
import lt.ktu.zalciai.food.entities.FoodL1;
import lt.ktu.zalciai.food.entities.FoodL2;
import lt.ktu.zalciai.snakemap.SnakeMap;

import java.awt.*;
import java.util.Random;

public final class FoodFactoryClassic implements FoodFactory {

    private static FoodFactoryClassic foodFactory;

    private final Random random;

    public static synchronized FoodFactoryClassic getInstance() {
        if (foodFactory == null) {
            foodFactory = new FoodFactoryClassic();
        }
        return foodFactory;
    }

    private FoodFactoryClassic() {
        random = new Random();
    }

    @Override
    public Food generateFood(SnakeMap snakeMap) {
        int classNumber = random.nextInt(3);
        Food randomFood;
        Point randomPoint;
        do {
            randomPoint = new Point(
                    random.nextInt(Constants.SNAKE_GRID_WIDTH - 1),
                    random.nextInt(Constants.SNAKE_GRID_HEIGHT - 1)
            );
        } while (snakeMap.colides(randomPoint));

        switch (classNumber) {
            case 0:
                randomFood = new FoodL1(randomPoint);
                break;
            case 1:
                randomFood = new FoodL2(randomPoint);
                break;
            default:
                randomFood = new FoodBasic(randomPoint);
                break;
        }
        return randomFood;
    }

}

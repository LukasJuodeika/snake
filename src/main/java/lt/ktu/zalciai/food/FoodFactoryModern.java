package lt.ktu.zalciai.food;

import lt.ktu.zalciai.Constants;
import lt.ktu.zalciai.food.entities.Food;
import lt.ktu.zalciai.food.entities.PowerupEnemy;
import lt.ktu.zalciai.food.entities.PowerupWall;
import lt.ktu.zalciai.food.entities.PowerupCheckpoint;
import lt.ktu.zalciai.snakemap.SnakeMap;

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
                randomFood = new PowerupWall(randomPoint);
                break;
            case 1:
                randomFood = new PowerupEnemy(randomPoint);
                break;
            default:
                randomFood = new PowerupCheckpoint(randomPoint);
                break;
        }
        return randomFood;
    }
}

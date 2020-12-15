package lt.ktu.zalciai.food;

import lt.ktu.zalciai.food.entities.Food;
import lt.ktu.zalciai.snakemap.SnakeMap;

public interface FoodFactory {
    Food generateFood(SnakeMap snakeMap);
}

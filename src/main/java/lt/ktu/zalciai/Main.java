package lt.ktu.zalciai;

import lt.ktu.zalciai.food.FoodFactory;
import lt.ktu.zalciai.food.FoodFactoryRandom;

public class Main {
    public static void main(String[] args) {
        FoodFactory foodFactory = FoodFactoryRandom.getInstance();
        SnakeApplication snakeApplication = new SnakeApplication(foodFactory);
        snakeApplication.startGame();
    }
}

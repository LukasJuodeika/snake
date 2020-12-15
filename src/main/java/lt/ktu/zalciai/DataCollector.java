package lt.ktu.zalciai;

import lt.ktu.zalciai.food.entities.Food;
import lt.ktu.zalciai.ws.SnakeClient;

public class DataCollector {
    public static String showData(SnakeClient client, Snake snake, Food food)
    {
        return "Snake with color code: " + client.getColor() + " has " + snake.GetScore() + " points, next spawned food is " + food.toString();
    }
}

package lt.ktu.zalciai.ws;

import lt.ktu.zalciai.food.FoodFactory;
import lt.ktu.zalciai.food.FoodFactoryRandom;
import lt.ktu.zalciai.snakemap.SnakeMapFactory;
import lt.ktu.zalciai.snakemap.SnakeMap;
import lt.ktu.zalciai.*;
import lt.ktu.zalciai.ws.logging.LoggingClient;

import java.util.*;
import java.awt.Point;

import java.net.URI;
import java.net.URISyntaxException;

public class SnakeClient extends LoggingClient {

	public Map<String, Collection<Point>> remoteColorPoints = Collections.emptyMap();

	public SnakeClient(URI serverURI) {
		super(serverURI);
	}

	@Override
	public void onMessage(String message) {
		remoteColorPoints = decodePoints(message);
	}

	public void sendPoints(Map<String, Collection<Point>> points) {
		send(encodePoints(points));
	}

	public static void main(String[] args) throws URISyntaxException {
		SnakeClient client = new SnakeClient(new URI("ws://localhost:8887"));
		client.connect();

		FoodFactory foodFactory = FoodFactoryRandom.getInstance();
        SnakeMap snakeMap = SnakeMapFactory.createCustomSidesMap(Constants.SNAKE_GRID_WIDTH, Constants.SNAKE_GRID_HEIGHT);
        SnakeApplication snakeApplication = new SnakeApplication(foodFactory, snakeMap, client);
        snakeApplication.startGame();
	}
}
package lt.ktu.zalciai.ws;

import lt.ktu.zalciai.display.DisplayViewFactory;
import lt.ktu.zalciai.exceptions.CollisionException;
import lt.ktu.zalciai.food.FoodFactory;
import lt.ktu.zalciai.food.FoodFactoryClassic;
import lt.ktu.zalciai.food.FoodFactoryModern;
import lt.ktu.zalciai.snakemap.SnakeMapFactory;
import lt.ktu.zalciai.snakemap.SnakeMap;
import lt.ktu.zalciai.*;
import lt.ktu.zalciai.ws.logging.LoggingClient;

import java.util.*;
import java.awt.Point;

import java.net.URI;
import java.net.URISyntaxException;

public class SnakeClient extends LoggingClient {

	private Random obj = new Random();
	private int rand_num = obj.nextInt(0xffffff + 1);
	// format it as hexadecimal string and print
	private String colorCode = String.format("#%06x", rand_num);

	public String getColor()
	{
		return colorCode;
	}

	public Map<String, Set<Point>> remoteColorPoints = Collections.emptyMap();

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

	public static void main(String[] args) throws URISyntaxException, CollisionException {
		//Random color generator
		Random obj = new Random();
		int rand_num = obj.nextInt(0xffffff + 1);
		// format it as hexadecimal string and print
		String colorCode = String.format("#%06x", rand_num);


		var client = new SnakeClient(new URI("ws://localhost:8887"));
		client.connect();

		var foodFactory = FoodFactoryModern.getInstance();
        var snakeMap = SnakeMapFactory.createCustomSidesMap(Constants.SNAKE_GRID_WIDTH, Constants.SNAKE_GRID_HEIGHT);
        var snakeApplication = new SnakeApplication(
                foodFactory,
                snakeMap,
                client,
                new DisplayViewFactory(),
                new SnakeFat()
        );
        snakeApplication.SnakeColor = client.getColor();
        snakeApplication.startGame();

	}
}
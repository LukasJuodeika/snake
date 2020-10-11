package lt.ktu.zalciai.ws;

import lt.ktu.zalciai.food.FoodFactory;
import lt.ktu.zalciai.food.FoodFactoryRandom;
import lt.ktu.zalciai.snakemap.SnakeMapFactory;
import lt.ktu.zalciai.snakemap.SnakeMap;
import lt.ktu.zalciai.*;

import java.util.*;
import java.awt.Point;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

public class EmptyClient extends WebSocketClient {

	public Map<String, Collection<Point>> remoteColorPoints = Collections.emptyMap();

	public EmptyClient(URI serverUri, Draft draft) {
		super(serverUri, draft);
	}

	public EmptyClient(URI serverURI) {
		super(serverURI);
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		send("Hello, it is me. Mario :)");
		System.out.println("new connection opened");
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		System.out.println("closed with exit code " + code + " additional info: " + reason);
	}

	@Override
	public void onMessage(String message) {
		// System.out.println("received message: " + message);
		remoteColorPoints = decodePoints(message);
	}

	@Override
	public void onMessage(ByteBuffer message) {
		System.out.println("received ByteBuffer");
	}

	@Override
	public void onError(Exception ex) {
		System.err.println("an error occurred:" + ex);
	}

	public void sendPoints(Map<String, Collection<Point>> points) {
		send(encodePoints(points));
	}

	private String encodePoints(Map<String, Collection<Point>> points) {
		String json = new Gson().toJson(points);
		return json;
	}

	private Map<String, Collection<Point>> decodePoints(String json) {
		Map<String, Collection<Point>> points = new Gson().fromJson(json
			, new TypeToken<Map<String, Collection<Point>>>() {}.getType());
		return points;
	}

	public static void main(String[] args) throws URISyntaxException {		
		EmptyClient client = new EmptyClient(new URI("ws://localhost:8887"));
		client.connect();

		FoodFactory foodFactory = FoodFactoryRandom.getInstance();
        SnakeMap snakeMap = SnakeMapFactory.createCustomSidesMap(Constants.SNAKE_GRID_WIDTH, Constants.SNAKE_GRID_HEIGHT);
        SnakeApplication snakeApplication = new SnakeApplication(foodFactory, snakeMap, client);
        snakeApplication.startGame();
	}
}
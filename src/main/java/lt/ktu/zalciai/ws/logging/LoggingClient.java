package lt.ktu.zalciai.ws.logging;

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
import java.nio.ByteBuffer;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class LoggingClient extends WebSocketClient {

    public LoggingClient(URI serverURI) {
        super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("new connection opened");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("new message: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("closed with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onMessage(ByteBuffer message) {
        System.out.println("received ByteBuffer");
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("an error occurred:" + ex);
    }

    protected String encodePoints(Map<String, Collection<Point>> points) {
        String json = new Gson().toJson(points);
        return json;
    }

    protected Map<String, Set<Point>> decodePoints(String json) {
        Map<String, Set<Point>> points = new Gson().fromJson(json, new TypeToken<Map<String, HashSet<Point>>>() {}.getType());
        return points;
    }
}
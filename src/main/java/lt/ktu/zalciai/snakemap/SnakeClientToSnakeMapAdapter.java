package lt.ktu.zalciai.snakemap;

import java.util.HashSet;
import java.util.Set;
import java.awt.*;

import lt.ktu.zalciai.ws.SnakeClient;

public class SnakeClientToSnakeMapAdapter implements SnakeMap {
    
    private final SnakeClient snakeClient;

    public SnakeClientToSnakeMapAdapter(SnakeClient snakeClient) {
        this.snakeClient = snakeClient;
    }
    
    @Override
    public boolean colides(Point point) {
        return snakeClient.remoteColorPoints.entrySet().stream().anyMatch(x -> x.getValue().contains(point));
    }

    public Set<Point> getWalls() {
        Set<Point> points = new HashSet<Point>();
        snakeClient.remoteColorPoints.entrySet().stream().forEach(x -> points.addAll(x.getValue()));
        return points;
    }
}

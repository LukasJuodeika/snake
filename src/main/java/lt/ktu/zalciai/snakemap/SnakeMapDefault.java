package lt.ktu.zalciai.snakemap;

import lt.ktu.zalciai.snakemap.walls.Walls;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class SnakeMapDefault implements SnakeMap {

    private final Set<Point> walls = new HashSet<>();

    public SnakeMapDefault(int mapWidth, int mapHeight) {
        walls.addAll(Walls.generateBorders(mapWidth, mapHeight));
    }

    @Override
    public boolean colides(Point point) {
        return walls.contains(point);
    }

    public Set<Point> getWalls() {
        return walls;
    }
}

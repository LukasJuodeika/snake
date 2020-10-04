package lt.ktu.zalciai.snakemap;


import lt.ktu.zalciai.snakemap.walls.Walls;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class SnakeMapCustomSides implements SnakeMap {

    private final int RECT_TO_MAP_RATIO = 6;

    private final Set<Point> walls = new HashSet<>();

    public SnakeMapCustomSides(int mapWidth, int mapHeight) {
        walls.addAll(Walls.generateBorders(mapWidth, mapHeight));
        walls.addAll(Walls.generateFillRect(
                new Point(0, 0),
                new Point(mapWidth / RECT_TO_MAP_RATIO, mapHeight / RECT_TO_MAP_RATIO)
        ));
        walls.addAll(Walls.generateFillRect(
                new Point(mapWidth / RECT_TO_MAP_RATIO * (RECT_TO_MAP_RATIO - 1), 0),
                new Point(mapWidth, mapHeight / RECT_TO_MAP_RATIO)
        ));
        walls.addAll(Walls.generateFillRect(
                new Point(0, mapHeight / RECT_TO_MAP_RATIO * (RECT_TO_MAP_RATIO - 1)),
                new Point(mapWidth / RECT_TO_MAP_RATIO, mapHeight)
        ));
        walls.addAll(Walls.generateFillRect(
                new Point(mapWidth / RECT_TO_MAP_RATIO * (RECT_TO_MAP_RATIO - 1), mapHeight / RECT_TO_MAP_RATIO * (RECT_TO_MAP_RATIO - 1)),
                new Point(mapWidth, mapHeight)
        ));
    }

    @Override
    public boolean colides(Point point) {
        return walls.contains(point);
    }

    public Set<Point> getWalls() {
        return walls;
    }
}

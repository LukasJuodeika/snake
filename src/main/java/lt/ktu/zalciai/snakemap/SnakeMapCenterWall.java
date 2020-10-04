package lt.ktu.zalciai.snakemap;


import lt.ktu.zalciai.snakemap.walls.Walls;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class SnakeMapCenterWall implements SnakeMap {

    private final Set<Point> walls = new HashSet<>();

    public SnakeMapCenterWall(int mapWidth, int mapHeight) {
        walls.addAll(Walls.generateBorders(mapWidth, mapHeight));
        walls.addAll(Walls.generateFillRect(
                new Point(mapWidth/2 - 5, mapHeight/2 -5),
                new Point(mapWidth/2 + 5, mapHeight/2 + 5)
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

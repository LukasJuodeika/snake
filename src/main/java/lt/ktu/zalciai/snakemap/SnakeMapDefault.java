package lt.ktu.zalciai.snakemap;

import lt.ktu.zalciai.Constants;
import lt.ktu.zalciai.snakemap.walls.Walls;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class SnakeMapDefault implements SnakeMap {
    private final Set<Point> walls;

    private SnakeMapDefault(Set<Point> walls) {
        this.walls = walls;
    }

    @Override
    public boolean colides(Point point) {
        return walls.contains(point);
    }

    public Set<Point> getWalls() {
        return walls;
    }

    public static class Builder {
        private Set<Point> walls = new HashSet<>();
        private int mapWidth;
        private int mapHeight;

        public Builder() {
            mapHeight = Constants.SNAKE_GRID_HEIGHT;
            mapWidth = Constants.SNAKE_GRID_WIDTH;
        }

        public Builder setWidth(int width) {
            this.mapWidth = width;
            return this;
        }

        public Builder setHeight(int height) {
            this.mapHeight = height;
            return this;
        }

        public SnakeMapDefault build() {
            walls.addAll(Walls.generateBorders(mapWidth, mapHeight));
            return new SnakeMapDefault(walls);
        }
    }
}

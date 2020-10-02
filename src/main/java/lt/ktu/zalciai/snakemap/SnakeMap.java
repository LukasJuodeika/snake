package lt.ktu.zalciai.snakemap;

import java.awt.*;
import java.util.Set;

public interface SnakeMap {
    boolean colides(Point point);

    Set<Point> getWalls();
}

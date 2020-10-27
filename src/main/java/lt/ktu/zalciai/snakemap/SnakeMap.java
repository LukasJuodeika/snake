package lt.ktu.zalciai.snakemap;

import java.awt.*;
import java.util.Collection;

public interface SnakeMap {
    boolean colides(Point point);

    Collection<Point> getWalls();
}

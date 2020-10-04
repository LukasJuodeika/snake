package lt.ktu.zalciai.snakegrid;

import java.awt.*;
import java.util.Collection;
import java.util.Map;

public interface SnakeGridContract {

    interface View {

    }

    interface Controller {

        Map<String, Collection<Point>> generateDrawPoints();
    }

}

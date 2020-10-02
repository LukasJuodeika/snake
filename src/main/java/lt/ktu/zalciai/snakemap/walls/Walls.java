package lt.ktu.zalciai.snakemap.walls;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Walls {

    public static Set<Point> generateBorders(int width, int height) {
        HashSet<Point> hashSet = new HashSet<>();
        Set<Point> top = Stream.iterate(0, n -> ++n)
                .limit(width)
                .map(it -> new Point(it, 0))
                .collect(Collectors.toSet());

        Set<Point> bottom = Stream.iterate(0, n -> ++n)
                .limit(width)
                .map(it -> new Point(it, height))
                .collect(Collectors.toSet());

        Set<Point> left = Stream.iterate(0, n -> ++n)
                .limit(height)
                .map(it -> new Point(0, it))
                .collect(Collectors.toSet());

        Set<Point> right = Stream.iterate(0, n -> ++n)
                .limit(height)
                .map(it -> new Point(width - 1, it))
                .collect(Collectors.toSet());
        hashSet.addAll(top);
        hashSet.addAll(bottom);
        hashSet.addAll(left);
        hashSet.addAll(right);
        return hashSet;
    }
}

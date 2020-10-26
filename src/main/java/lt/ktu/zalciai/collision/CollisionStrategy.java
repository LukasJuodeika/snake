package lt.ktu.zalciai.collision;

import java.awt.Point;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public interface CollisionStrategy {
    boolean colides(Point point, Predicate<Point> mapCollision, Map<String, Set<Point>> remotePoints);
  
    static CollisionStrategy normalStrategy() {
        return (point, mapCollision, enemyPoints) -> 
            mapCollision.test(point) || enemyPoints.entrySet().stream().anyMatch(x -> x.getValue().contains(point));
    }
  
    static CollisionStrategy invulnerableStrategy() {
        return (point, mapCollision, enemyPoints) -> mapCollision.test(point);
    }
}
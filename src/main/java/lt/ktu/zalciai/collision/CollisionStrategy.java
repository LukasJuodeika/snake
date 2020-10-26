package lt.ktu.zalciai.collision;

import java.awt.Point;
import java.util.function.Predicate;

public interface CollisionStrategy {
    boolean colides(Point point, Predicate<Point> mapCollision, Predicate<Point> remoteCollision);
  
    static CollisionStrategy normalStrategy() {
        return (point, mapCollision, remoteCollision) -> 
            mapCollision.test(point) || remoteCollision.test(point);
    }
  
    static CollisionStrategy invulnerableStrategy() {
        return (point, mapCollision, remoteCollision) -> mapCollision.test(point);
    }
}
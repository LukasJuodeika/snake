package lt.ktu.zalciai.collision;

import java.awt.Point;
import java.util.function.Predicate;

public interface CollisionStrategy {
    boolean colides(Point point, Predicate<Point> mapCollision, Predicate<Point> remoteCollision);
  
    static CollisionStrategy normal() {
        return (point, mapCollision, remoteCollision) -> mapCollision.test(point) || remoteCollision.test(point);
    }
  
    static CollisionStrategy ignoreEnemy() {
        return (point, mapCollision, remoteCollision) -> mapCollision.test(point);
    }

    static CollisionStrategy ignoreWall() {
        return (point, mapCollision, remoteCollision) -> remoteCollision.test(point);
    }

    static CollisionStrategy ignoreAll() {
        return (point, mapCollision, remoteCollision) -> false;
    }
}
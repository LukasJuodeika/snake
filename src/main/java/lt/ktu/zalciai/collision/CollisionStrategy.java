package lt.ktu.zalciai.collision;

import java.awt.Point;
import java.util.function.Predicate;

public interface CollisionStrategy {
    boolean colides(Point point, Predicate<Point> mapCollision, Predicate<Point> remoteCollision);
  
    static CollisionStrategy normalStrategy() {
        return (point, mapCollision, remoteCollision) -> mapCollision.test(point) || remoteCollision.test(point);
    }
  
    static CollisionStrategy ignoreEnemyStrategy() {
        return (point, mapCollision, remoteCollision) -> mapCollision.test(point);
    }

    static CollisionStrategy ignoreWallStrategy() {
        return (point, mapCollision, remoteCollision) -> remoteCollision.test(point);
    }

    static CollisionStrategy ignoreAllStrategy() {
        return (point, mapCollision, remoteCollision) -> false;
    }
}
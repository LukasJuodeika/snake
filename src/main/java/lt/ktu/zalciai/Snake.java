package lt.ktu.zalciai;

import lt.ktu.zalciai.enums.Direction;
import lt.ktu.zalciai.exceptions.CollisionException;
import lt.ktu.zalciai.food.entities.Food;
import lt.ktu.zalciai.collision.CollisionStrategy;

import java.util.Map;
import java.awt.*;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.Predicate;

public abstract class Snake {
    protected int startX, startY;
    protected CollisionStrategy collisionStrategy;

    abstract void start(int startX, int startY);
    abstract Point move(Direction direction, Predicate<Point> collides, Predicate<Point> collidesRemote) throws CollisionException;
    abstract void eatFood(Food food);
    abstract LinkedList<Point> getPoints();
    abstract void setCollisionStrategy(CollisionStrategy collisionStrategy);
    abstract int GetScore();

    public final void play() {
        start(startX, startY);
        getPoints();
        setCollisionStrategy(collisionStrategy);
        GetScore();
    }

    public Point createNextHead(Direction direction, Point head) {
        Point next;
        switch (direction) {
            case UP:
                next = new Point(head.x, head.y - 1);
                break;
            case DOWN:
                next = new Point(head.x, head.y + 1);
                break;
            case LEFT:
                next = new Point(head.x - 1, head.y);
                break;
            default: //RIGHT
                next = new Point(head.x + 1, head.y);
                break;
        }
        return next;
    }
}

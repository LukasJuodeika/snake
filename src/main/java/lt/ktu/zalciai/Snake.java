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
    int startX, startY;
    Direction direction; Predicate<Point> collides; Predicate<Point> collidesRemote;
    Food food;
    Point head;
    CollisionStrategy collisionStrategy;
    private Object CollisionException;

    abstract void start(int startX, int startY);
    abstract Point move(Direction direction, Predicate<Point> collides, Predicate<Point> collidesRemote) throws CollisionException;
    abstract void eatFood(Food food);
    abstract Point createNextHead(Direction direction, Point head);
    abstract LinkedList<Point> getPoints();
    abstract void setCollisionStrategy(CollisionStrategy collisionStrategy);
    abstract int GetScore();

    public final void play() throws CollisionException {
        start(startX, startY);
//        move(direction, collides, collidesRemote);
//        eatFood(food);
//        createNextHead(direction, head);
        getPoints();
        setCollisionStrategy(collisionStrategy);
        GetScore();
    }
}

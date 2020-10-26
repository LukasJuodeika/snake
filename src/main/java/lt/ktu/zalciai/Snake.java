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

public class Snake {
    private final LinkedList<Point> snake = new LinkedList<>();
    private int tailLength = 2;
    private int score = 0;
    private CollisionStrategy collisionStrategy;

    public void start(int startX, int startY) {
        snake.clear();
        tailLength = 2;
        score = 0;
        snake.add(new Point(startX, startY));
    }

    public Point move(Direction direction, Predicate<Point> collides, Predicate<Point> collidesRemote) throws CollisionException {
        Point head = snake.getLast();
        Point next = createNextHead(direction, head);
        if (snake.size() > tailLength) {
            snake.remove();
        }
        if (snake.contains(next) || collisionStrategy.colides(next, collides, collidesRemote)) {
            throw new CollisionException();
        }
        snake.add(next);
        return next;
    }

    public void eatFood(Food food) {
        collisionStrategy = food.getStrategy();
        score += food.getScore();
        tailLength++;
        System.out.println("Score: " + score);
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

    public LinkedList<Point> getPoints() {
        return snake;
    }

    public void setCollisionStrategy(CollisionStrategy collisionStrategy) {
        this.collisionStrategy = collisionStrategy;
    }
}
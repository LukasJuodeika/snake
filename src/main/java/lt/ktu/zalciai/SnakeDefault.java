package lt.ktu.zalciai;

import lt.ktu.zalciai.collision.CollisionStrategy;
import lt.ktu.zalciai.enums.Direction;
import lt.ktu.zalciai.exceptions.CollisionException;
import lt.ktu.zalciai.food.entities.Food;

import java.awt.*;
import java.util.LinkedList;
import java.util.function.Predicate;

public final class SnakeDefault extends Snake {

    private final LinkedList<Point> snake = new LinkedList<>();
    private int tailLength = 2;
    private int score = 0;

    @Override
    public void start(int startX, int startY) {
        snake.clear();
        tailLength = 2;
        score = 0;
        snake.add(new Point(startX, startY));
    }

    @Override
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

    @Override
    public void eatFood(Food food) {
        collisionStrategy = food.getStrategy();
        score += food.getScore();
        tailLength++;
    }

    @Override
    public LinkedList<Point> getPoints() {
        return snake;
    }

    @Override
    public void setCollisionStrategy(CollisionStrategy collisionStrategy) {
        this.collisionStrategy = collisionStrategy;
    }

    @Override
    public int GetScore() {
        return score;
    }

}

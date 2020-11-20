package lt.ktu.zalciai.npc;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lt.ktu.zalciai.Constants;

public class SimpleWorm implements NPC {

    private List<Point> body;

    public SimpleWorm(int size) {
        if(size <= 0)
            throw new IllegalArgumentException("Snake length must be more than 0");

        body = generateBody(size);
    }

    public int getSize() {
        return body.size();
    }

    private List<Point> generateBody(int size) {
        var list = new ArrayList<Point>();
        for (int i = 0; i < size; i++) {
            list.add(new Point(0, size - i));
        }
        return list;
    }

    public Collection<Point> getBodyPoints() {
        return body;
    }

    public Point getHeadPosition() {
        return body.get(0);
    }

    public void move(Point delta) {
        body.forEach(item -> {
           item.x = (delta.x + item.x) % Constants.SNAKE_GRID_WIDTH;
           item.y = (delta.y + item.y) % Constants.SNAKE_GRID_HEIGHT;
        });
    }

    public void performAction() {
        this.move(new Point(0, 1));
    }

    public NPC copy() {
        SimpleWorm wallWormClone = new SimpleWorm(this.getSize());
        wallWormClone.move(this.getHeadPosition());
        wallWormClone.move(new Point(0, this.getSize() * -1));
        return wallWormClone;
    }
}

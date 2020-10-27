package lt.ktu.zalciai.npc;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

import lt.ktu.zalciai.Constants;

public class WallWorm extends NPC {

    private ArrayList<Point> body;

    public WallWorm(int size) {
        body = new ArrayList<Point>();
        for (int i = 0; i < size; i++) {
            body.add(new Point(0, size - i));
        }
    }

    public int getSize() {
        return body.size();
    }

    public Collection<Point> getBodyPoints() {
        return body;
    }

    public Point getHeadPosition() {
        return body.get(0);
    }
    
    public void move(Point delta) {
        for (int i = 0; i < body.size(); i++) {
            body.get(i).x = (delta.x + body.get(i).x) % Constants.SNAKE_GRID_WIDTH;
            body.get(i).y = (delta.y + body.get(i).y) % Constants.SNAKE_GRID_HEIGHT;
        }
    }

    @Override
    public void performAction() {
        this.move(new Point(0, 1));
    }

    @Override
    public NPC copy() {
        WallWorm wallWormClone = new WallWorm(this.getSize());
        wallWormClone.move(this.getHeadPosition());
        wallWormClone.move(new Point(0, this.getSize() * -1));
        return wallWormClone;
    }
}

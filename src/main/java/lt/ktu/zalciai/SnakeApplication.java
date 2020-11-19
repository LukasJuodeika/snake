package lt.ktu.zalciai;

import lt.ktu.zalciai.controls.InputActionListener;
import lt.ktu.zalciai.controls.UserInputWASD;
import lt.ktu.zalciai.display.DisplayContract;
import lt.ktu.zalciai.display.DisplayJPanel;
import lt.ktu.zalciai.enums.ControlAction;
import lt.ktu.zalciai.enums.Direction;
import lt.ktu.zalciai.exceptions.CollisionException;
import lt.ktu.zalciai.food.FoodFactory;
import lt.ktu.zalciai.food.entities.Food;
import lt.ktu.zalciai.npc.NPC;
import lt.ktu.zalciai.npc.NonPlayerCharacters;
import lt.ktu.zalciai.snakegrid.SnakeGridContract;
import lt.ktu.zalciai.snakemap.SnakeClientToSnakeMapAdapter;
import lt.ktu.zalciai.snakemap.SnakeMap;
import lt.ktu.zalciai.ws.SnakeClient;
import lt.ktu.zalciai.collision.CollisionStrategy;


import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.Timer;

public class SnakeApplication implements ActionListener, InputActionListener, SnakeGridContract.Controller {

    private final int FRAME_INTERVAL = 80;
    private Timer timer = new Timer(FRAME_INTERVAL, this);
    private Snake snake = new Snake();
    private Direction direction = Direction.DOWN;
    private boolean over = false, paused;

    public Food food;
    private final DisplayContract.View view;
    private final SnakeMap snakeMap;
    private final FoodFactory foodFactory;

    private final SnakeClient client;
    private final SnakeMap remoteMap;
    private final NonPlayerCharacters nonPlayerCharacters;

    public SnakeApplication(
            FoodFactory foodFactory,
            SnakeMap snakeMap,
            SnakeClient client
    ) {
        this.foodFactory = foodFactory;
        this.snakeMap = snakeMap;
        this.client = client;
        this.remoteMap = new SnakeClientToSnakeMapAdapter(client);
        this.nonPlayerCharacters = new NonPlayerCharacters();
        view = new DisplayJPanel(
                new UserInputWASD(this),
                this
        );
    }

    public void startGame() {
        over = false;
        direction = Direction.DOWN;
        paused = false;
        snake.setCollisionStrategy(CollisionStrategy.normal());
        snake.start(15, 15);
        food = foodFactory.generateFood();
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (paused || over)
            return;
        Point newHead;
        try {
            newHead = snake.move(direction, snakeMap::colides, remoteMap::colides);
        } catch (CollisionException exception) {
            System.out.println(exception);
            over = true;
            return;
        }
        if (newHead.equals(food.getPoint())) {
            snake.eatFood(food);
            food = foodFactory.generateFood();
        }
        nonPlayerCharacters.performAction();
        client.sendPoints(Collections.singletonMap("#ff0000", snake.getPoints()));
        view.refresh();
    }

    @Override
    public void onControlAction(ControlAction controlAction) {
        switch (controlAction) {
            case PAUSE: {
                if (over) {
                    startGame();
                } else {
                    paused = !paused;
                }
            }
            break;
        }
    }

    @Override
    public void onDirectionAction(Direction direction) {
        if (!direction.isOpposite(this.direction)) {
            this.direction = direction;
        }
    }

    @Override
    public Map<String, Collection<Point>> generateDrawPoints() {
        Map<String, Collection<Point>> colorPoints = new HashMap<>();

        //draw snake
        addToColorPoints(colorPoints, "#FF00FF", snake.getPoints());

        //draw food
        addToColorPoints(colorPoints, food.getColorHex(), Collections.singletonList(food.getPoint()));

        //draw walls
        addToColorPoints(colorPoints, "#000", snakeMap.getWalls());

        //draw NPCs
        for (NPC npc : nonPlayerCharacters.getNPCs()) {
            addToColorPoints(colorPoints, "#000", npc.getBodyPoints());
        }

        //draw remote
        for (Map.Entry<String, Set<Point>> entry : client.remoteColorPoints.entrySet()) {
            addToColorPoints(colorPoints, entry.getKey(), entry.getValue());
        }
        
        return colorPoints;
    }

    private void addToColorPoints(
            Map<String, Collection<Point>> colorPoints,
            String colorHex,
            Collection<Point> points
    ) {
        colorPoints.merge(colorHex, points, (list1, list2) ->
                Stream.concat(list1.stream(), list2.stream())
                        .collect(Collectors.toList())
        );
    }
}

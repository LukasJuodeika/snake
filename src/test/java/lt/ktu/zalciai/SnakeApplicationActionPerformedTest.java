package lt.ktu.zalciai;


import lt.ktu.zalciai.display.DisplayContract;
import lt.ktu.zalciai.display.DisplayViewFactory;
import lt.ktu.zalciai.enums.ControlAction;
import lt.ktu.zalciai.exceptions.CollisionException;
import lt.ktu.zalciai.food.FoodFactory;
import lt.ktu.zalciai.food.entities.Food;
import lt.ktu.zalciai.snakemap.SnakeMap;
import lt.ktu.zalciai.ws.SnakeClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.awt.event.ActionEvent;

public class SnakeDefaultApplicationActionPerformedTest {

    @Mock
    public FoodFactory foodFactory;

    @Mock
    public Food food;

    @Mock
    public SnakeMap snakeMap;

    @Mock
    public SnakeClient snakeClient;

    @Mock
    public ActionEvent actionEvent;

    @Mock
    public DisplayContract.View view;

    @Mock
    public DisplayViewFactory displayViewFactory;

    @Mock
    public Snake snake;

    private SnakeDefaultApplication snakeDefaultApplication;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        doReturn(view).when(displayViewFactory).createDisplay(any(), any());
        doReturn(food).when(foodFactory).generateFood();
        snakeDefaultApplication = new SnakeDefaultApplication(
                foodFactory,
                snakeMap,
                snakeClient,
                displayViewFactory,
                snake
        );
    }

    @Test
    public void actionWhilePaused() throws Exception {
        var headPoint = new Point(1,1);
        var foodPoint = new Point(2, 2);
        doReturn(headPoint).when(snake).move(any(), any(), any());
        doReturn(foodPoint).when(food).getPoint();

        snakeDefaultApplication.startGame();
        snakeDefaultApplication.onControlAction(ControlAction.PAUSE);
        snakeDefaultApplication.actionPerformed(actionEvent);

        verify(snake, never()).move(any(), any(), any());
        verify(snakeClient, never()).sendPoints(anyMap());
        verify(view, never()).refresh();
    }

    @Test
    public void collision() throws Exception{
        var headPoint = new Point(1,1);
        var foodPoint = new Point(2, 2);
        doThrow(new CollisionException()).when(snake).move(any(), any(), any());
        doReturn(foodPoint).when(food).getPoint();

        snakeDefaultApplication.startGame();
        snakeDefaultApplication.actionPerformed(actionEvent);

        verify(food, never()).getPoint();
        verify(snake).move(any(), any(), any());
        verify(snakeClient, never()).sendPoints(anyMap());
        verify(view, never()).refresh();
    }

    @Test
    public void actionAfterCollision() throws Exception{
        var headPoint = new Point(1,1);
        var foodPoint = new Point(2, 2);
        doThrow(new CollisionException()).when(snake).move(any(), any(), any());
        doReturn(foodPoint).when(food).getPoint();

        snakeDefaultApplication.startGame();
        snakeDefaultApplication.actionPerformed(actionEvent);
        snakeDefaultApplication.actionPerformed(actionEvent);

        verify(food, never()).getPoint();
        verify(snake, times(1)).move(any(), any(), any());
        verify(snakeClient, never()).sendPoints(anyMap());
        verify(view, never()).refresh();
    }

    @Test
    public void eatFood() throws Exception{
        var headPoint = new Point(1,1);
        var foodPoint = new Point(1, 1);
        doReturn(headPoint).when(snake).move(any(), any(), any());
        doReturn(foodPoint).when(food).getPoint();

        snakeDefaultApplication.startGame();
        snakeDefaultApplication.actionPerformed(actionEvent);

        verify(snake).move(any(), any(), any());
        verify(snake).eatFood(food);
        verify(foodFactory, times(2)).generateFood();
        verify(snakeClient).sendPoints(anyMap());
        verify(view).refresh();
    }

    @Test
    public void dontEatFood() throws Exception{
        var headPoint = new Point(1,1);
        var foodPoint = new Point(2, 1);
        doReturn(headPoint).when(snake).move(any(), any(), any());
        doReturn(foodPoint).when(food).getPoint();

        snakeDefaultApplication.startGame();
        snakeDefaultApplication.actionPerformed(actionEvent);

        verify(snake).move(any(), any(), any());
        verify(snake, never()).eatFood(food);
        verify(snakeClient).sendPoints(anyMap());
        verify(view).refresh();
    }
}

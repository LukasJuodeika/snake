package lt.ktu.zalciai.collision;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.util.function.Predicate;

import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

public class CollisionStrategyIgnoreAllTest {

    private CollisionStrategy strategy = CollisionStrategy.ignoreAll();

    @Mock
    public Predicate<Point> mapCollision;

    @Mock
    public Predicate<Point> remoteCollision;

    @Mock
    public Point point;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void bothCollide() {
        doReturn(true).when(mapCollision).test(any());
        doReturn(true).when(remoteCollision).test(any());

        var result = strategy.colides(point, mapCollision, remoteCollision);

        assertFalse(result);
    }

    @Test
    public void mapCollides() {
        doReturn(true).when(mapCollision).test(any());
        doReturn(false).when(remoteCollision).test(any());

        var result = strategy.colides(point, mapCollision, remoteCollision);

        assertFalse(result);
    }

    @Test
    public void remoteCollides() {
        doReturn(false).when(mapCollision).test(any());
        doReturn(true).when(remoteCollision).test(any());

        var result = strategy.colides(point, mapCollision, remoteCollision);

        assertFalse(result);
    }

    @Test
    public void noCollisions() {
        doReturn(false).when(mapCollision).test(any());
        doReturn(false).when(remoteCollision).test(any());

        var result = strategy.colides(point, mapCollision, remoteCollision);

        assertFalse(result);
    }
}

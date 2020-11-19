package lt.ktu.zalciai.npc;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotSame;

public class SimpleWormPrototypeTest {

    @Test
    public void equalSize() {
        var original = new SimpleWorm(3);
        var clone = (SimpleWorm) original.copy();

        assertThat(clone.getSize()).isEqualTo(original.getSize());
    }

    @Test
    public void equalHedPosition() {
        var original = new SimpleWorm(3);
        var clone = (SimpleWorm) original.copy();

        assertThat(clone.getHeadPosition()).isEqualTo(original.getHeadPosition());
    }

    @Test
    public void deepCopy() {
        var original = new SimpleWorm(3);
        var clone = (SimpleWorm) original.copy();

        assertNotSame(original, clone);
        assertNotSame(original.getBodyPoints(), clone.getBodyPoints());
        assertNotSame(original.getBodyPoints(), clone.getBodyPoints());
    }
}

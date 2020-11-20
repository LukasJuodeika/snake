package lt.ktu.zalciai.npc;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.Test;

public class SimpleWormTest {

    @Test
    public void construct_illegalWorm() {
        var wormLength = 0;

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new SimpleWorm(wormLength));
    }

    @Test
    public void construct_wormLength5() {
        var wormLength = 5;
        var worm = new SimpleWorm(wormLength);

        assertThat(worm.getBodyPoints()).hasSize(wormLength);
    }

}

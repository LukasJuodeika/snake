package lt.ktu.zalciai.food;


import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.*;

public class FoodFactoryGetInstanceTest {

    @Test
    public void singleThread() {
        Assertions.assertThat(FoodFactory.getInstance()).isEqualTo(FoodFactory.getInstance());
    }

    @Test
    public void twoRunnables() throws InterruptedException {
        Set<String> classNames = new HashSet<>();
        Thread thread1 = new Thread(() -> classNames.add(FoodFactory.getInstance().toString()));
        Thread thread2 = new Thread(() -> classNames.add(FoodFactory.getInstance().toString()));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        Assertions.assertThat(classNames.size()).isEqualTo(1);
    }


    @Test
    public void fiveRunnables() throws InterruptedException {
        Set<String> classNames = new HashSet<>();
        Thread thread1 = new Thread(() -> classNames.add(FoodFactory.getInstance().toString()));
        Thread thread2 = new Thread(() -> classNames.add(FoodFactory.getInstance().toString()));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        Assertions.assertThat(classNames.size()).isEqualTo(1);
    }
}
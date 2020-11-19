package lt.ktu.zalciai.food;


import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.*;

public class FoodFactoryClassicGetInstanceTest {

     @Test
     public void singleThread() {
         Assertions.assertThat(FoodFactoryClassic.getInstance()).isEqualTo(FoodFactoryClassic.getInstance());
     }

     @Test
     public void twoRunnables() throws InterruptedException {
         Set<String> classNames = new HashSet<>();
         Thread thread1 = new Thread(() -> classNames.add(FoodFactoryClassic.getInstance().toString()));
         Thread thread2 = new Thread(() -> classNames.add(FoodFactoryClassic.getInstance().toString()));
         thread1.start();
         thread2.start();
         thread1.join();
         thread2.join();

         Assertions.assertThat(classNames.size()).isEqualTo(1);
     }

     @Test
     public void fiveRunnables() throws InterruptedException {
         Set<String> classNames = new HashSet<>();
         Thread thread1 = new Thread(() -> classNames.add(FoodFactoryClassic.getInstance().toString()));
         Thread thread2 = new Thread(() -> classNames.add(FoodFactoryClassic.getInstance().toString()));
         thread1.start();
         thread2.start();
         thread1.join();
         thread2.join();

         Assertions.assertThat(classNames.size()).isEqualTo(1);
     }
}

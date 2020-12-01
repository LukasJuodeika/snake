package lt.ktu.zalciai.npc.state;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CareTaker {
   private List<Memento> mementoList = new ArrayList<Memento>();
   private final Random random;

   public CareTaker() {
      random = new Random();
   }

   public void add(Memento state){
      if (mementoList.size() < 5) {
         mementoList.add(state);
      }
   }

   public Memento getRandom(){
      return mementoList.get(random.nextInt(mementoList.size()));
   }
}

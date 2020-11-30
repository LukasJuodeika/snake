package lt.ktu.zalciai.npc.state;

public class Context {
    private State state;

    public Context(){
       state = new StartState();
    }
 
    public void setState(State state){
       this.state = state;		
    }
 
    public State getState(){
       return state;
    }
}

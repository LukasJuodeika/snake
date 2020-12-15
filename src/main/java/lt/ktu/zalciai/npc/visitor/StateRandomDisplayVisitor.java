package lt.ktu.zalciai.npc.visitor;

import java.util.Random;

import lt.ktu.zalciai.npc.state.*;

public class StateRandomDisplayVisitor implements StateDisplayVisitor {

    private final Random random;

    public StateRandomDisplayVisitor() {
        random = new Random();
    }

    private String getRandomColor() {
        int classNumber = random.nextInt(3);
        switch (classNumber) {
            case 0:
                return "#ABCABC";
            case 1:
                return "#321321";
            default:
                return "#123123";
        }
    }
    
    @Override
    public String visit(StartState startState) {
        return getRandomColor();
    }

    @Override
    public String visit(AdvancedState advancedState) {
        return getRandomColor();
    }

    @Override
    public String visit(HardState hardState) {
        return getRandomColor();
    }

    @Override
    public String visit(ExtremeState extremeState) {
        return getRandomColor();
    }
}

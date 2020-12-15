package lt.ktu.zalciai.npc.visitor;

import lt.ktu.zalciai.npc.state.*;

public class StateColorfulDisplayVisitor implements StateDisplayVisitor {
    
    @Override
    public String visit(StartState startState) {
        return "#AA00AA";
    }

    @Override
    public String visit(AdvancedState advancedState) {
        return "#BBBB00";
    }

    @Override
    public String visit(HardState hardState) {
        return "#55AA55";

    }

    @Override
    public String visit(ExtremeState extremeState) {
        return "#AC1065";
    }
}

package lt.ktu.zalciai.npc.visitor;

import lt.ktu.zalciai.npc.state.AdvancedState;
import lt.ktu.zalciai.npc.state.ExtremeState;
import lt.ktu.zalciai.npc.state.HardState;
import lt.ktu.zalciai.npc.state.StartState;

public class StateBaseDisplayVisitor implements StateDisplayVisitor {

    @Override
    public String visit(StartState startState) {
        return "#000";
    }

    @Override
    public String visit(AdvancedState advancedState) {
        return "#000";
    }

    @Override
    public String visit(HardState hardState) {
        return "#000";

    }

    @Override
    public String visit(ExtremeState extremeState) {
        return "#000";
    }
    
}

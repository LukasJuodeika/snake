package lt.ktu.zalciai.npc.visitor;

import lt.ktu.zalciai.npc.state.*;

public interface StateDisplayVisitor {
	public String visit(StartState startState);
	public String visit(AdvancedState advancedState);
	public String visit(HardState hardState);
	public String visit(ExtremeState extremeState);
}
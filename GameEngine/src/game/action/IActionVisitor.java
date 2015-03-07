package game.action;

public interface IActionVisitor {
	void visit(IActionVisitee visitee);
}

package game.action;

public interface IActionVisitee {
	void accept(IActionVisitor visitor);
	ActionType getType();
}

package game.action.scaffold;

import game.error.ActionException;

import java.util.List;

public interface IActionVisitee {
	void accept(IActionVisitor visitor) throws ActionException;
	ActionType getType();
	List<IAction> getActions() throws ActionException;
	void registerAction(IAction action) throws ActionException;
}

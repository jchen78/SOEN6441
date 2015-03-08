package game.action.scaffold;

import game.error.ActionException;

public interface IActionVisitor {
	void visit(IActionVisitee visitee) throws ActionException;
}

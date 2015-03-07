package game.action;

import game.error.ActionException;

import java.util.List;

public interface IActionVisitor {
	void visit(IActionVisitee visitee) throws ActionException;
}

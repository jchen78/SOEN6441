package game.action.scaffold;

import game.error.ActionException;


/**
 * 
 * IActionVisitor.java
 * 
 * /
 
public interface IActionVisitor {
	void visit(IActionVisitee visitee) throws ActionException;
}

package game.error;

import game.action.*;

public class ActionException extends Exception {
	private static final long serialVersionUID = 1L;
	private IActionVisitor _actionController;
	private IActionVisitee _currentAction;
	
	public ActionException(IActionVisitor visitor, IActionVisitee visitee, Exception innerException) {
		super(innerException);
		_actionController = visitor;
		_currentAction = visitee;
	}
	
	public IActionVisitor getActionController() {
		return _actionController;
	}
	
	public IActionVisitee getAttemptedAction() {
		return _currentAction;
	}
}

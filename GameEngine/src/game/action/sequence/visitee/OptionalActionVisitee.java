package game.action.sequence.visitee;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.error.EntityNotSetException;

public class OptionalActionVisitee implements IVisitee {
	private IVisitee[] _action;
	
	public OptionalActionVisitee(IVisitee optionalAction) {
		_action = new IVisitee[2];
		_action[0] = optionalAction;
		_action[1] = new NoActionVisitee();
	}
	
	@Override
	public void accept(IVisitor visitor) throws GameOverException, EntityNotSetException {
		visitor.visit(new SingleActionSelector(_action, "actions"));
	}

	@Override
	public String getDescription() {
		return _action[0].getDescription() + " (Optional action: further choose whether to skip.)";
	}

}

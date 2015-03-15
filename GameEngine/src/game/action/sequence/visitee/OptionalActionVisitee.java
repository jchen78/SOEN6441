package game.action.sequence.visitee;

import java.util.LinkedList;
import java.util.List;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;

public class OptionalActionVisitee implements IVisitee {
	private List<IVisitee> _action;
	
	public OptionalActionVisitee(IVisitee optionalAction) {
		_action = new LinkedList<IVisitee>();
		_action.add(optionalAction);
		_action.add(new NoActionVisitee());
	}
	
	@Override
	public void accept(IVisitor visitor) throws GameOverException {
		visitor.visit(new SingleActionSelector(_action, "actions"));
	}

	@Override
	public String getDescription() {
		return _action.get(0).getDescription();
	}

}

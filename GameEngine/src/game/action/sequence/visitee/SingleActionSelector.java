package game.action.sequence.visitee;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;

public class SingleActionSelector implements IVisitee {
	private IVisitee[] _possibleActions;
	private IVisitee _selection;
	private String _visiteeType;
	
	public SingleActionSelector(IVisitee[] possibleActions, String visiteeType) {
		_possibleActions = possibleActions;
		_visiteeType = visiteeType;
	}

	@Override
	public void accept(IVisitor visitor) throws GameOverException {
		_selection = (IVisitee)visitor.selectAction(_possibleActions);
		visitor.visit(_selection);
	}

	@Override
	public String getDescription() {
		return "Select one of the " + _visiteeType;
	}
	
	public IVisitee getSelection() {
		return _selection;
	}
}

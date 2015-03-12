package game.action.sequence.visitee;

import java.util.List;
import java.util.Queue;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;

public class SingleActionSelector implements IVisitee {
	private List<IVisitee> _possibleActions;
	private IVisitee _selection;
	private String _visiteeType;
	
	public SingleActionSelector(List<IVisitee> possibleActions, String visiteeType) {
		_possibleActions = possibleActions;
		_visiteeType = visiteeType;
	}

	@Override
	public void accept(IVisitor visitor) throws GameOverException {
		_selection = visitor.selectAction(_possibleActions);
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

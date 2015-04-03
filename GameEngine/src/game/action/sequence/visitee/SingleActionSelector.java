package game.action.sequence.visitee;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.error.EntityNotSetException;
import game.error.InvalidOperationException;

public class SingleActionSelector implements IVisitee {
	private IVisitee[] _possibleActions;
	private int _selectedIndex = -1;
	private IVisitee _selection;
	private String _visiteeType;
	
	public SingleActionSelector(IVisitee[] possibleActions, String visiteeType) {
		_possibleActions = possibleActions;
		_visiteeType = visiteeType;
	}

	@Override
	public void accept(IVisitor visitor) throws GameOverException, EntityNotSetException, InvalidOperationException {
		_selection = (IVisitee)visitor.selectAction(_possibleActions);
		for (int i = 0; i < _possibleActions.length; i++)
			if (_selection == _possibleActions[i]) {
				_selectedIndex = i;
				break;
			}
		
		visitor.visit(_selection);
	}

	@Override
	public String getDescription() {
		return "Select one of the " + _visiteeType;
	}
	
	public IVisitee getSelection() {
		return _selection;
	}
	
	public int getSelectedIndex() {
		return _selectedIndex;
	}
}

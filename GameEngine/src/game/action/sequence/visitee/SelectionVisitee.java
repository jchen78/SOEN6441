package game.action.sequence.visitee;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;

public class SelectionVisitee implements IVisitee {
	private String _selectionName;
	
	public SelectionVisitee(String selectionName) {
		_selectionName = selectionName;
	}
	
	@Override
	public void accept(IVisitor visitor) throws GameOverException {
		
	}

	@Override
	public String getDescription() {
		return _selectionName;
	}

}

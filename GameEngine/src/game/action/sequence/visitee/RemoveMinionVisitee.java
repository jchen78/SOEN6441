package game.action.sequence.visitee;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.engine.MapArea;

public class RemoveMinionVisitee implements IVisitee {
	private String _targetPlayerName;
	
	public RemoveMinionVisitee(String targetPlayerName) {
		_targetPlayerName = targetPlayerName;
	}

	@Override
	public void accept(IVisitor visitor) throws GameOverException {
		// TODO Auto-generated method stub
		// Creates list of areas for removal
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public MapArea getRemovedMapArea() {
		return null;
	}
}

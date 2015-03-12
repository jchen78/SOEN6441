package game.action.sequence.visitee;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.engine.*;

public class WinningConditionVisitor implements IVisitee {
	@Override
	public void accept(IVisitor visitor) throws GameOverException {
		// TODO Auto-generated method stub
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Evaluating whether winning conditions have been met...";
	}

}

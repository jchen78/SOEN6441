package game.action.sequence.visitee;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.engine.*;

public class EvaluateEscapeActionVisitee implements IVisitee {
	public EvaluateEscapeActionVisitee(Player targetPlayer, ActionType typeOfAction, ActionName actionToInterrupt) {
		
	}
	
	@Override
	public void accept(IVisitor visitor) throws GameOverException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isActionInterrupted() {
		return false;
	}
}

package game.action.sequence.visitee;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;

public class NoActionVisitee implements IVisitee {

	@Override
	public void accept(IVisitor visitor) throws GameOverException {
	}

	@Override
	public String getDescription() {
		return "Skip the current action.";
	}

}

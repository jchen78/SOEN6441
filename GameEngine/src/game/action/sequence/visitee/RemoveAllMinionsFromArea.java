package game.action.sequence.visitee;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;

public class RemoveAllMinionsFromArea implements IVisitee {

	@Override
	public void accept(IVisitor visitor) throws GameOverException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Remove all minions from the area affected";
	}

}

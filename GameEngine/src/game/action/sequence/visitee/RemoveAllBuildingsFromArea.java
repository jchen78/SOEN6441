package game.action.sequence.visitee;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;

public class RemoveAllBuildingsFromArea implements IVisitee {

	@Override
	public void accept(IVisitor visitor) throws GameOverException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDescription() {
	
	   return "Remove any building from the area affected";
	}

}

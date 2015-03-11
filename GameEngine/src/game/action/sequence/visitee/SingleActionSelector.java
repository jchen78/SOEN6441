package game.action.sequence.visitee;

import java.util.List;
import java.util.Queue;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;

public class SingleActionSelector implements IVisitee {
	public SingleActionSelector(List<IVisitee> possibleActions) {
		
	}

	@Override
	public Queue<IVisitee> accept(IVisitor visitor) throws GameOverException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}

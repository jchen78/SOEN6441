package game.action.sequence.visitee;

import java.util.LinkedList;
import java.util.Queue;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.engine.*;

public class WinningConditionVisitor implements IVisitee {
	@Override
	public Queue<IVisitee> accept(IVisitor visitor) {
		// TODO Auto-generated method stub
		// No prerequisites: return an empty collection
		return new LinkedList<IVisitee>();
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Evaluating whether winning conditions have been met...";
	}

}

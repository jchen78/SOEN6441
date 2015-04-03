package game.action.sequence.precondition;

import game.action.sequence.interfaces.IPrecondition;
import game.action.sequence.interfaces.IVisitor;

public class HasEnoughMoney implements IPrecondition {
	private int _requiredAmount;
	
	public HasEnoughMoney(int requiredAmount) {
		_requiredAmount = requiredAmount;
	}

	@Override
	public boolean isActionPossible(IVisitor currentInstance) {
		return currentInstance.getCurrentPlayer().getMoney() >= _requiredAmount;
	}

}

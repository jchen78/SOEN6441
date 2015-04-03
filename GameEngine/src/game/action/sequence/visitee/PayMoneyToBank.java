package game.action.sequence.visitee;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.error.InvalidOperationException;

public class PayMoneyToBank implements IVisitee {
	private int _amount;
	
	public PayMoneyToBank(int amount) {
		_amount = amount;
	}

	@Override
	public void accept(IVisitor visitor) throws GameOverException, InvalidOperationException {
		visitor.getCurrentPlayer().removeMoney(_amount);
		visitor.depositMoneyToBank(_amount);
	}

	@Override
	public String getDescription() {
		return "Pay $" + _amount + " to bank";
	}

}

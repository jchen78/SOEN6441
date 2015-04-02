package game.action.sequence.visitee;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.core.interfaces.IPlayer;
import game.engine.*;
import game.error.EntityNotSetException;
import game.error.InvalidOperationException;

public class RemoveMoneyVisitee implements IVisitee {
	private String _targetName;
	private int _amount;
	
	public RemoveMoneyVisitee(String targetName, int amount) {
		_targetName = targetName;
		_amount = amount;
	}
	
	@Override
	public void accept(IVisitor visitor) throws GameOverException, EntityNotSetException {
		IPlayer targetPlayer = visitor.getPlayer(_targetName);
		int availableFunds = targetPlayer.getMoney();
		if (availableFunds == 0) {
			_amount = 0;
			return;
		} else if (availableFunds < _amount)
			_amount = availableFunds;
		
		try {
			targetPlayer.removeMoney(_amount);
		} catch (InvalidOperationException e) {
			// TODO Gracefully exit
			e.printStackTrace();
		}
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getAmountRemoved() {
		return _amount;
	}
}

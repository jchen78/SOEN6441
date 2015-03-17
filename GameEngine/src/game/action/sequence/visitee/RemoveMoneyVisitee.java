package game.action.sequence.visitee;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.engine.*;
import game.error.InvalidOperationException;

public class RemoveMoneyVisitee implements IVisitee {
	private String _targetName;
	private int _amount;
	private ActionType _actionSource;
	private ActionName _actionName;
	
	public RemoveMoneyVisitee(String targetName, int amount, ActionType actionSource, ActionName actionName) {
		_targetName = targetName;
		_amount = amount;
		_actionSource = actionSource;
		_actionName = actionName;
	}
	
	@Override
	public void accept(IVisitor visitor) throws GameOverException {
		Player targetPlayer = visitor.getGameInstance().getPlayer(_targetName);
		int availableFunds = targetPlayer.getMoney();
		if (availableFunds == 0) {
			_amount = 0;
			return;
		} else if (availableFunds < _amount)
			_amount = availableFunds;
		
		if (!_targetName.equals(visitor.getCurrentPlayer().getName()) || _actionName != ActionName.BuyBuilding) {
			EvaluateEscapeActionVisitee interrupt = new EvaluateEscapeActionVisitee(targetPlayer, _actionSource, _actionName);
			visitor.visit(interrupt);
			if (interrupt.isActionInterrupted()) {
				_amount = 0;
				return;
			}
		}
		
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
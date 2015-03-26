package game.action.sequence.visitee;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.core.interfaces.IPlayer;
import game.engine.*;
import game.error.InvalidOperationException;

public class TakeMoneyFromOthersVisitee implements IVisitee {
	private int _amount;
	private ActionType _actionType;
	private ActionName _actionName;
	
	public TakeMoneyFromOthersVisitee(int amount, ActionType actionSource, ActionName actionName) {
		_amount = amount;
		_actionType = actionSource;
		_actionName = actionName;
	}
	
	@Override
	public void accept(IVisitor visitor) throws GameOverException {
		for (IPlayer player : visitor.getGameInstance().getAllPlayers()) {
			if (player == visitor.getCurrentPlayer())
				continue;
			
			RemoveMoneyVisitee currentAction = new RemoveMoneyVisitee(player.getName(), _amount, _actionType, _actionName);
			visitor.visit(currentAction);
			
			try {
				player.addMoney(currentAction.getAmountRemoved());
			} catch (InvalidOperationException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getDescription() {
		return "Take " + _amount + " from others, where possible.";
	}
	
}

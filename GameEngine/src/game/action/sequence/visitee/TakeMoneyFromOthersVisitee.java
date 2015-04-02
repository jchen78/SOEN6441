package game.action.sequence.visitee;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.core.interfaces.IPlayer;
import game.engine.*;
import game.error.EntityNotSetException;
import game.error.InvalidOperationException;

public class TakeMoneyFromOthersVisitee implements IVisitee {
	private int _amount;
	
	public TakeMoneyFromOthersVisitee(int amount) {
		_amount = amount;
	}
	
	@Override
	public void accept(IVisitor visitor) throws GameOverException, EntityNotSetException, InvalidOperationException {
		IPlayer activePlayer = visitor.getCurrentPlayer();
		for (IPlayer targetPlayer : visitor.getAllPlayers()) {
			if (targetPlayer == activePlayer)
				continue;
			
			RemoveMoneyVisitee currentAction = new RemoveMoneyVisitee(targetPlayer.getName(), _amount);
			InterruptibleVisitee interruptHandler = new InterruptibleVisitee(currentAction, targetPlayer.getName(), ActionType.PlayerCardText, ActionName.TakeMoney);
			visitor.visit(interruptHandler);
			
			if (interruptHandler.isActionInterrupted())
				continue;
			
			try {
				activePlayer.addMoney(currentAction.getAmountRemoved());
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

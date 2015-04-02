package game.action.sequence.visitee;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.core.interfaces.ICityArea;
import game.core.interfaces.IPlayer;
import game.core.interfaces.IPlayerCard;
import game.error.InvalidOperationException;

public class TakeMoneyFromBank implements IVisitee {
    private int _amount;
	private IPlayerCard _card;
	
	
	public TakeMoneyFromBank(int amount) {
        _amount=amount;
	}
	
	@Override
	public void accept(IVisitor visitor) throws GameOverException, InvalidOperationException {
	    int amountRetrieved = visitor.withdrawMoneyFromBank(_amount);
		IPlayer currentPlayer = visitor.getCurrentPlayer();
		currentPlayer.addMoney(amountRetrieved);
		currentPlayer.putPlayerCardInDisplay(_card);
	}

	@Override
	public String getDescription() {
	              return "Take money from the bank";
	}

}

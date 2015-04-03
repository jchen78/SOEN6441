package game.action.sequence.visitee;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.core.interfaces.IPlayer;
import game.core.interfaces.IPlayerCard;
import game.error.BankException;
import game.error.InvalidOperationException;

public class TakeLoanFromBankVisitee implements IVisitee {
	private int _loanAmount;
	private IPlayerCard _card;
	
	public TakeLoanFromBankVisitee(IPlayerCard currentCard) {
		_loanAmount = 10;
		_card = currentCard;
	}
	
	@Override
	public void accept(IVisitor visitor) throws GameOverException, InvalidOperationException {
		int amountRetrieved = visitor.withdrawMoneyFromBank(_loanAmount);
		IPlayer currentPlayer = visitor.getCurrentPlayer();
		currentPlayer.addMoney(amountRetrieved);
		currentPlayer.putPlayerCardInDisplay(_card);
	}

	@Override
	public String getDescription() {
		return "Take $10 from Bank. At the end of the game, pay $12, or lose 15 points.";
	}

}

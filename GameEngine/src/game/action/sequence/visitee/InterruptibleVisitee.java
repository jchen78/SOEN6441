package game.action.sequence.visitee;

import java.util.LinkedList;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.core.enums.PlayerCardName;
import game.core.interfaces.IPlayer;
import game.core.interfaces.IPlayerCard;
import game.engine.ActionName;
import game.engine.ActionType;
import game.engine.CardType;
import game.error.EntityNotSetException;

public class InterruptibleVisitee implements IVisitee {
	private IVisitee _interruptibleAction;
	private String _interruptorName;
	private ActionName _actionName;
	private ActionType _actionType;
	private LinkedList<IPlayerCard> _interruptorCards;
	
	private boolean _isActionInterrupted = false;
	
	public InterruptibleVisitee(IVisitee interruptibleAction, String potentialInterruptorName, ActionType actionType, ActionName actionName) {
		_interruptibleAction = interruptibleAction;
		_interruptorName = potentialInterruptorName;
		_actionName = actionName;
		_actionType = actionType;
	}

	@Override
	public String getDescription() {
		return "Attempting to complete interruptible action: " + _interruptibleAction.getDescription();
	}

	@Override
	public void accept(IVisitor visitor) throws GameOverException, EntityNotSetException {
		IPlayer targetPlayer = visitor.getPlayer(_interruptorName);
		_interruptorCards = new LinkedList<IPlayerCard>();
		
		int numberInterruptorCards = 0;
		for (PlayerCardName cardName : targetPlayer.getPlayerCards()) {
			IPlayerCard card = visitor.getPlayerCard(cardName);
			if (card.canInterrupt(_actionName, _actionType)) {
				_interruptorCards.add(card);
				numberInterruptorCards++;
			}
		}
		
		if (numberInterruptorCards > 0) {
			SelectionVisitee[] choices = new SelectionVisitee[numberInterruptorCards + 1];
			int index = 0;
			for (IPlayerCard currentCard : _interruptorCards) {
				choices[index++] = new SelectionVisitee(currentCard.getDescription());
			}
			
			choices[numberInterruptorCards] = new SelectionVisitee("Do nothing (let current action complete).");
			
			SingleActionSelector selector = new SingleActionSelector(choices, "interrupt cards");
			visitor.setCurrentPlayer(targetPlayer);
			visitor.visit(selector);
			int selectedIndex = selector.getSelectedIndex();
			visitor.clearCurrentPlayer();
			
			if (selectedIndex < numberInterruptorCards) {
				_isActionInterrupted = true;
				PlayerCardName discardedCard = _interruptorCards.get(selectedIndex).getName();
				targetPlayer.discardPlayerCard(discardedCard);
				visitor.discardPlayerCard(discardedCard);
			}
		}
		
		if (!_isActionInterrupted)
			_interruptibleAction.accept(visitor);
	}
	
	public boolean isActionInterrupted() {
		return _isActionInterrupted;
	}
}

package game.action.sequence.visitee;

import java.util.LinkedList;
import java.util.List;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.core.enums.CityAreaData;
import game.core.enums.PlayerCardName;
import game.core.interfaces.ICityArea;
import game.core.interfaces.IPlayer;
import game.core.interfaces.IPlayerCard;
import game.engine.GameManager;
import game.engine.CardType;
import game.error.EntityNotSetException;

public class TurnVisitee implements IVisitee {
	private String _playerName;
	
	public TurnVisitee(String playerName) {
		_playerName = playerName;
	}
	
	@Override
	public void accept(IVisitor visitor) throws GameOverException {
		// TODO: Offer to save & exit the game
		
		// First update the visitor to indicate the current player.
		GameManager gameInstance = visitor.getGameInstance();
		IPlayer currentPlayer = gameInstance.getPlayer(_playerName);
		visitor.setCurrentPlayer(currentPlayer);
		
		// Evaluate the player's winning conditions; no prerequisites are related to this action.
		new WinningConditionVisitor().accept(visitor);
		
		// If there are any playable cards, create a card-chooser and add that to the action selector.
		// If there are any city cards, add those to the possible actions
		LinkedList<IVisitee> currentlyAvailableActions = new LinkedList<IVisitee>();
		List<IVisitee> playableCards = new LinkedList<IVisitee>();
		for (PlayerCardName cardName : currentPlayer.getPlayerCards()) {
			IPlayerCard currentCard = gameInstance.getPlayerCard(cardName);
			try {
				if (currentCard.getCardType().equals(CardType.Playable))
					playableCards.add(currentCard);
			} catch (EntityNotSetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		currentlyAvailableActions.add(new SingleActionSelector(playableCards, "player cards."));
		for (CityAreaData cityAreaName : currentPlayer.getCityCards()) {
			ICityArea cityCard = gameInstance.getCityArea(cityAreaName);
			if (cityCard.getCardType().equals(CardType.Playable))
				currentlyAvailableActions.add(cityCard);
		}
		
		new MultipleActionSelector(currentlyAvailableActions).accept(visitor);
		
		int numberCardsInHand = currentPlayer.getPlayerCards().size();
		for (int i = numberCardsInHand; i < 5; i++) {
			currentPlayer.addPlayerCard(gameInstance.drawPlayerCard());
		}
	}

	@Override
	public String getDescription() {
		return _playerName + "'s turn.";
	}

}

package game.action.sequence.visitee;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.engine.CityAreaCard;
import game.engine.GameManager;
import game.engine.Player;
import game.engine.PlayerCard;
import game.engine.CardType;
import game.error.EntityNotSetException;

public class TurnVisitee implements IVisitee {
	private String _playerName;
	
	public TurnVisitee(String playerName) {
		_playerName = playerName;
	}
	
	@Override
	public Queue<IVisitee> accept(IVisitor visitor) {
		// First update the visitor to indicate the current player.
		GameManager gameInstance = visitor.getGameInstance();
		Player currentPlayer = gameInstance.getPlayer(_playerName);
		visitor.setCurrentPlayer(currentPlayer);
		
		// Evaluate the player's winning conditions; no prerequisites are related to this action.
		new WinningConditionVisitor().accept(visitor);
		
		// If there are any playable cards, create a card-chooser and add that to the action selector.
		// If there are any city cards, add those to the possible actions
		LinkedList<IVisitee> currentlyAvailableActions = new LinkedList<IVisitee>();
		List<IVisitee> playableCards = new LinkedList<IVisitee>();
		for (String cardName : currentPlayer.getPlayerCards()) {
			PlayerCard currentCard = gameInstance.getPlayerCard(cardName);
			try {
				if (currentCard.getType().equals(CardType.Playable))
					playableCards.add(currentCard);
			} catch (EntityNotSetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		currentlyAvailableActions.add(new SingleActionSelector(playableCards));
		for (String cityAreaName : currentPlayer.getCityCards()) {
			CityAreaCard cityCard = gameInstance.getCityAreaCard(cityAreaName);
			if (cityCard.getCardType().equals(CardType.Playable))
				currentlyAvailableActions.add(cityCard);
		}
		
		visitor.visit(currentlyAvailableActions);
		
		// No prerequisites: return an empty collection.
		return new LinkedList<IVisitee>();
	}

	@Override
	public String getDescription() {
		return _playerName + "'s turn.";
	}

}

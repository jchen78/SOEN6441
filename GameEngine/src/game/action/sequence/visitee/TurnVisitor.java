package game.action.sequence.visitee;

import java.util.LinkedList;
import java.util.Queue;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.engine.Player;

public class TurnVisitor implements IVisitee {
	private String _playerName;
	
	public TurnVisitor(String playerName) {
		_playerName = playerName;
	}
	
	@Override
	public Queue<IVisitee> accept(IVisitor visitor) {
		// First update the visitor to indicate the current player.
		Player currentPlayer = visitor.getGameInstance().getPlayer(_playerName);
		visitor.setCurrentPlayer(currentPlayer);
		
		// Evaluate the player's winning conditions; no prerequisites are related to this action.
		new WinningConditionVisitor().accept(visitor);
		
		// If there are any playable cards, create a card-chooser and add that to the action selector.
		// If there are any city cards, add those to the possible actions
		
		
		// No prerequisites: return an empty collection.
		return new LinkedList<IVisitee>();
	}

	@Override
	public String getDescription() {
		return _playerName + "'s turn.";
	}

}

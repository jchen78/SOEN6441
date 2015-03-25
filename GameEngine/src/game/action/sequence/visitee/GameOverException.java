package game.action.sequence.visitee;

import game.core.interfaces.IPlayer;

/**
 * Thrown when a winning condition is met.
 * @author Jason
 *
 */
public class GameOverException extends Exception {
	private IPlayer _winner;
	
	public GameOverException(IPlayer winner) {
		_winner = winner;
	}
	
	public GameOverException() {
		_winner = null;
	}
	
	public IPlayer getWinner() {
		return _winner;
	}
}

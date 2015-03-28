package game.engine.utils;

import game.core.interfaces.IPlayer;
import game.core.io.PersistanceManager;
import game.engine.ConcreteCreator;
import game.error.InvalidOperationException;

// TODO: This class SHOULD be tested; the tests are relevant to the proper implementation of the game.
/**
 * This class handles player-turn logic. It is in charge of retrieving players and keeping track of the players' data.
 * @author Jason
 *
 */
public class PlayerIterator {
	private int _currentIndex;
	private IPlayer[] _allPlayers;
	private ConcreteCreator _creator;

	private static int getNumberOfPlayers(String initialState) {
		return initialState.length() - initialState.replace(PersistanceManager.ROW_SEPARATOR, "").length() + 1;
	}
	
	public PlayerIterator(String initialState) throws IllegalArgumentException, InvalidOperationException {
		this(getNumberOfPlayers(initialState));
		setState(initialState);
	}
	
	public PlayerIterator(int numberOfPlayers) {
		this(numberOfPlayers, new ConcreteCreator());
	}
	
	public PlayerIterator(int numberOfPlayers, ConcreteCreator creator) {
		_allPlayers = new IPlayer[numberOfPlayers];
		_currentIndex = 0; // TODO: get random starting player.
		_creator = creator;
	}
	
	public void addPlayer(IPlayer newPlayer) {
		_allPlayers[_currentIndex] = newPlayer;
		iteratePlayerIndex();
	}

	private void iteratePlayerIndex() {
		_currentIndex = (_currentIndex + 1) % _allPlayers.length;
	}
	
	public void setCurrentPlayer(String playerName) {
		while (_allPlayers[_currentIndex].getName() != playerName)
			iteratePlayerIndex();
	}
	
	public IPlayer getCurrentPlayer() {
		return _allPlayers[_currentIndex];
	}
	
	public IPlayer iterate() {
		iteratePlayerIndex();
		return getCurrentPlayer();
	}
	
	public IPlayer[] getNonCurrentPlayers() {
		IPlayer[] nonCurrentPlayers = new IPlayer[_allPlayers.length - 1];
		for (int i = 0; i < nonCurrentPlayers.length; i++) {
			int nextIndex = (_currentIndex + i) % _allPlayers.length;
			nonCurrentPlayers[i - 1] = _allPlayers[nextIndex];
		}
		
		return nonCurrentPlayers;
	}
	
	public String getCurrentState() {
		String currentState = "";
		for (IPlayer player : _allPlayers)
			currentState += PersistanceManager.ROW_SEPARATOR + player.getCurrentState();
		
		if (currentState.length() > 0)
			currentState = currentState.replaceFirst(PersistanceManager.ROW_SEPARATOR, "");
		
		return currentState;
	}
	
	public void setState(String currentState) throws IllegalArgumentException, InvalidOperationException {
		String[] allPlayersState = currentState.split(PersistanceManager.ROW_SEPARATOR);
		if (allPlayersState.length != _allPlayers.length)
			throw new IllegalArgumentException();
		
		int currentIndex = 0;
		for (String currentPlayerState : allPlayersState) {
			IPlayer currentPlayer = _creator.createPlayer(currentIndex++, currentPlayerState);
			addPlayer(currentPlayer);
		}
	}

	public IPlayer getPlayer(String playerName) {
		for (IPlayer player : _allPlayers) {
			if (player.getName().equals(playerName))
				return player;
		}
		return null;
	}

	public IPlayer getPlayer(int i) {
		// TODO Auto-generated method stub
		return _allPlayers[i];
	}
}
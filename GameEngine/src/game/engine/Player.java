package game.engine;

import game.core.enums.CityAreaData;
import game.core.enums.PersonalityCardName;
import game.core.enums.PlayerCardName;
import game.core.interfaces.ICityArea;
import game.core.interfaces.IGameInstance;
import game.core.interfaces.IPlayer;
import game.error.InvalidOperationException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Player implements IMoneyHolder, IPlayer {
	private int _playerIndex;
	private String _playerName;
	private String _playerColor;
	private int _numOfPlayerBuildings;
	private int _numOfPlayerMinions = 12;
	private PersonalityCardName _playerPersonality;
	private int _playerMoney;
	private LinkedList<PlayerCardName> _playerCards;
	private HashSet<CityAreaData> _cityCards;
	
	public Player(int index) {
		_playerIndex = index;
	}

	/**
	 * Gets the user-friendly player name.
	 */
    public String getName() {
		return _playerName;
	}
	
    /**
     * Sets the user-friendly player name, intended for display purposes.
     * @param name Player's name
     * @throws InvalidOperationException Thrown when the name contains a semicolon character.
     */
    public void setName(String name) throws InvalidOperationException {
    	if (name.contains(";"))
    		throw new InvalidOperationException("Player name cannot contain the semicolon character.");
    	
		this._playerName = name;
	}
	
    /**
     * Gets the player color, as set by the game manager.
     * @return The game manager-supplied player color.
     */
	public String getplayercolor() {
		return _playerColor;
	}
	
	/**
	 * Allows the game manager to initialize the player's color.
	 * @param playercolor Name of the color, as can later be recognized by the game manager.
	 * @throws InvalidOperationException Thrown when the color contains a semicolon character.
	 */
	public void setplayercolor(String playercolor) throws InvalidOperationException {
		if (_playerColor.contains(";"))
			throw new InvalidOperationException("The player color cannot contain the semicolon character.");
		
		this._playerColor = playercolor;
	}
	
	/**
	 * Gets the number of minions currently in the player's possession, i.e. not on the board.
	 */
	public int getNumberOfMinionsInHand() {
		return _numOfPlayerMinions;
	}
	
	/**
	 * Initializes the number of minions in the player's possession, i.e. not on the board.
	 * @param minionCount Number of minions.
	 * @throws InvalidOperationException Thrown when the number of minions is not in [0, 12]
	 */
	public void setMinion(int minionCount) throws InvalidOperationException {
		if (minionCount < 0 || minionCount > 12)
			throw new InvalidOperationException("Number of minions must be valid.");
		
		_numOfPlayerMinions = minionCount;
	}
	
	/**
	 * Gets the number of buildings in the player's possession, i.e. not on the board.
	 */
	public int getNumberOfBuildingsInHand() {
		return _numOfPlayerBuildings;
	}
   
	/**
	 * Initializes the number of buildings in the player's hand.
	 * @param buildingCount Number of buildings in the player' hand after initialization.
	 * @throws InvalidOperationException Thrown when the number of buildings is not in [0, 6]
	 */
	public void setNumberBuildings(int buildingCount) throws InvalidOperationException {
		if (buildingCount < 0 || buildingCount > 6)
			throw new InvalidOperationException("Number of buildings must be valid.");
	   
		this._numOfPlayerBuildings = buildingCount;
	}
	
	/**
	 * Gets the name of the player's personality card as supplied by the game manager.
	 */
	public PersonalityCardName getPersonality() {
		return _playerPersonality;
	}
	
	private void setPersonality(String personality) throws InvalidOperationException {
		if (personality.contains(";"))
			throw new InvalidOperationException("The personality card name cannot contain the semicolon character.");
		
		_playerPersonality = PersonalityCardName.valueOf(personality);
	}
	
	/**
	 * Initializes the personality card in the player's possession.
	 * @param personality Name of the card as recognizable by the game manager.
	 */
	public void setPersonality(PersonalityCardName personality) {
		_playerPersonality = personality;
	}
	
	@Override
	public String getAccountHolderName()
	{
		return _playerName;
	}
	
	/**
	 * Gets the money currently in the player's hand.
	 */
	@Override
	public int getMoney() {
		return _playerMoney;
	}
	
	/**
	 * Initializes the money in the player's hand.
	 * @param money Amount in the player's hand after initialization.
	 * @throws InvalidOperationException Thrown when money is not in [0, 120].
	 */
	public void setPlayerMoney(int money) throws InvalidOperationException {
		if (money < 0 || money > 120)
			throw new InvalidOperationException("Amount of money must be valid.");
	   
		_playerMoney = money;
	}
	
	@Override
	public void addMoney(int amount) throws InvalidOperationException {
		setPlayerMoney(_playerMoney + amount);
	}
	
	@Override
	public void removeMoney(int amount) throws InvalidOperationException {
		setPlayerMoney(_playerMoney - amount);
	}
	
	/**
	 * Gets the list of player cards in the current player's hand.
	 * @return List of card names, as recognizable by the game manager.
	 */
	public List<PlayerCardName> getPlayerCards() {
		// TODO
		return null;
	}
	
	/**
	 * Initializes the set of player cards in the current player's hand.
	 * @param cardNames List of card names, as recognizable by the game manager.
	 * @throws InvalidOperationException Thrown when at least one card name contains a semicolon character.
	 */
	public void setPlayerCards(ArrayList<String> cardNames) throws InvalidOperationException {
		_playerCards = new LinkedList<PlayerCardName>();
		for (String name : cardNames) {
			if (name.contains(";"))
				throw new InvalidOperationException("Card names cannot contain the semicolon character.");
			
			_playerCards.add(PlayerCardName.valueOf(name));
		}
	}
	/**
	 * Serializes the current player's state.
	 */
	public String getCurrentState() {
		String currentState =
			valueOrEmpty(_playerName) + ";"
			+ valueOrEmpty(_playerColor) + ";"
			+ _numOfPlayerBuildings + ";"
			+ _numOfPlayerMinions + ";"
			+ valueOrEmpty(_playerPersonality.getValue()) + ";"
			+ _playerMoney;
		
		for (PlayerCardName name : _playerCards)
			currentState += ";" + valueOrEmpty(name.getValue());
		
		return currentState;
	}
	
	private String valueOrEmpty(String value) {
		return value == null ? "" : value;
	}
	
	/**
	 * Loads the player's state from serialized data.
	 * @see getCurrentState
	 * @param serializedData Current state, as retrieved from the <code>getCurrentState</code> method.
	 * @throws NumberFormatException Thrown when the serialized data is invalid.
	 * @throws InvalidOperationException Thrown when the serialized data is invalid.
	 */
	public void setCurrentState(String serializedData) throws NumberFormatException, InvalidOperationException {
		if (serializedData == null)
			throw new InvalidOperationException("Serialized data cannot be null.");
		
		String[] serializedParts = serializedData.split(";");
		if (serializedParts.length < 6 || serializedParts.length > 106)
			throw new InvalidOperationException("Invalid serialized data.");
		
		_playerName = serializedParts[0];
		_playerColor = serializedParts[1];
		_numOfPlayerBuildings = Integer.parseInt(serializedParts[2]);
		_numOfPlayerMinions = Integer.parseInt(serializedParts[3]);
		setPersonality(serializedParts[4]);
		_playerMoney = Integer.parseInt(serializedParts[5]);
		_playerCards = new LinkedList<PlayerCardName>();
		for (int i = 6; i < serializedParts.length; i++)
			_playerCards.add(PlayerCardName.valueOf(serializedParts[i]));
	}

	public int getIndex() {
		return _playerIndex;
	}

	public void decrementMinionsBy(int count) {
		_numOfPlayerMinions -= count;
		
	}

	public void incrementNumberOfMinionsBy(int count) {
		_numOfPlayerMinions += count;
	}

	@Override
	public CityAreaData[] getCityCards() {
		return _cityCards.toArray(new CityAreaData[_cityCards.size()]);
	}

	@Override
	public void addPlayerCard(PlayerCardName playerCard) {
		// TODO Auto-generated method stub
		_playerCards.add(playerCard);
	}

	@Override
	public void returnMinionsToHand(int numberMinions) {
		// TODO Basic sanity checks
		this._numOfPlayerMinions += numberMinions;
	}

	@Override
	public List<ICityArea> getPopulatedAreas(IGameInstance gameInstance) {
		List<ICityArea> currentlyPopulatedAreas = new LinkedList<ICityArea>();
		for (CityAreaData area : CityAreaData.values()) {
			ICityArea currentArea = gameInstance.getMapArea(area);
			if (currentArea.getMinions()[_playerIndex] > 0)
				currentlyPopulatedAreas.add(currentArea);
		}
//		
		return currentlyPopulatedAreas;
	}

	@Override
	public void removeMinionsFromHand(int numberMinions) {
		// TODO Basic sanity checks
		this._numOfPlayerMinions -= numberMinions;
	}
}
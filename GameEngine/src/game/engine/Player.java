package game.engine;

import game.error.InvalidOperationException;

import java.util.ArrayList;

public class Player implements IMoneyHolder {
	private int playerIndex;
	private String playerName;
	private String playerColor;
	private int numOfPlayerBuildings;
	private int numOfPlayerMinions = 12;
	private String playerPersonality;
	private int playerMoney;
	private ArrayList<String> playerCards;
	
	public Player(int index) {
		playerIndex = index;
	}

	/**
	 * Gets the user-friendly player name.
	 */
    public String getName() {
		return playerName;
	}
	
    /**
     * Sets the user-friendly player name, intended for display purposes.
     * @param name Player's name
     * @throws InvalidOperationException Thrown when the name contains a semicolon character.
     */
    public void setName(String name) throws InvalidOperationException {
    	if (name.contains(";"))
    		throw new InvalidOperationException("Player name cannot contain the semicolon character.");
    	
		this.playerName = name;
	}
	
    /**
     * Gets the player color, as set by the game manager.
     * @return The game manager-supplied player color.
     */
	public String getplayercolor() {
		return playerColor;
	}
	
	/**
	 * Allows the game manager to initialize the player's color.
	 * @param playercolor Name of the color, as can later be recognized by the game manager.
	 * @throws InvalidOperationException Thrown when the color contains a semicolon character.
	 */
	public void setplayercolor(String playercolor) throws InvalidOperationException {
		if (playerColor.contains(";"))
			throw new InvalidOperationException("The player color cannot contain the semicolon character.");
		
		this.playerColor = playercolor;
	}
	
	/**
	 * Gets the number of minions currently in the player's possession, i.e. not on the board.
	 */
	public int getNumberOfMinionsInHand() {
		return numOfPlayerMinions;
	}
	
	/**
	 * Initializes the number of minions in the player's possession, i.e. not on the board.
	 * @param minionCount Number of minions.
	 * @throws InvalidOperationException Thrown when the number of minions is not in [0, 12]
	 */
	public void setMinion(int minionCount) throws InvalidOperationException {
		if (minionCount < 0 || minionCount > 12)
			throw new InvalidOperationException("Number of minions must be valid.");
		
		numOfPlayerMinions = minionCount;
	}
	
	/**
	 * Gets the number of buildings in the player's possession, i.e. not on the board.
	 */
	public int getNumberOfBuildingsInHand() {
		return numOfPlayerBuildings;
	}
   
	/**
	 * Initializes the number of buildings in the player's hand.
	 * @param buildingCount Number of buildings in the player' hand after initialization.
	 * @throws InvalidOperationException Thrown when the number of buildings is not in [0, 6]
	 */
	public void setNumberBuildings(int buildingCount) throws InvalidOperationException {
		if (buildingCount < 0 || buildingCount > 6)
			throw new InvalidOperationException("Number of buildings must be valid.");
	   
		this.numOfPlayerBuildings = buildingCount;
	}
	
	/**
	 * Gets the name of the player's personality card as supplied by the game manager.
	 */
	public String getPersonality() {
		return playerPersonality;
	}
	
	/**
	 * Initializes the personality card in the player's possession.
	 * @param personality Name of the card as recognizable by the game manager.
	 * @throws InvalidOperationException Thrown when the name contains a semicolon.
	 */
	public void setPersonality(String personality) throws InvalidOperationException {
		if (personality.contains(";"))
			throw new InvalidOperationException("The personality card name cannot contain the semicolon character.");
		
		this.playerPersonality = personality;
	}
	
	@Override
	public String getAccountHolderName()
	{
		return playerName;
	}
	
	/**
	 * Gets the money currently in the player's hand.
	 */
	@Override
	public int getMoney() {
		return playerMoney;
	}
	
	/**
	 * Initializes the money in the player's hand.
	 * @param money Amount in the player's hand after initialization.
	 * @throws InvalidOperationException Thrown when money is not in [0, 120].
	 */
	public void setPlayerMoney(int money) throws InvalidOperationException {
		if (money < 0 || money > 120)
			throw new InvalidOperationException("Amount of money must be valid.");
	   
		playerMoney = money;
	}
	
	@Override
	public void addMoney(int amount) throws InvalidOperationException {
		setPlayerMoney(playerMoney + amount);
	}
	
	@Override
	public void removeMoney(int amount) throws InvalidOperationException {
		setPlayerMoney(playerMoney - amount);
	}
	
	/**
	 * Gets the list of player cards in the current player's hand.
	 * @return List of card names, as recognizable by the game manager.
	 */
	public ArrayList<String> getPlayerCards() {
		return playerCards;
	}
	
	/**
	 * Initializes the set of player cards in the current player's hand.
	 * @param cardNames List of card names, as recognizable by the game manager.
	 * @throws InvalidOperationException Thrown when at least one card name contains a semicolon character.
	 */
	public void setPlayerCards(ArrayList<String> cardNames) throws InvalidOperationException {
		for (String name : cardNames)
			if (name.contains(";"))
				throw new InvalidOperationException("Card names cannot contain the semicolon character.");
		
		this.playerCards = cardNames;
	}
	
	/**
	 * Serializes the current player's state.
	 */
	public String getCurrentState() {
		String currentState =
			valueOrEmpty(playerName) + ";"
			+ valueOrEmpty(playerColor) + ";"
			+ numOfPlayerBuildings + ";"
			+ numOfPlayerMinions + ";"
			+ valueOrEmpty(playerPersonality) + ";"
			+ playerMoney;
		
		for (String name : playerCards)
			currentState += ";" + valueOrEmpty(name);
		
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
		
		playerName = serializedParts[0];
		playerColor = serializedParts[1];
		numOfPlayerBuildings = Integer.parseInt(serializedParts[2]);
		numOfPlayerMinions = Integer.parseInt(serializedParts[3]);
		playerPersonality = serializedParts[4];
		playerMoney = Integer.parseInt(serializedParts[5]);
		playerCards = new ArrayList<String>();
		for (int i = 6; i < serializedParts.length; i++)
			playerCards.add(serializedParts[i]);
	}

	public int getIndex() {
		return playerIndex;
	}

	public void decrementMinionsBy(int count) {
		numOfPlayerMinions -= count;
		
	}

	public void incrementNumberOfMinionsBy(int count) {
		numOfPlayerMinions += count;
	}
}
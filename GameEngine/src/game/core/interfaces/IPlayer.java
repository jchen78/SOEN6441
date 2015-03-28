package game.core.interfaces;

import game.action.sequence.interfaces.IVisitee;
import game.core.enums.CityAreaData;
import game.core.enums.PersonalityCardName;
import game.core.enums.PlayerCardName;
import game.error.InvalidOperationException;

/**
 * The class represents a player. As such, it will contain
 * the data relevant to the player. The <code>accept</code> method will initiate 
 * the player's turn --i.e., the player will choose one
 * player card, then perform all actions required (choose to
 * play the next icon on the card if applicable, or play a city
 * card if applicable).
 */
public interface IPlayer extends IVisitee {

	/**
	 * Gets the user-friendly player name.
	 */
	public String getName();

    /**
     * Sets the user-friendly player name, intended for display purposes.
     * @param name Player's name
     * @throws InvalidOperationException Thrown when the name contains a semicolon character.
     */
	void setName(String name) throws InvalidOperationException;

	public int getIndex();

	public int getMoney();

	public void addMoney(int amount) throws InvalidOperationException;

	public void removeMoney(int _amount) throws InvalidOperationException;

	/**
	 * Gets all player cards in the current player's hand.
	 * @return Array of card names, as recognizable by the game manager. This structure is not a reference to internal data.
	 */
	public PlayerCardName[] getPlayerCards();

	public CityAreaData[] getCityCards();

	public void addPlayerCard(PlayerCardName drawPlayerCard);

	/**
	 * Gets the number of minions currently in the player's possession, i.e. not on the board.
	 */
	public int getNumberOfMinionsInHand();

	public void returnMinionsToHand(int numberMinions) throws IllegalArgumentException;
	
	public void removeMinionsFromHand(int numberMinions) throws IllegalArgumentException;
	
	/**
	 * Gets the number of buildings in the player's possession, i.e. not on the board.
	 */
	public int getNumberOfBuildingsInHand();

	public ICityArea[] getPopulatedAreas(IGameInstance gameInstance);

	/**
	 * Loads the player's state from serialized data.
	 * @see getCurrentState
	 * @param serializedData Current state, as retrieved from the <code>getCurrentState</code> method.
	 * @throws NumberFormatException Thrown when the serialized data is invalid.
	 * @throws InvalidOperationException Thrown when the serialized data is invalid.
	 */
	void setCurrentState(String serializedData) throws NumberFormatException, InvalidOperationException;

	/**
	 * Serializes the current player's state.
	 */
	String getCurrentState();

	/**
	 * Initializes the personality card in the player's possession.
	 * @param personality Name of the card as recognizable by the game manager.
	 */
	void setPersonality(PersonalityCardName personality);

	/**
	 * Gets the name of the player's personality card as supplied by the game manager.
	 */
	PersonalityCardName getPersonality();

    /**
     * Gets the player color, as set by the game manager.
     * @return The game manager-supplied player color.
     */
	String getplayercolor();

	/**
	 * Allows the game manager to initialize the player's color.
	 * @param playercolor Name of the color, as can later be recognized by the game manager.
	 * @throws InvalidOperationException Thrown when the color contains a semicolon character.
	 */
	void setplayercolor(String playercolor) throws InvalidOperationException;
}

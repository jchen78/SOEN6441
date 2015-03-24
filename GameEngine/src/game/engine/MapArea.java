package game.engine;

import game.action.sequence.interfaces.IVisitor;
import game.action.sequence.visitee.GameOverException;
import game.core.enums.CityAreaData;
import game.core.interfaces.ICityArea;
import game.core.interfaces.IPlayer;
import game.error.InvalidEntityNameException;
import game.error.InvalidOperationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class represents the entity of a game board area.
 */
public class MapArea implements IEntity, ICityArea {
	private CityAreaData _cardStats;
	private int _number;
	private Integer _buildingOwner;
	private boolean _isTroubleMarkerSet;
	private int _numberDemons;
	private int _numberTrolls;
	private int[] _minions = new int[4]; //indicates how many minions each player has in that area
	private List<MapArea> adjacentMapAreas = new ArrayList();
	/**
	 * Constructor: Initializes data structures for loading.
	 */
	public MapArea() {
		this._buildingOwner = null;
	}
    
	/**
	 * Returns true if it should set the trouble marker
	 * @return true if it should set the trouble marker
	 */
	private boolean shouldSetTroubleMarker() {
		int totalNumberMinions = _numberTrolls + _numberDemons;
		if (totalNumberMinions > 1)
			return true;
		
		for (int i = 0; i < 4; i++)
			totalNumberMinions += _minions[i];
		
		return totalNumberMinions > 1;
	}
	
	/**
	 * User-friendly name of the gameboard area represented by the current instance.
	 * @return User-friendly name (not the entity name).
	 */
	public String getName() {
		return _cardStats.getText();
	}
	
	/**
	 * Identifies the gameboard area by number.
	 * @return Gameboard area number.
	 */
	public int getNumber() {
		return _number;
	}
	
	/**
	 * Represents the cost of a building.
	 * @return Cost for constructing a building on the gameboard area represented by this instance.
	 */
	public int getBuildingCost() {
		return _cardStats.getBuildingCost();
	}
	
	/**
	 * Represents whether a trouble marker is currently set on the gameboard area represented by this instance.
	 * @return True if a trouble marker is currently set, false otherwise.
	 */
	public boolean hasTroubleMarker() {
		return _isTroubleMarkerSet;
	}
	
	/**
	 * Gets the number of demons on the gameboard area represented by this instance.
	 * @return The number of demons in the current gameboard area.
	 */
	public int getNumberDemons() {
		return _numberDemons;
	}
	
	/**
	 * Modifies the number of demons on the gameboard area represented by this instance.
	 * @param numberDemons The total number of demons on this board area after the operation. 
	 * @throws InvalidOperationException Thrown when the number of demons placed exceeds the total number of demon pieces in the game or is otherwise invalid (negative).
	 */
	public void setNumberDemons(int numberDemons) throws InvalidOperationException {
		if (numberDemons < 0 || numberDemons > 4)
			throw new InvalidOperationException("The number of demons must be valid.");
		
		if (numberDemons < _numberDemons)
			_isTroubleMarkerSet = false;
		else
			_isTroubleMarkerSet = shouldSetTroubleMarker();
		
		this._numberDemons = numberDemons;
	}
	
	/**
	 * Gets the number of trolls on the gameboard area represented by this instance.
	 * @return The number of trolls in the current gameboard area.
	 */
	public int getNumberTrolls() {
		return _numberTrolls;
	}
	
	/**
	 * Modifies the number of trolls on the gameboard area represented by this instance.
	 * @param numberTrolls The total number of trolls on this board area after the operation.
	 * @throws InvalidOperationException Thrown when the number of trolls exceeds the total number of troll pieces in the game or is otherwise invalid (negative).
	 */
	public void setNumberTrolls(int numberTrolls) throws InvalidOperationException {
		if (numberTrolls < 0 || numberTrolls > 3)
			throw new InvalidOperationException("The number of trolls must be valid.");
		
		if (numberTrolls < this._numberTrolls)
			_isTroubleMarkerSet = false;
		else
			_isTroubleMarkerSet = shouldSetTroubleMarker();
			
		this._numberTrolls = numberTrolls;
	}
	
	/**
	 * Gets the number of minions for each player.
	 * @return An array containing the number of minions by player, where the player index corresponds to the array index.
	 */
	public int[] getMinions() {
		return _minions;
	}

	private void performValidationForPlayerID(int playerID)	throws InvalidOperationException {
		if (playerID < 0 || playerID > 3)
			throw new InvalidOperationException("Player ID must be valid.");
	}
	
	/**
	 * Add and Remove Building for a particular playerID in a area.
	 * @playerID Index of the player (0-3, inclusive).
	 * @throws InvalidOperationException Thrown when the player ID is invalid or when a trouble marker is already set.
	 */
	public void addBuilding (int playerID) throws InvalidOperationException {
		performValidationForPlayerID(playerID);
		if (_isTroubleMarkerSet)
			throw new InvalidOperationException("Cannot add building while trouble marker is set.");
		
		_buildingOwner = playerID;
	}
	
	/**
	 * Gets the ID of the player owning the building, if any.
	 * @return null if no building is constructed, or the index of the owning player otherwise.
	 */
	public Integer getBuildingOwner() {
		// TODO Auto-generated method stub
		return _buildingOwner;
	}

	@Override
	public void setEntity(String entityName) throws InvalidEntityNameException {
		try {
			_cardStats = CityAreaData.valueOf(entityName);
		}
		catch (IllegalArgumentException e) {
			throw new InvalidEntityNameException();
		}
		
		// TODO: set actions acccording to _cardStats.
	}
	
	/**
	 * Represents the dynamic data of the current instance.
	 * @see setCurrentState
	 * @return Serializes the mutable data as a String.
	 */
	public String getCurrentState() {
		String serializedData = "";
		if (_isTroubleMarkerSet)
			serializedData = "1;-1;";
		else
			serializedData = "0;" + _buildingOwner + ";";
		
		serializedData += _minions[0] + ";" + _minions[1] + ";" + _minions[2] + ";" + _minions[3] + ";";
		serializedData += _numberDemons + ";";
		serializedData += _numberTrolls;
		
		return serializedData;
	}
	
	/**
	 * Sets the instance to the data specified by the string, as retrieved via getCurrentState.
	 * @see getCurrentState
	 * @param serializedData String representing the saved state of a gameboard area instance.
	 * @throws InvalidOperationException Thrown when the serialized data is in an invalid format or contains invalid data.
	 */
	public void setCurrentState(String serializedData, GameManager gameManager) throws InvalidOperationException {
		if (serializedData == null)
			throw new InvalidOperationException("Serialized data must be valid.");
		
		String[] dataParts = serializedData.split(";");
		if (dataParts.length != 8)
			throw new InvalidOperationException("Serialized data must be valid.");
		
		int[] formattedDataParts = new int[8];
		for (int i = 0; i < 8; i++)
			try {
				formattedDataParts[i] = Integer.parseInt(dataParts[i]);
			} catch (Exception e) {
				throw new InvalidOperationException("Serialized data must be valid.");
			}
		
		_isTroubleMarkerSet = formattedDataParts[0] == 1;
		if (formattedDataParts[1] == -1)
			_buildingOwner = null;
		else
			addBuilding(formattedDataParts[1]);
		
		_minions = new int[4];
		addMinions(gameManager.getPlayer(0), formattedDataParts[2]);
		addMinions(gameManager.getPlayer(1), formattedDataParts[3]);
		addMinions(gameManager.getPlayer(2), formattedDataParts[4]);
		addMinions(gameManager.getPlayer(3), formattedDataParts[5]);
		
		setNumberDemons(formattedDataParts[6]);
		setNumberTrolls(formattedDataParts[7]);
	}

	public void removeMinions(IPlayer player, int count ) throws InvalidOperationException {
		player.returnMinionsToHand(1);
		_minions[player.getIndex()]--;
		_isTroubleMarkerSet = false;
	}
	
	/**
	 * @param 
	 * @return
	 */
	public boolean isAdjacent(String areaName) {
		return false;
	}

	/**
	 * @param mapArea= MapArea object 
	 */
	public void addAdjacentArea(MapArea mapArea) {
		adjacentMapAreas.add(mapArea);
		
	}

	@Override
	public void accept(IVisitor visitor) throws GameOverException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityAreaData getCityAreaName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CardType getCardType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAdjacent(CityAreaData areaName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addMinions(IPlayer currentPlayer, int numberMinions) throws InvalidOperationException {
		if (numberMinions < 0 || numberMinions > 12)
			throw new InvalidOperationException("The number of minions must be valid.");
		
		currentPlayer.removeMinionsFromHand(numberMinions);
		_minions[currentPlayer.getIndex()] += numberMinions;
		_isTroubleMarkerSet = shouldSetTroubleMarker();
	}
}
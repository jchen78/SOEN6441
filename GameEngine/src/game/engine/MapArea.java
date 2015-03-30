package game.engine;

import game.action.sequence.interfaces.IVisitor;
import game.action.sequence.visitee.GameOverException;
import game.core.enums.CityAreaData;
import game.core.interfaces.ICityArea;
import game.core.interfaces.IGameInstance;
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
	private String _buildingOwner;
	private boolean _isTroubleMarkerSet;
	private int _numberDemons;
	private int _numberTrolls;
	private int[] _minions = new int[4]; //indicates how many minions each player has in that area
	private List<MapArea> adjacentMapAreas = new ArrayList<MapArea>();
	/**
	 * Constructor: Initializes data structures for loading.
	 */
	public MapArea() {
		this._buildingOwner = null;
	}
    
	public MapArea(String initialState) throws InvalidOperationException {
		setCurrentState(initialState);
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
		return _cardStats.getDescriptiveName();
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
	
	/**
	 * Add a building owned by the specified player.
	 * @playerName Name of the building owner.
	 * @throws InvalidOperationException Thrown when a trouble marker is already set.
	 */
	public void addBuilding (String playerName) throws InvalidOperationException {
		if (_isTroubleMarkerSet)
			throw new InvalidOperationException("Cannot add building while trouble marker is set.");
		
		_buildingOwner = playerName;
	}
	
	/**
	 * Gets the ID of the player owning the building, if any.
	 * @return null if no building is constructed, or the index of the owning player otherwise.
	 */
	public String getBuildingOwner() {
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
			serializedData = "1;";
		else
			serializedData = "0;";
		
		serializedData += _minions[0] + ";" + _minions[1] + ";" + _minions[2] + ";" + _minions[3] + ";";
		serializedData += _numberDemons + ";";
		serializedData += _numberTrolls;
		serializedData += (_buildingOwner == null ? "" : _buildingOwner) + ";";
		
		return serializedData;
	}
	
	@Override
	public void setCurrentState(String serializedData) throws InvalidOperationException {
		if (serializedData == null)
			throw new InvalidOperationException("Serialized data must be valid.");
		
		String[] dataParts = serializedData.split(";");
		if (dataParts.length < 7 || dataParts.length > 8)
			throw new InvalidOperationException("Serialized data must be valid.");
		
		_cardStats = CityAreaData.valueOf(dataParts[0]);
		
		int[] formattedDataParts = new int[7];
		for (int i = 0; i < 7; i++)
			try {
				formattedDataParts[i] = Integer.parseInt(dataParts[i + 1]);
			} catch (Exception e) {
				throw new InvalidOperationException("Serialized data must be valid.");
			}
		
		_isTroubleMarkerSet = formattedDataParts[0] == 1;
	
		_minions = new int[4];
		_minions[0] = formattedDataParts[1];
		_minions[1] = formattedDataParts[2];
		_minions[2] = formattedDataParts[3];
		_minions[3] = formattedDataParts[4];
		
		setNumberDemons(formattedDataParts[5]);
		setNumberTrolls(formattedDataParts[6]);
		
		if (dataParts.length == 8 && dataParts[7] != null && dataParts[7].length() > 0)
			addBuilding(dataParts[7]);
	}
	
	@Override
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
		return _cardStats.getDescriptiveName();
	}

	@Override
	public CityAreaData getCityAreaName() {
		return _cardStats;
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
		
		addMinions(currentPlayer.getIndex(), numberMinions);
	}
	
	private void addMinions(int playerIndex, int numberMinions) {
		_minions[playerIndex] += numberMinions;
		_isTroubleMarkerSet = shouldSetTroubleMarker();
	}
}
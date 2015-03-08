package game.engine;

import game.error.InvalidEntityNameException;
import game.error.InvalidOperationException;

import java.util.HashMap;

/**
 * This class represents the entity of a game board area.
 */
public class MapArea implements IEntity {
	private static HashMap<String, Integer> INTERNAL_MAPAREA_NAMES = new HashMap<String, Integer>();
	private static String[] DISPLAY_NAMES = new String[12];
	private static int[] BUILDING_COSTS = new int[12];
	
	static {
		INTERNAL_MAPAREA_NAMES.put("DollySisters", 1);
		INTERNAL_MAPAREA_NAMES.put("UnrealEstate", 2);
		INTERNAL_MAPAREA_NAMES.put("DragonsLanding", 3);
		INTERNAL_MAPAREA_NAMES.put("SmallGods", 4);
		INTERNAL_MAPAREA_NAMES.put("TheScours", 5);
		INTERNAL_MAPAREA_NAMES.put("TheHippo", 6);
		INTERNAL_MAPAREA_NAMES.put("TheShades", 7);
		INTERNAL_MAPAREA_NAMES.put("Dimwell", 8);
		INTERNAL_MAPAREA_NAMES.put("Longwall", 9);
		INTERNAL_MAPAREA_NAMES.put("IsleOfGods", 10);
		INTERNAL_MAPAREA_NAMES.put("SevenSleepers", 11);
		INTERNAL_MAPAREA_NAMES.put("NapHill", 12);
		
		DISPLAY_NAMES[0] = "Dolly Sisters";
		DISPLAY_NAMES[1] = "Unreal Estate";
		DISPLAY_NAMES[2] = "Dragon's Landing";
		DISPLAY_NAMES[3] = "Small Gods";
		DISPLAY_NAMES[4] = "The Scours";
		DISPLAY_NAMES[5] = "The Hippo";
		DISPLAY_NAMES[6] = "The Shades";
		DISPLAY_NAMES[7] = "Dimwell";
		DISPLAY_NAMES[8] = "Longwall";
		DISPLAY_NAMES[9] = "Isle Of Gods";
		DISPLAY_NAMES[10] = "Seven Sleepers";
		DISPLAY_NAMES[11] = "Nap Hill";
		
		BUILDING_COSTS[0] = 6;
		BUILDING_COSTS[1] = 18;
		BUILDING_COSTS[2] = 12;
		BUILDING_COSTS[3] = 18;
		BUILDING_COSTS[4] = 6;
		BUILDING_COSTS[5] = 12;
		BUILDING_COSTS[6] = 6;
		BUILDING_COSTS[7] = 6;
		BUILDING_COSTS[8] = 12;
		BUILDING_COSTS[9] = 12;
		BUILDING_COSTS[10] = 18;
		BUILDING_COSTS[11] = 12;
	}
	
	/**
	 * Gets the set of all valid entity names for the game board.
	 * @return An array of gameboard area entity names, in no particular order.
	 */
	public static String[] getInternalNames() {
		return INTERNAL_MAPAREA_NAMES.keySet().toArray(new String[12]);
	}
	
	private int _number;
	private Integer _buildingOwner;
	private boolean _isTroubleMarkerSet;
	private int _numberDemons;
	private int _numberTrolls;
	private int[] _minions = new int[4]; //indicates how many minions each player has in that area
	
	/**
	 * Constructor: Initializes data structures for loading.
	 */
	public MapArea() {
		this._buildingOwner = null;
	}
    
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
		return DISPLAY_NAMES[_number - 1];
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
		return BUILDING_COSTS[_number - 1];
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
	 * Adds the specified number of minions for the player identified.
	 * @param playerID Index of the player (0-3, inclusive).
	 * @param count Number of minions to add (0-12, inclusive).
	 * @throws InvalidOperationException Thrown when the playerID or the count is invalid.
	 */
	public void addMinions(int playerID, int count) throws InvalidOperationException {
		performValidationForPlayerID(playerID);
		if (count < 0 || count > 12)
			throw new InvalidOperationException("The number of minions must be valid.");
		
		_minions[playerID] += count;
		_isTroubleMarkerSet = shouldSetTroubleMarker();
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
		if (!INTERNAL_MAPAREA_NAMES.containsKey(entityName))
			throw new InvalidEntityNameException();
		
		_number = INTERNAL_MAPAREA_NAMES.get(entityName);
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
	public void setCurrentState(String serializedData) throws InvalidOperationException {
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
		addMinions(0, formattedDataParts[2]);
		addMinions(1, formattedDataParts[3]);
		addMinions(2, formattedDataParts[4]);
		addMinions(3, formattedDataParts[5]);
		
		setNumberDemons(formattedDataParts[6]);
		setNumberTrolls(formattedDataParts[7]);
	}

	public void removeMinion(int playerIndex) throws InvalidOperationException {
		if (playerIndex < 0 || playerIndex > 3 || _minions[playerIndex] <= 0)
			throw new InvalidOperationException("No minions may be removed for the player specified.");
		
		_minions[playerIndex]--;
		_isTroubleMarkerSet = false;
	}
	
	// TODO
	public boolean isAdjacent(String areaName) {
		return false;
	}
}
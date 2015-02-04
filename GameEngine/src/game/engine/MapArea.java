package game.engine;

import game.error.InvalidEntityNameException;
import game.error.InvalidOperationException;

import java.util.HashMap;

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
	
	public static String[] getInternalNames() {
		return (String[])INTERNAL_MAPAREA_NAMES.keySet().toArray();
	}
	
	private int _number;
	private Integer _buildingOwner;
	private boolean _isTroubleMarkerSet;
	private int _bankBal=10;
	private int _numberDemons;
	private int _numberTrolls;
	private int[] _minions = new int[4]; //indicates how many minions each player has in that area
	
	public MapArea() {
		this._buildingOwner = null;
	}
    
	public String getName() {
		return DISPLAY_NAMES[_number - 1];
	}

	public int getNumber() {
		return _number;
	}

	public int getBuildingCost() {
		return BUILDING_COSTS[_number - 1];
	}

	public boolean hasTroubleMarker() {
		return _isTroubleMarkerSet;
	}

	public void setTroubleMarker(boolean troubleMarker) throws InvalidOperationException {
		if (_buildingOwner != null && troubleMarker)
			throw new InvalidOperationException("Cannot set trouble marker when a building is constructed.");
		
		this._isTroubleMarkerSet = troubleMarker;
	}

	public int getNumberDemons() {
		return _numberDemons;
	}

	public void setNumberDemons(int numberDemons) throws InvalidOperationException {
		if (numberDemons < 0 || numberDemons > 4)
			throw new InvalidOperationException("The number of demons must be valid.");
		this._numberDemons = numberDemons;
	}

	public int getNumberTrolls() {
		return _numberTrolls;
	}

	public void setNumberTrolls(int numberTrolls) throws InvalidOperationException {
		if (numberTrolls < 0 || numberTrolls > 3)
			throw new InvalidOperationException("The number of trolls must be valid.");
		
		this._numberTrolls = numberTrolls;
	}
	
	public int getBankBal() {
		return _bankBal;
	}

	public void setBankBal(int bankBal) {
		_bankBal = bankBal;
	}
	public int[] getMinions() {
		return _minions;
	}

	public void addMinions(int playerID, int count) throws InvalidOperationException {
		performValidationForPlayerID(playerID);
		if (count < 0 || count > 12)
			throw new InvalidOperationException("The number of minions must be valid.");
		
		_minions[playerID] += count;
	}

	private void performValidationForPlayerID(int playerID)	throws InvalidOperationException {
		if (playerID < 0 || playerID > 3)
			throw new InvalidOperationException("Player ID must be valid.");
	}
	
	/*
	 * Add and Remove Building for a particular playerID in a area
	 */
	public void addBuilding (int playerID) throws InvalidOperationException {
		performValidationForPlayerID(playerID);
		if (_isTroubleMarkerSet)
			throw new InvalidOperationException("Cannot add building while trouble marker is set.");
		
		_buildingOwner = playerID;
	}

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
}
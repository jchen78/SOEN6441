package game.engine;

import game.error.InvalidEntityNameException;

import java.util.HashMap;

public class CityAreaCard extends Card {
	private static final HashMap<String, String> CITY_AREA_CARD_NAMES = new HashMap<String, String>();
	
	static {
		CITY_AREA_CARD_NAMES.put("DollySisters", "Dolly Sisters");
		CITY_AREA_CARD_NAMES.put("UnrealEstate", "Unreal Estate");
		CITY_AREA_CARD_NAMES.put("DragonsLanding", "Dragon's Landing");
		CITY_AREA_CARD_NAMES.put("SmallGods", "Small Gods");
		CITY_AREA_CARD_NAMES.put("TheScours", "The Scours");
		CITY_AREA_CARD_NAMES.put("TheHippo", "The Hippo");
		CITY_AREA_CARD_NAMES.put("TheShades", "The Shades");
		CITY_AREA_CARD_NAMES.put("Dimwell", "Dimwell");
		CITY_AREA_CARD_NAMES.put("Longwall", "Longwall");
		CITY_AREA_CARD_NAMES.put("IsleOfGods", "Isle of Gods");
		CITY_AREA_CARD_NAMES.put("SevenSleepers", "Seven Sleepers");
		CITY_AREA_CARD_NAMES.put("NapHill", "Nap Hill");
	}
	
	public static String[] getCityAreaCardNames() {
		return (String[])CITY_AREA_CARD_NAMES.keySet().toArray();
	}
	
	@Override
	public void setEntity(String entityName) throws InvalidEntityNameException {
		if (!CITY_AREA_CARD_NAMES.containsKey(entityName))
			throw new InvalidEntityNameException();
		
		super.setEntity(entityName);
	}
	
	@Override
	public String getCardname() {
		return CITY_AREA_CARD_NAMES.get(_cardName);
	}
}

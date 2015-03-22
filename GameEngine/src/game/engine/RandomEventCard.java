package game.engine;

import game.core.interfaces.IRandomEventCard;
import game.error.InvalidEntityNameException;

import java.util.HashMap;

/**
 * This class represents entities from the Random Event card deck.
 */
public class RandomEventCard extends Card implements IRandomEventCard {
	private static final HashMap<String, String> RANDOM_EVENT_CARD_NAMES = new HashMap<String, String>();
	
	static {
		RANDOM_EVENT_CARD_NAMES.put("Fire", "Fire");
		RANDOM_EVENT_CARD_NAMES.put("Fog", "Fog");
		RANDOM_EVENT_CARD_NAMES.put("MysteriousMurders", "Mysterious Murders");
		RANDOM_EVENT_CARD_NAMES.put("Riots", "Riots");
		RANDOM_EVENT_CARD_NAMES.put("Subsidence", "Subsidence");
		RANDOM_EVENT_CARD_NAMES.put("Trolls", "Trolls");
		RANDOM_EVENT_CARD_NAMES.put("BloodyStupidJohnson", "Bloody Stupid Johnson");
		RANDOM_EVENT_CARD_NAMES.put("DemonsFromTheDungeonDimensions", "Demons from the Dungeon Dimensions");
		RANDOM_EVENT_CARD_NAMES.put("TheDragon", "The Dragon");
		RANDOM_EVENT_CARD_NAMES.put("Earthquake", "Earthquake");
		RANDOM_EVENT_CARD_NAMES.put("Explosion", "Explosion");
		RANDOM_EVENT_CARD_NAMES.put("Flood", "Flood");
	}
	
	/**
	 * Gets the set of all valid entity names for the Random Event card deck.
	 * @return An array of valid names, with no guarantees on order.
	 */
	public static String[] getRandomEventCardNames() {
		return RANDOM_EVENT_CARD_NAMES.keySet().toArray(new String[12]);
	}
	
	@Override
	public void setEntity(String entityName) throws InvalidEntityNameException {
		if (!RANDOM_EVENT_CARD_NAMES.containsKey(entityName))
			throw new InvalidEntityNameException();
		
		super.setEntity(entityName);
	}
	
	@Override
	public String getCardname() {
		return RANDOM_EVENT_CARD_NAMES.get(_cardName);
	}
}

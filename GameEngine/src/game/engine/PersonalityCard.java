package game.engine;

import game.error.InvalidEntityNameException;

import java.util.HashMap;

/**
 * This class represents an entity from the Personality card deck.
 */
public class PersonalityCard extends Card {
	private static final HashMap<String, String> PERSONALITY_CARD_NAMES = new HashMap<String, String>();
	
	static {
		PERSONALITY_CARD_NAMES.put("LordDeWorde", "Lord de Worde");
		PERSONALITY_CARD_NAMES.put("CommanderVimes", "Commander Vimes");
		PERSONALITY_CARD_NAMES.put("LordVetinari", "Lord Vetinari");
		PERSONALITY_CARD_NAMES.put("LordSelachii", "Lord Selachii");
		PERSONALITY_CARD_NAMES.put("LordRust", "Lord Rust");
		PERSONALITY_CARD_NAMES.put("DragonKingOfArms", "Dragon King of Arms");
		PERSONALITY_CARD_NAMES.put("Chrysoprase", "Chrysoprase");
	}
	
	/**
	 * Gets all valid entity names for the Personality card deck.
	 * @return An array of valid names in no particular order.
	 */
	public static String[] getPersonalityCardNames() {
		return PERSONALITY_CARD_NAMES.keySet().toArray(new String[7]);
	}
	
	@Override
	public void setEntity(String entityName) throws InvalidEntityNameException {
		if (!PERSONALITY_CARD_NAMES.containsKey(entityName))
			throw new InvalidEntityNameException();
		
		super.setEntity(entityName);
	}
	
	@Override
	public String getCardname() {
		return PERSONALITY_CARD_NAMES.get(_cardName);
	}
}

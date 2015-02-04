package game.engine;

import game.error.InvalidEntityNameException;

import java.util.HashMap;

public class RandomEventCard extends Card {
	private static final HashMap<String, String> RANDOM_EVENT_CARD_NAMES = new HashMap<String, String>();
	private static final String[] randomEventCardNames = { "" };
	public static String[] getRandomEventCardNames() {
		return randomEventCardNames;
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

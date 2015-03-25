package game.engine;

import game.core.enums.RandomEventCardName;
import game.core.interfaces.IRandomEventCard;
import game.error.InvalidEntityNameException;

import java.util.HashMap;

/**
 * This class represents entities from the Random Event card deck.
 */
public class RandomEventCard implements IRandomEventCard {
	private static final HashMap<String, String> RANDOM_EVENT_CARD_NAMES = new HashMap<String, String>();
	
	private RandomEventCardName _cardName;
	
	public RandomEventCard(RandomEventCardName cardName) {
		_cardName = cardName;
	}
	
	@Override
	public RandomEventCardName getCardName() {
		return _cardName;
	}
}

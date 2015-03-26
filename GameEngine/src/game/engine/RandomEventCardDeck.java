package game.engine;

import game.core.enums.RandomEventCardName;
import game.engine.utils.CardDeck;

public class RandomEventCardDeck extends CardDeck<RandomEventCardName> {
	public RandomEventCardDeck(String nameOfCardsInDeck) {
		super(nameOfCardsInDeck);
	}

	@Override
	public void add(String cardName) {
		super.add(RandomEventCardName.valueOf(cardName));
	}
}

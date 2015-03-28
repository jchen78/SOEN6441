package game.engine;

import game.core.enums.PersonalityCardName;
import game.engine.utils.CardDeck;

public class PersonalityCardDeck extends CardDeck<PersonalityCardName> {
	protected PersonalityCardDeck(String nameOfCardsInDeck) {
		super(nameOfCardsInDeck);
	}

	@Override
	public void add(String cardName) {
		PersonalityCardName value = PersonalityCardName.valueOf(cardName);
		super.add(value);
	}
}

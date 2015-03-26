package game.engine;

import game.core.enums.PlayerCardName;
import game.engine.utils.CardDeck;

public class PlayerCardDeck extends CardDeck<PlayerCardName> {
	protected PlayerCardDeck(String nameOfCardsInDeck) {
		super(nameOfCardsInDeck);
	}

	@Override
	public void add(String internalCardName) {
		PlayerCardName card = PlayerCardName.valueOf(internalCardName);
		super.add(card);
	}
}

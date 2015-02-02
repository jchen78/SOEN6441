package tests;

import static org.junit.Assert.*;

import java.util.UUID;

import game.engine.Card;
import game.error.InvalidEntityNameException;

import org.junit.Before;
import org.junit.Test;

public class CardTests {
	private String _cardName;
	
	@Before public void InitializeCard() {
		_cardName = UUID.randomUUID().toString();
	}
	
	@Test public void WhenLoadingCardWithCardnameShouldSetCardname() throws InvalidEntityNameException {
		Card card = new Card();
		card.setEntity(_cardName);
		assertEquals(card.getCardname(), _cardName);
	}
}

package game.engine.utils;

import game.core.enums.ICardName;
import game.core.io.PersistanceManager;

import java.util.LinkedList;

public abstract class CardDeck<T extends ICardName> {
	private LinkedList<T> _data;
	
	protected CardDeck(String nameOfCardsInDeck) {
		_data = new LinkedList<T>();
		String[] cardNames = nameOfCardsInDeck.split(PersistanceManager.ROW_SEPARATOR);
		for (String cardName : cardNames) {
			if (cardName != null && cardName.length() > 0)
				add(cardName);
		}
	}
	
	public void shuffle() {
		
	}
	
	public abstract void add(String cardName);
	
	public void add(T newCard) {
		_data.add(newCard);
	}
	
	public T drawCard() {
		if (_data.size() == 0)
			return null;
		
		return _data.removeFirst();
	}
	
	public String getCurrentState() {
		String serializedValue = "";
		for (T currentCard : _data) {
			serializedValue += ";" + currentCard.getValue();
		}
		
		return serializedValue;
	}
}

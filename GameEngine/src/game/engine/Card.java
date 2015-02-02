package game.engine;

import game.error.InvalidEntityNameException;

/**
 * This class will be marked <span class="font-style: italics;">abstract</span> when
 * every card type has a specific implementation.
 */
public class Card {
	private String _cardName;
	
	/**
	 * The specifics of the card properties will be hard-coded in this file;
	 * such properties will be loaded according to the card name. Therefore,
	 * in specific implementations of this class, exceptions will be thrown
	 * if an invalid card name is provided.
	 */
	public Card() {
	}
	
	/**
	 * This method will load the entity's (static) data using the corresponding entity name.
	 * @param entityName The internal entity name.
	 * @throws InvalidEntityNameException Thrown when the entity name is not recognized by the specific entity.
	 */
	public void setEntity(String entityName) throws InvalidEntityNameException {
		_cardName = entityName;
	}
	
	/**
	 * Returns the internal card name in this class; specific implementations
	 * should override this method to provide a user-friendly name.
	 * @return Card identifier used internally.
	 */
	public String getCardname() {
		return _cardName;
	}
}

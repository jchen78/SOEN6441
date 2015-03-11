package game.engine;

import game.action.sequence.interfaces.ICardVisitee;
import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.action.sequence.visitee.GameOverException;
import game.error.InvalidEntityNameException;

import java.util.HashMap;
import java.util.Queue;

/**
 * This class represents entities from the City Area card deck.
 */
public class CityAreaCard extends Card implements ICardVisitee {
	private static final HashMap<String, String> CITY_AREA_CARD_NAMES = new HashMap<String, String>();
	
	static {
		CITY_AREA_CARD_NAMES.put("DollySisters", "Dolly Sisters");
		CITY_AREA_CARD_NAMES.put("UnrealEstate", "Unreal Estate");
		CITY_AREA_CARD_NAMES.put("DragonsLanding", "Dragon's Landing");
		CITY_AREA_CARD_NAMES.put("SmallGods", "Small Gods");
		CITY_AREA_CARD_NAMES.put("TheScours", "The Scours");
		CITY_AREA_CARD_NAMES.put("TheHippo", "The Hippo");
		CITY_AREA_CARD_NAMES.put("TheShades", "The Shades");
		CITY_AREA_CARD_NAMES.put("Dimwell", "Dimwell");
		CITY_AREA_CARD_NAMES.put("Longwall", "Longwall");
		CITY_AREA_CARD_NAMES.put("IsleOfGods", "Isle of Gods");
		CITY_AREA_CARD_NAMES.put("SevenSleepers", "Seven Sleepers");
		CITY_AREA_CARD_NAMES.put("NapHill", "Nap Hill");
	}
	
	/**
	 * Gets the list of all valid entity names for the CIty Area card deck.
	 * @return An array of valid names, with no guarantees on order.
	 */
	public static String[] getCityAreaCardNames() {
		return CITY_AREA_CARD_NAMES.keySet().toArray(new String[12]);
	}
	
	private boolean _isActive = false;
	
	@Override
	public void setEntity(String entityName) throws InvalidEntityNameException {
		if (!CITY_AREA_CARD_NAMES.containsKey(entityName))
			throw new InvalidEntityNameException();
		
		super.setEntity(entityName);
	}
	
	@Override
	public String getCardname() {
		return CITY_AREA_CARD_NAMES.get(_cardName);
	}
	
	public boolean isActive() {
		return _isActive;
	}
	
	public void setCardStatus(boolean isActive) {
		_isActive = isActive;
	}

	@Override
	public Queue<IVisitee> accept(IVisitor visitor) throws GameOverException {
		// TODO Auto-generated method stub
		// The intention is to do this using the Strategy pattern. This way, the number of classes and repeated code are both minimized.
		return null;
	}

	@Override
	public String getDescription() {
		return "Play " + getCardname();
	}

	@Override
	public CardType getCardType() {
		// TODO Auto-generated method stub
		return null;
	}
}

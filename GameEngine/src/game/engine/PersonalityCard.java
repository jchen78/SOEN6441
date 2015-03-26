package game.engine;

import game.core.enums.PersonalityCardName;
import game.core.interfaces.IPersonalityCard;
import game.error.InvalidEntityNameException;

import java.util.HashMap;

/**
 * This class represents an entity from the Personality card deck.
 */
public class PersonalityCard extends Card implements IPersonalityCard {
	private static final HashMap<String, String> PERSONALITY_CARD_NAMES = new HashMap<String, String>();
	private static final HashMap<String, String> PERSONALITY_CARD_EXPLANATION = new HashMap<String, String>();
	
	static {
		PERSONALITY_CARD_NAMES.put("LordDeWorde", "Lord de Worde");
		PERSONALITY_CARD_NAMES.put("CommanderVimes", "Commander Vimes");
		PERSONALITY_CARD_NAMES.put("LordVetinari", "Lord Vetinari");
		PERSONALITY_CARD_NAMES.put("LordSelachii", "Lord Selachii");
		PERSONALITY_CARD_NAMES.put("LordRust", "Lord Rust");
		PERSONALITY_CARD_NAMES.put("DragonKingOfArms", "Dragon King of Arms");
		PERSONALITY_CARD_NAMES.put("Chrysoprase", "Chrysoprase");
		
		PERSONALITY_CARD_EXPLANATION.put("LordDeWorde", "you win if at the beginning of your turn you control a certain number of areas (having more pieces in than anyone else in a certain number of areas).");
		PERSONALITY_CARD_EXPLANATION.put("Lord de Worde", "you win if at the beginning of your turn you control a certain number of areas (having more pieces in than anyone else in a certain number of areas).");

		PERSONALITY_CARD_EXPLANATION.put("CommanderVimes", "If the games end due to the cards running out then you win the game");
		PERSONALITY_CARD_EXPLANATION.put("Commander Vimes", "If the games end due to the cards running out then you win the game");

		PERSONALITY_CARD_EXPLANATION.put("LordVetinari", "Lord Vetinari wins by having minions in 11 different areas with two players, 10 with three, and 9 with four");
		PERSONALITY_CARD_EXPLANATION.put("Lord Vetinari", "Lord Vetinari wins by having minions in 11 different areas with two players, 10 with three, and 9 with four");

		PERSONALITY_CARD_EXPLANATION.put("LordSelachii", "you win if at the beginning of your turn you control a certain number of areas (having more pieces in than anyone else in a certain number of areas).");
		PERSONALITY_CARD_EXPLANATION.put("Lord Selachii", "you win if at the beginning of your turn you control a certain number of areas (having more pieces in than anyone else in a certain number of areas).");

		PERSONALITY_CARD_EXPLANATION.put("LordRust", "you win if at the beginning of your turn you control a certain number of areas (having more pieces in than anyone else in a certain number of areas).");
		PERSONALITY_CARD_EXPLANATION.put("Lord Rust", "you win if at the beginning of your turn you control a certain number of areas (having more pieces in than anyone else in a certain number of areas).");

		PERSONALITY_CARD_EXPLANATION.put("DragonKingOfArms", "if at the start of you turn there are eight or more trouble markers on the board then you win the game immediately.");
		PERSONALITY_CARD_EXPLANATION.put("Dragon King of Arms", "if at the start of you turn there are eight or more trouble markers on the board then you win the game immediately.");

		PERSONALITY_CARD_EXPLANATION.put("Chrysoprase", "wins if that player’s net worth (cash plus building values) is $50 or more at the beginning of their turn");
	}
	
	public PersonalityCard(PersonalityCardName name) {
		// TODO Auto-generated constructor stub
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
	
	/**
	 * Gets the explanation for desirable personality card
	 * @param cardName
	 * @return A string of explanation for the personality card
	 */
	public static String getCardExplanation(String cardName) {
		if (!PERSONALITY_CARD_EXPLANATION.containsKey(cardName))
			return null;
		return PERSONALITY_CARD_EXPLANATION.get(cardName);
	}
}

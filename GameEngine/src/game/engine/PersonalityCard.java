package game.engine;

import game.action.sequence.interfaces.IVisitor;
import game.action.sequence.visitee.GameOverException;
import game.core.enums.PersonalityCardName;
import game.core.interfaces.ICityArea;
import game.core.interfaces.IPersonalityCard;
import game.core.interfaces.IPlayer;
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

		PERSONALITY_CARD_EXPLANATION.put("Chrysoprase", "wins if that playerâ€™s net worth (cash plus building values) is $50 or more at the beginning of their turn");
	}

	PersonalityCardName _cardName;
	public PersonalityCard(PersonalityCardName name) {
		// TODO Auto-generated constructor stub
		_cardName = name;
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

	@Override
	public void accept(IVisitor visitor) throws GameOverException {

		IPlayer player = visitor.getCurrentPlayer();
		ICityArea[] cityArea = visitor.getAllCityAreas();
		IPlayer[] allPlayers = visitor.getAllPlayers();
		int numberOfPlayers = visitor.getNumberOfPlayers();
		int playerIndex = player.getIndex();
		String playerName = player.getName();

		boolean winningConditionMet = false;

		int iControlCnt = 0;
		int iHaveMinionInAreasCnt = 0;
		
		int totalMoney = player.getMoney() - player.getLoanAmount();
		
		for(int areaId = 0; areaId < 12; areaId ++)
		{
			boolean iControlThis = false;

			int myPieces = cityArea[areaId].getNumberOfMinions(playerIndex);
			if( cityArea[areaId].getNumberOfMinions(playerIndex) > 0 && 
					cityArea[areaId].getNumberDemons() == 0 )
				iHaveMinionInAreasCnt ++;

			if( playerName.equals(cityArea[areaId].getBuildingOwner()) )
			{
				myPieces ++;
				totalMoney += cityArea[areaId].getBuildingCost();
			}

			for(int playerId = 0; playerId < numberOfPlayers; playerId ++)
			{
				if(playerId == playerIndex)	// this is playerIndex, don't count this
					continue;

				int hisPieces = cityArea[areaId].getNumberOfMinions(playerId);
				if( allPlayers[playerId].getName().equals(cityArea[areaId].getBuildingOwner()) )
					hisPieces ++;

				if(myPieces <= hisPieces)
					iControlThis = false;
			}

			if(myPieces <= cityArea[areaId].getNumberTrolls())
				iControlThis = false;

			if(cityArea[areaId].getNumberDemons() > 0)
				iControlThis = false;

			if(iControlThis == true)
				iControlCnt ++;
		}

		switch (_cardName) {
		case Chrysoprase:
			if(totalMoney >= 50)
				winningConditionMet = true;
			break;

		case CommanderVimes:
			break;

		case DragonKingOfArms:
			int numberOfTroubleMarkers = 0;
			for(int i = 0; i < 12; i ++)
				if(cityArea[i].hasTroubleMarker())	// this method nees to be implemented in ICityArea
					numberOfTroubleMarkers ++;
			if(numberOfTroubleMarkers >= 8)
				winningConditionMet = true;
			break;

		case LordDeWorde:
		case LordRust:
		case LordSelachii:
			if(numberOfPlayers == 2 && iControlCnt == 7)
				winningConditionMet = true;
			if(numberOfPlayers == 3 && iControlCnt == 5)
				winningConditionMet = true;
			if(numberOfPlayers == 4 && iControlCnt == 4)
				winningConditionMet = true;
			break;

		case LordVetinari:
			if(numberOfPlayers == 2 && iHaveMinionInAreasCnt >= 11)
				winningConditionMet = true;
			if(numberOfPlayers == 3 && iHaveMinionInAreasCnt >= 10)
				winningConditionMet = true;
			if(numberOfPlayers == 4 && iHaveMinionInAreasCnt >= 9)
				winningConditionMet = true;
			break;

		default:
			break;
		}



	}

	@Override
	public String getDescription() {
		return "Verifying whether the current player has won the game.";
	}
}

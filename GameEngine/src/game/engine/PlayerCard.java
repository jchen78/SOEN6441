package game.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import game.action.scaffold.IAction;
import game.action.sequence.interfaces.ICardVisitee;
import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.action.sequence.visitee.GameOverException;
import game.error.EntityNotSetException;
import game.error.InvalidEntityNameException;

/**
 * This class represents the green- or brown-bordered player cards. It is yet incomplete: only arbitrary (temporary) names and border color are stored.
 */
public class PlayerCard extends Card implements ICardVisitee {

	private static final HashMap<String, String> GREENBORDERED_CARD_NAMES = new HashMap<String, String>();
	private static final HashMap<String, String> BROWNBORDERED_CARD_NAMES = new HashMap<String, String>();	
	private ArrayList<String> symbols = new ArrayList<String>();
	private String explanation;
	private String cardName;

	static {
		GREENBORDERED_CARD_NAMES.put("MrBoggis", "Mr Boggis");
		GREENBORDERED_CARD_NAMES.put("MrBent", "Mr Bent");
		GREENBORDERED_CARD_NAMES.put("TheBeggarsGuild", "The Beggar's Guild");
		GREENBORDERED_CARD_NAMES.put("TheBankOfAnkhMorpork", "The Bank of Ankh-Morpork");
		GREENBORDERED_CARD_NAMES.put("TheAnkhMorporkSunshineDragonSanctuary", "The Ankh-Morpork Synshine Dragon Sanctuary");
		GREENBORDERED_CARD_NAMES.put("SergeantAngua", "Sergeant Angua");
		GREENBORDERED_CARD_NAMES.put("TheAgonyAunts", "The Agony Aunts");
		GREENBORDERED_CARD_NAMES.put("TheDysk", "The Dysk");
		GREENBORDERED_CARD_NAMES.put("TheDuckman", "The Duckman");
		GREENBORDERED_CARD_NAMES.put("Drumknott", "Drumknott");
		GREENBORDERED_CARD_NAMES.put("CMOTDibbler", "CMOT Dibbler");
		GREENBORDERED_CARD_NAMES.put("DrCruces", "Dr Cruces");
		GREENBORDERED_CARD_NAMES.put("CaptainCarrot", "Captain Carrot");
		GREENBORDERED_CARD_NAMES.put("MsCake", "Ms Cake");
		GREENBORDERED_CARD_NAMES.put("Groat", "Groat");
		GREENBORDERED_CARD_NAMES.put("GimletsDwarfDelicatessen", "Gimlets Dwarf Delicatessen");
		GREENBORDERED_CARD_NAMES.put("Gaspode", "Gaspode");
		GREENBORDERED_CARD_NAMES.put("FreshStartClub", "Fresh Start Club");
		GREENBORDERED_CARD_NAMES.put("FourOleRon", "Four Ole Ron");
		GREENBORDERED_CARD_NAMES.put("TheFoolsGuild", "The Fools' Guild");
		GREENBORDERED_CARD_NAMES.put("TheFireBrigade", "The Fire Brigade");
		GREENBORDERED_CARD_NAMES.put("InigoSkimmer", "Inigo Skimmer");
		GREENBORDERED_CARD_NAMES.put("HistoryMonks", "History Monks");
		GREENBORDERED_CARD_NAMES.put("Hex", "Hex");
		GREENBORDERED_CARD_NAMES.put("HereNNow", "Here'n'Now");
		GREENBORDERED_CARD_NAMES.put("HarryKing", "Harry King");
		GREENBORDERED_CARD_NAMES.put("HargasHouseOfRibs", "Harga's House of Ribs");
		GREENBORDERED_CARD_NAMES.put("MrGryle", "Mr Gryle");
		GREENBORDERED_CARD_NAMES.put("ThePeeledNuts", "The Peeled Nuts");
		GREENBORDERED_CARD_NAMES.put("TheOperaHouse", "The Opera House");
		GREENBORDERED_CARD_NAMES.put("NorryNobbs", "Norry Nobbs");
		GREENBORDERED_CARD_NAMES.put("Modo", "Modo");
		GREENBORDERED_CARD_NAMES.put("TheMendedDrum", "The Mended Drum");
		GREENBORDERED_CARD_NAMES.put("Librarian", "Librarian");
		GREENBORDERED_CARD_NAMES.put("LeonardOfQuirm", "Leonard of Quirm");
		GREENBORDERED_CARD_NAMES.put("ShonkyShop", "Shonky Shop");
		GREENBORDERED_CARD_NAMES.put("SacharissaCrisplock", "Sacharissa Crisplock");
		GREENBORDERED_CARD_NAMES.put("RosiePalm", "Rosie Palm");
		GREENBORDERED_CARD_NAMES.put("Rincewind", "Rincewind");
		GREENBORDERED_CARD_NAMES.put("TheRoyalMint", "The Royal Mint");
		GREENBORDERED_CARD_NAMES.put("QueenMolly", "Queen Molly");
		GREENBORDERED_CARD_NAMES.put("PinkPussycatClub", "Pink Pussycat Club");
		GREENBORDERED_CARD_NAMES.put("ZorgoTheRetrophrenologist", "Zorgo the Retro-phrenologist");
		GREENBORDERED_CARD_NAMES.put("DrWhiteface", "Dr Whiteface");
		GREENBORDERED_CARD_NAMES.put("WallaceSponky", "Wallace Sponky");
		GREENBORDERED_CARD_NAMES.put("TheSeamstressesGuild", "The Seamstresses' Guild");
		GREENBORDERED_CARD_NAMES.put("MrPinAndMrTulip", "Mr Pin & Mr Tulip");
		GREENBORDERED_CARD_NAMES.put("TheThievesGuild", "The Thieves' Guild");


		BROWNBORDERED_CARD_NAMES.put("SergeantCheeryLittlebottom", "Sergeant Cheery Littlebottom");
		BROWNBORDERED_CARD_NAMES.put("OttoChriek", "Otto Chriek");
		BROWNBORDERED_CARD_NAMES.put("TheClacks", "The Clack");
		BROWNBORDERED_CARD_NAMES.put("SergeantColon", "Sergeant Colon");
		BROWNBORDERED_CARD_NAMES.put("CosmoLavish", "Cosmo Lavish");
		BROWNBORDERED_CARD_NAMES.put("TheDean", "The Dean");
		BROWNBORDERED_CARD_NAMES.put("HELLO", "'HELLO'");
		BROWNBORDERED_CARD_NAMES.put("BurleighAndStronginthearm", "Burleigh & Stronginthearm");
		BROWNBORDERED_CARD_NAMES.put("TheBursar", "The Bursar");
		BROWNBORDERED_CARD_NAMES.put("CableStreetParticulars", "Cable Street Particulars");
		BROWNBORDERED_CARD_NAMES.put("CantingCrew", "Canting Crew");
		BROWNBORDERED_CARD_NAMES.put("Carcer", "Carcer");
		BROWNBORDERED_CARD_NAMES.put("TheChairOfIndefiniteStudies", "The Chair of Indefinite Studies");
		BROWNBORDERED_CARD_NAMES.put("SirCharlesLavatory", "Sir Charles Lavatory");
		BROWNBORDERED_CARD_NAMES.put("Dorfl", "Dorfl");
		BROWNBORDERED_CARD_NAMES.put("SergeantDetritus", "Sergeant Detritus");
		BROWNBORDERED_CARD_NAMES.put("DeepDwarves", "Deep Dwarves");
		BROWNBORDERED_CARD_NAMES.put("AdoraBelleheart", "Adora Belleheart");
		BROWNBORDERED_CARD_NAMES.put("TheAlchemistsGuild", "The Alchemists' Guild");
		BROWNBORDERED_CARD_NAMES.put("TheAuditors", "The Auditors");
		BROWNBORDERED_CARD_NAMES.put("BaggySwipes", "Baggy Swipes");
		BROWNBORDERED_CARD_NAMES.put("Susan", "Susan");
		BROWNBORDERED_CARD_NAMES.put("SybilVines", "Sybil Vines");
		BROWNBORDERED_CARD_NAMES.put("MrTeatime", "Mr Teatime");
		BROWNBORDERED_CARD_NAMES.put("TheWatch", "The Watch");
		BROWNBORDERED_CARD_NAMES.put("WeeMadArthur", "Wee Mad Arthur");
		BROWNBORDERED_CARD_NAMES.put("WilliamDeWorde", "William de Worde");
		BROWNBORDERED_CARD_NAMES.put("Willikins", "Willikins");
		BROWNBORDERED_CARD_NAMES.put("ArchchancellorRidcully", "Archchancellor Ridcully");
		BROWNBORDERED_CARD_NAMES.put("Ruby", "Ruby");
		BROWNBORDERED_CARD_NAMES.put("TheSeniorWrangler", "The Senior Wrangler");
		BROWNBORDERED_CARD_NAMES.put("MrShine", "Mr Shine");
		BROWNBORDERED_CARD_NAMES.put("MrSlant", "Mr Slant");
		BROWNBORDERED_CARD_NAMES.put("TheSmokingGnu", "The Smoking Gnu");
		BROWNBORDERED_CARD_NAMES.put("Stanley", "Stanley");
		BROWNBORDERED_CARD_NAMES.put("MoistVonLipwig", "Moist von Lipwig");
		BROWNBORDERED_CARD_NAMES.put("DoctorMossyLawn", "Doctor Mossy Lawn");
		BROWNBORDERED_CARD_NAMES.put("PatriciansPalace", "Patrician's Palace");
		BROWNBORDERED_CARD_NAMES.put("PonderStibbons", "Ponder Stibbons");
		BROWNBORDERED_CARD_NAMES.put("ThePostOffice", "The Post Office");
		BROWNBORDERED_CARD_NAMES.put("ReacherGilt", "Reacher Gilt");
		BROWNBORDERED_CARD_NAMES.put("ProfessorOfRecentRunes", "Professor of Recent Runes");
		BROWNBORDERED_CARD_NAMES.put("DoctorHix", "Doctor Hix");
		BROWNBORDERED_CARD_NAMES.put("HobsonsLiveryStable", "Hobson's Livery Stable");
		BROWNBORDERED_CARD_NAMES.put("Hubert", "Hubert");
		BROWNBORDERED_CARD_NAMES.put("Igor", "Igor");
		BROWNBORDERED_CARD_NAMES.put("TheLuggage", "The Luggage");
		BROWNBORDERED_CARD_NAMES.put("TheMob", "The Mob");
		BROWNBORDERED_CARD_NAMES.put("LordDowney", "Lord Downey");
		BROWNBORDERED_CARD_NAMES.put("Dwarves", "Dwarves");
		BROWNBORDERED_CARD_NAMES.put("EdwardDeath", "Edward d'Eath");
		BROWNBORDERED_CARD_NAMES.put("Errol", "Errol");
		BROWNBORDERED_CARD_NAMES.put("Gargoyles", "Gargoyles");
	}
	
	
	public PlayerCard(String _cardName)
	{
		if(cardName.compareTo("MrBoggis") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("MrBoggis")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Take $2 if possible, from every other player";
		}

		// TODO
		if(cardName.compareTo("MrBent") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("MrBent")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("TheBeggarsGuild") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheBeggarsGuild")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("TheBankOfAnkhMorpork") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheBankOfAnkhMorpork")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("TheAnkhMorporkSynshineDragonSanctuary") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheAnkhMorporkSynshineDragonSanctuary")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("SergeantAngua") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("SergeantAngua")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("TheAgonyAunts") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheAgonyAunts")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("TheDysk") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheDysk")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("TheDuckman") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheDuckman")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("Drumknott") == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("CMOTDibbler") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("CMOTDibbler")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("DrCruces") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("DrCruces")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("CaptainCarrot") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("CaptainCarrot")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("MsCake") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("MsCake")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("Groat") == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("GimletsDwarfDelicatessen") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("GimletsDwarfDelicatessen")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("Gaspode") == 0 )
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("FreshStartClub") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("FreshStartClub")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("FourOleRon") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("FourOleRon")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("TheFoolsGuild") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheFoolsGuild")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("TheFireBrigade") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheFireBrigade")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("InigoSkimmer") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("InigoSkimmer")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("HistoryMonks") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("HistoryMonks")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("Hex") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("Hex")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("HereNNow") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("HereNNow")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("HarryKing") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("HarryKing")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("HargasHouseOfRibs") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("HargasHouseOfRibs")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("MrGryle") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("MrGryle")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("ThePeeledNuts") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("ThePeeledNuts")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("TheOperaHouse") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheOperaHouse")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("NorryNobbs") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("NorryNobbs")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("Modo") == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("TheMendedDrum") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheMendedDrum")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("Librarian") == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("LeonardOfQuirm") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("LeonardOfQuirm")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("ShonkyShop") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("ShonkyShop")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("SacharissaCrisplock") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("SacharissaCrisplock")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("RosiePalm") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("RosiePalm")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("Rincewind") == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("TheRoyalMint") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheRoyalMint")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("QueenMolly") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("QueenMolly")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("PinkPussycatClub") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("PinkPussycatClub")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("ZorgoTheRetrophrenologist") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("ZorgoTheRetrophrenologist")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("DrWhiteface") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("DrWhiteface")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("WallaceSponky") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("WallaceSponky")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("TheSeamstressesGuild") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheSeamstressesGuild")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("MrPinAndMrTulip") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("MrPinAndMrTulip")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		if(cardName.compareTo("TheThievesGuild") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheThievesGuild")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
		//TODO
		//Do the same thing for brown cards
		if(cardName.compareTo("SergeantCheeryLittlebottom") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("SergeantCheeryLittlebottom")) == 0)
		{
			cardName = _cardName;
			symbols.add("");
			explanation = "";
		}
	}

	/**
	 * Gets all valid entity names for the green-bordered player card deck.
	 * @return An array of all valid green-bordered player card entity names, in no particular order.
	 */
	public static String[] getGreenBorderedCardNames() {
		return GREENBORDERED_CARD_NAMES.keySet().toArray(new String[48]);
	}

	/**
	 * Gets all valid entity names for the brown-bordered player card deck.
	 * @return An array of all valid brown-bordered player card entity names, in no particular order.
	 */
	public static String[] getBrownBorderedCardNames() {
		return BROWNBORDERED_CARD_NAMES.keySet().toArray(new String[52]);
	}

	private String _borderColor = null;

	@Override
	public void setEntity(String entityName) throws InvalidEntityNameException {
		if (entityName == null)
			throw new InvalidEntityNameException();

		if (GREENBORDERED_CARD_NAMES.containsKey(entityName))
			_borderColor = "Green";
		else if (BROWNBORDERED_CARD_NAMES.containsKey(entityName))
			_borderColor = "Brown";
		else
			throw new InvalidEntityNameException();
	}

	/**
	 * Gets the border color, as loaded when the <code>setEntity</code> method was called.
	 * @see setEntity
	 * @return Border color ("Green" or "Brown").
	 * @throws EntityNotSetException Thrown when this method is called before the <code>setEntity</code> method.
	 */
	public String getBorderColor() throws EntityNotSetException {
		if (_borderColor == null)
			throw new EntityNotSetException();

		return _borderColor;
	}

	// TODO
	public List<IAction> getActions() throws EntityNotSetException {
		return null;
	}

	// TODO
	public CardType getType() throws EntityNotSetException {
		return null;
	}

	@Override
	public void accept(IVisitor visitor) throws GameOverException {
		// TODO Auto-generated method stub
		// The plan is to implement this method using the Command design pattern.
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CardType getCardType() {
		// TODO Auto-generated method stub
		return null;
	}
}

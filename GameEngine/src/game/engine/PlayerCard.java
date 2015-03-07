package game.engine;

import java.util.HashMap;
import java.util.List;

import game.action.IAction;
import game.error.EntityNotSetException;
import game.error.InvalidEntityNameException;

/**
 * This class represents the green- or brown-bordered player cards. It is yet incomplete: only arbitrary (temporary) names and border color are stored.
 */
public class PlayerCard extends Card {
	private static final HashMap<String, String> GREENBORDERED_CARD_NAMES = new HashMap<String, String>();
	private static final HashMap<String, String> BROWNBORDERED_CARD_NAMES = new HashMap<String, String>();

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
}

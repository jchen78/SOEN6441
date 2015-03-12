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
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Place this card infront of you and take a loan of $10 from the bank and at the end of the game you must payback $12 or lose 15 points";
		}
		if(cardName.compareTo("TheBeggarsGuild") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheBeggarsGuild")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Select one player, they must give you two cards of their choice";
		}
		if(cardName.compareTo("TheBankOfAnkhMorpork") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheBankOfAnkhMorpork")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Place this card infront of you and take a loan of $10 from the bank and at the end of the game you must payback $12 or lose 15 points";
		}
		if(cardName.compareTo("TheAnkhMorporkSynshineDragonSanctuary") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheAnkhMorporkSynshineDragonSanctuary")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Each player must give you either $1 or one of their cards";
		}
		if(cardName.compareTo("SergeantAngua") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("SergeantAngua")) == 0)
		{
			cardName = _cardName;
			symbols.add("Remove one Trouble marker");
			symbols.add("Play another card");
		}
		if(cardName.compareTo("TheAgonyAunts") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheAgonyAunts")) == 0)
		{
			cardName = _cardName;
			symbols.add("Assassination");
			symbols.add("Take money $2");
			symbols.add("Place a minion");
		}
		if(cardName.compareTo("TheDysk") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheDysk")) == 0)
		{
			cardName = _cardName;
			symbols.add("Place a Building");
			symbols.add("Scroll");
			explanation = "Each $1 for each minion in The Isle of Gods";
		}
		if(cardName.compareTo("TheDuckman") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheDuckman")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			explanation = "Move a minion belonging to another player from one area to adjacent area";
		}
		if(cardName.compareTo("Drumknott") == 0 || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("Drumknott")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			explanation = "Play any two other cards from your hand";
		}
		if(cardName.compareTo("CMOTDibbler") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("CMOTDibbler")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Roll a die. On a roll of '7' or more you take $4 from the band and On the roll of '1' give '$1' to the bank or remove one of your minion from the board. All other results have no effects";
		}
		if(cardName.compareTo("DrCruces") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("DrCruces")) == 0)
		{
			cardName = _cardName;
			symbols.add("Assassination");
			symbols.add("Take money $3");
		}
		if(cardName.compareTo("CaptainCarrot") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("CaptainCarrot")) == 0)
		{
			cardName = _cardName;
			symbols.add("Place a minion");
			symbols.add("Remove one trouble marker");
			symbols.add("Take money $1");
		}
		if(cardName.compareTo("MsCake") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("MsCake")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Take money $2");
			symbols.add("Place a building");
			explanation = "Look at all but one of the unused personality card";
		}
		if(cardName.compareTo("Groat") == 0)
		{
			cardName = _cardName;
			symbols.add("Place a minion");
		}
		if(cardName.compareTo("GimletsDwarfDelicatessen") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("GimletsDwarfDelicatessen")) == 0)
		{
			cardName = _cardName;
			symbols.add("Take money $3");
			symbols.add("Place a minion");
		}
		if(cardName.compareTo("Gaspode") == 0 )
		{
			cardName = _cardName;
			symbols.add("Interrupt");
			explanation = "Stop a player from moving or removing one of your minion";
		}
		if(cardName.compareTo("FreshStartClub") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("FreshStartClub")) == 0)
		{
			cardName = _cardName;
			symbols.add("Interrupt");
			explanation = "If you have a minion removed you can place him in a different area";
		}
		if(cardName.compareTo("FourOleRon") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("FourOleRon")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Move a minion belonging to another player from one area to an adjacent area";
		}
		if(cardName.compareTo("TheFoolsGuild") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheFoolsGuild")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Select another player. If they donot give you $5 then place this card infront of them. This card now counts towards their hand size of five cards when they come to refill their hand. They cannot get rid of this card";
		}
		if(cardName.compareTo("TheFireBrigade") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheFireBrigade")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Choose a player. If they donot give you $5 then you can remove one of his building from the board";
		}
		if(cardName.compareTo("InigoSkimmer") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("InigoSkimmer")) == 0)
		{
			cardName = _cardName;
			symbols.add("Assassination");
			symbols.add("Take money $2");
		}
		if(cardName.compareTo("HistoryMonks") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("HistoryMonks")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Shuffle the discard pile and draw four cards randomly and place the remaining cards back as the discard pile";
		}
		if(cardName.compareTo("Hex") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("Hex")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a Building");
			explanation = "Take 3 cards from the draw deck";
		}
		if(cardName.compareTo("HereNNow") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("HereNNow")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Roll a die. On a roll of '7' or more you take $3 from the band and On the roll of '1' remove one of your minion from the board. All other results have no effects";
		}
		if(cardName.compareTo("HarryKing") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("HarryKing")) == 0)
		{
			cardName = _cardName;
			symbols.add("Place a minion");
			symbols.add("Scroll");
			explanation = "Discard as many card as you wish and take $2 for each discarded";
		}
		if(cardName.compareTo("HargasHouseOfRibs") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("HargasHouseOfRibs")) == 0)
		{
			cardName = _cardName;
			symbols.add("Take money $3");
			symbols.add("Place a minion");
		}
		if(cardName.compareTo("MrGryle") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("MrGryle")) == 0)
		{
			cardName = _cardName;
			symbols.add("Assassination");
			symbols.add("Take money $1");
		}
		if(cardName.compareTo("ThePeeledNuts") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("ThePeeledNuts")) == 0)
		{
			cardName = _cardName;
		}
		if(cardName.compareTo("TheOperaHouse") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheOperaHouse")) == 0)
		{
			cardName = _cardName;
			symbols.add("Place a building");
			symbols.add("Scroll");
			explanation = "Earn $1 for each minion in The Isle of Gods";
		}
		if(cardName.compareTo("NorryNobbs") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("NorryNobbs")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Take $3 from the player of your choice";
		}
		if(cardName.compareTo("Modo") == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Discard one card";
		}
		if(cardName.compareTo("TheMendedDrum") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheMendedDrum")) == 0)
		{
			cardName = _cardName;
			symbols.add("Place a building");
			symbols.add("Take money $2");
		}
		if(cardName.compareTo("Librarian") == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			explanation = "Take four cards from the draw deck";
		}
		if(cardName.compareTo("LeonardOfQuirm") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("LeonardOfQuirm")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			explanation = "Take four cards from the draw deck";
		}
		if(cardName.compareTo("ShonkyShop") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("ShonkyShop")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a building");
			explanation = "Discard as many card as you wish and take $1 for each one discarded";
		}
		if(cardName.compareTo("SacharissaCrisplock") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("SacharissaCrisplock")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Earn $1 for each trouble marker on the board";
		}
		if(cardName.compareTo("RosiePalm") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("RosiePalm")) == 0)
		{
			cardName = _cardName;
			symbols.add("Place a minion");
			symbols.add("Scroll");
			explanation = "Choose one Player. Give them one of of your cards. Thwy must give you $2 in return";
		}
		if(cardName.compareTo("Rincewind") == 0)
		{
			cardName = _cardName;
			symbols.add("Random Event");
			symbols.add("Scroll");
			symbols.add("Place another card");
			explanation = "Move one of your minion from an area containing a trouble marker to an adjacent area";
		}
		if(cardName.compareTo("TheRoyalMint") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheRoyalMint")) == 0)
		{
			cardName = _cardName;
			symbols.add("Place a building");
			symbols.add("Take money $5");
		}
		if(cardName.compareTo("QueenMolly") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("QueenMolly")) == 0)
		{
			cardName = _cardName;
			symbols.add("Place a minion");
			symbols.add("Scroll");
			explanation = "Select one player. They must give you two cards of their choice";
		}
		if(cardName.compareTo("PinkPussycatClub") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("PinkPussycatClub")) == 0)
		{
			cardName = _cardName;
			symbols.add("Take money $3");
			symbols.add("Play another card");
		}
		if(cardName.compareTo("ZorgoTheRetrophrenologist") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("ZorgoTheRetrophrenologist")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a building");
			explanation = "You may exchange your Personality card with one drawn randomly from those not in use";
		}
		if(cardName.compareTo("DrWhiteface") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("DrWhiteface")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Select another player. If they donot give you $5 then place this card infront of them. This card now counts towards their hand size of five cards when they come to refill their hand. They cannot get rid of this card";
		}
		if(cardName.compareTo("WallaceSponky") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("WallaceSponky")) == 0)
		{
			cardName = _cardName;
			symbols.add("Interrupt");
			explanation = "You cannot be affected by the text on a card played by another player";
		}
		if(cardName.compareTo("TheSeamstressesGuild") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheSeamstressesGuild")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Choose one player. Give them one of your cards. They must give you $2 in return";
		}
		if(cardName.compareTo("MrPinAndMrTulip") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("MrPinAndMrTulip")) == 0)
		{
			cardName = _cardName;
			symbols.add("Assassination");
			symbols.add("Take money $1");
			explanation = "The New Firm";
		}
		if(cardName.compareTo("TheThievesGuild") == 0  || cardName.compareTo(GREENBORDERED_CARD_NAMES.get("TheThievesGuild")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Take $2, if possible from every other player";
		}
		//TODO
		//Do the same thing for brown cards
		if(cardName.compareTo("SergeantCheeryLittlebottom") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("SergeantCheeryLittlebottom")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Remove one trouble marker");
			explanation = "Take two cards from the draw deck";
		}			
		if(cardName.compareTo("Otto Chriek") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Otto Chriek")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a building");
			explanation = "Earn $1 for each trouble marker on the board";
		}
		if(cardName.compareTo("The Clacks") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("The Clacks")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Take money $2");
			symbols.add("Place another card");
			explanation = "Take two cards from the draw deck";
		}
		if(cardName.compareTo("Segeant Colon") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Sergeant Colon")) == 0)
		{
			cardName = _cardName;
			symbols.add("Remove one trouble marker");
			symbols.add("Place a minion");
		}
		if(cardName.compareTo("Cosmo Lavish") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Cosmo Lavish")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place another card");
			explanation = "Pay another player $2. They must then remove one minion of their choice(not one of yours) with an area trouble marker in it";
		}
		if(cardName.compareTo("The Dean") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("The Dean")) == 0)
		{
			cardName = _cardName;
			symbols.add("Random Event");
			symbols.add("Scroll");
			symbols.add("Place another card");
			explanation = "Remove one minion from unreal estate";
		}
		if(cardName.compareTo("HELLO") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("HELLO")) == 0)
		{
			cardName = _cardName;
			symbols.add("Assassination");
			symbols.add("Assassination");
			symbols.add("Place a Building");
		}
		if(cardName.compareTo("Burleigh & Stronginthearm") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Burleigh & Stronginthearm")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a Building");
			explanation = "Pay a player of your choice $2. Then choose a minion to assassinate";
		}
		if(cardName.compareTo("The Bursar") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("The Bursar")) == 0)
		{
			cardName = _cardName;
			symbols.add("Random Event");
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Exchange the positions of any two minion on the board";
		}
		if(cardName.compareTo("Cable Street Particulars") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Cable Street Particulars")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Select one player. Look at his cards and choose one of them to be discarded";
		}
		if(cardName.compareTo("Canting Crew") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Canting Crew")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Move a minion belonging to another player from one area to an adjacent area";
		}
		if(cardName.compareTo("Carcer") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Carcer")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Roll the die twice and remove one minion of your choice from those areas even if there is no trouble there";
		}
		if(cardName.compareTo("The chair of Indefinite Studies") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("The chair of Indefinite Studies")) == 0)
		{
			cardName = _cardName;
			symbols.add("Random Event");
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Exchange your hand with that of another player";
		}
		if(cardName.compareTo("Sir Charles Lavatory") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Sir Charles Lavatory")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a building");
			explanation = "Earn $1 for each building on the board(any color)";
		}
		if(cardName.compareTo("Dorfl") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Dorfl")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Move one of your minion from one area to any other area on the board";
		}
		if(cardName.compareTo("Sergeant Detritus") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Sergeant Detritus")) == 0)
		{
			cardName = _cardName;
			symbols.add("Remove one trouble marker");
			symbols.add("Remove one trouble marker");
		}
		if(cardName.compareTo("Deep Dwarves") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Deep Dwarves")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Place a minion in any area and donot place a trouble marker";
		}
		if(cardName.compareTo("Adora Belle Dearheart") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Adora Belle Dearheart")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a minion");
			symbols.add("Place a building");
			explanation = "Move one of your minion from one area to any other area on the board";
		}
		if(cardName.compareTo("The Alchemists' Guild") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("The Alchemists' Guild")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a building");
			explanation = "Discard upto three cards and refill your hand to five cards";
		}
		if(cardName.compareTo("The Auditors") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("The Auditors")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			explanation = "Every other player, in player order, must remove one of their minion from the board";
		}
		if(cardName.compareTo("Buggy Swires") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Buggy Swires")) == 0)
		{
			cardName = _cardName;
			symbols.add("Remove a trouble marker");
		}
		if(cardName.compareTo("Susan") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Susan")) == 0)
		{
			cardName = _cardName;
			symbols.add("Interrupt");
			explanation = "Stop one of your minions from being removed from the board";
		}
		if(cardName.compareTo("Sybil Vimes") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Sybil Vimes")) == 0)
		{
			cardName = _cardName;
			symbols.add("Take money $3");
			symbols.add("Scroll");
			explanation = "Replace another player's building with one of your own. Pay the cost of the building to the original owner. It must be an area that doesnot have a trouble marker ";
		}
		if(cardName.compareTo("Mr Teatime") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Mr Teatime")) == 0)
		{
			cardName = _cardName;
			symbols.add("Take Money $3");
			symbols.add("Assassination");
			symbols.add("Play another card");
		}
		if(cardName.compareTo("The Watch") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("The Watch")) == 0)
		{
			cardName = _cardName;
			symbols.add("Place a building");
			symbols.add("Remove one trouble marker");
		}
		if(cardName.compareTo("Wee Mad Arthur") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Wee Mad Arthur")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			explanation = "You may build a building for a half price";
		}
		if(cardName.compareTo("William de Worde") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("William de Worde")) == 0)
		{
			cardName = _cardName;
			symbols.add("Place a minion");
			symbols.add("Scroll");
			explanation = "Earn $1 for each trouble marker on the board";
		}
		if(cardName.compareTo("Willikins") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Willikins")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			explanation = "Place one minion in an area you have a building";
		}
		if(cardName.compareTo("Archchancellor Ridcully") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Archchancellor Ridcully")) == 0)
		{
			cardName = _cardName;
			symbols.add("Random Event");
			symbols.add("Scroll");
			explanation = "Place one or two minions in or adjacent to Unreal Estate ";
		}
		if(cardName.compareTo("Ruby") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Ruby")) == 0)
		{
			cardName = _cardName;
			symbols.add("Place a minion");
			symbols.add("Place a building");
		}
		if(cardName.compareTo("The Senior Wrangler") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("The Senior Wrangler")) == 0)
		{
			cardName = _cardName;
			symbols.add("Random Event");
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Place one minion in or adjacent to Unreal Estate";
		}
		if(cardName.compareTo("Mr Shine") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Mr Shine")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			explanation = "Place a minion in any area and donot place a trouble marker";
		}
		if(cardName.compareTo("Mr Slant") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Mr Slant")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a building");
			explanation = "Choose a area containing a trouble marker and receive $2 for each minion there";
		}
		if(cardName.compareTo("The Smoking Gnu") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("The Smoking Gnu")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Place one minion in an area containing a trouble marker";
		}
		if(cardName.compareTo("Stanley") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Stanley")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Select two cards randomly from one player and choose one to keep. Return the another card";
		}
		if(cardName.compareTo("Moist von Lipwig") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Moist von Lipwig")) == 0)
		{
			cardName = _cardName;
			symbols.add("Place a minion");
			symbols.add("Take Money $3");
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Take two cards from the draw deck";
		}
		if(cardName.compareTo("Doctor Mossy Lawn") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Doctor Mossy Lawn")) == 0)
		{
			cardName = _cardName;
			symbols.add("Interrupt");
			explanation = "Interrupt your own turn. Your turn finishes but donot discard the card you played before this one";
		}
		if(cardName.compareTo("Patrician's Palace") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Patrician's Palace")) == 0)
		{
			cardName = _cardName;
			symbols.add("Place a building");
			symbols.add("Take money $4");
			symbols.add("Place a minion");
		}
		if(cardName.compareTo("Ponder Stibbons") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Ponder Stibbons")) == 0)
		{
			cardName = _cardName;
			symbols.add("Random Event");
			symbols.add("Scroll");
			explanation = "Play any two cards from your hand";
		}
		if(cardName.compareTo("The Post Office") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("The Post Office")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Take $1 for each building on the board";
		}
		if(cardName.compareTo("Reacher Gilt") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Reacher Gilt")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			explanation = "You can replace other player's building with one of your own. Pay the cost of the building to the original owner. It must be an area that has trouble marker in it";
		}
		if(cardName.compareTo("Professor of Recent Runes") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Professor of Recent Runes")) == 0)
		{
			cardName = _cardName;
			symbols.add("Random Event");
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Take two cards from the draw deck";
		}
		if(cardName.compareTo("Doctor Hix") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Doctor Hix")) == 0)
		{
			cardName = _cardName;
			symbols.add("Random Event");
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Place a trouble marker in an area of your choice";
		}
		if(cardName.compareTo("Hobson's Livery Stable") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Hobson's Livery Stable")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a building");
			explanation = "Pay $2 to a player of your choice. Then move one of your minions to any area you wish";
		}
		if(cardName.compareTo("Hubert") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Hubert")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Force one player to give another player $3(you cannot choose yourself)";
		}
		if(cardName.compareTo("Igor") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Igor")) == 0)
		{
			cardName = _cardName;
			symbols.add("Interrupt");
			explanation = "If you have a minion removed you can place him in a different area";
		}
		if(cardName.compareTo("The luggage") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("The luggage")) == 0)
		{
			cardName = _cardName;
			symbols.add("Assassination");
			symbols.add("Scroll");
			explanation = "Discard one card";
		}
		if(cardName.compareTo("The Mob") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("The Mob")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a minion");
			symbols.add("Play another card");
			explanation = "Place one trouble marker in an area adjacent to one already containing a trouble marker";
		}
		if(cardName.compareTo("Lord Downey") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Lord Downey")) == 0)
		{
			cardName = _cardName;
			symbols.add("Assassination");
			symbols.add("Take money $3");
			symbols.add("Place a building");
		}
		if(cardName.compareTo("Dwarves") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Dwarves")) == 0)
		{
			cardName = _cardName;
			symbols.add("Place a minion");
			symbols.add("Place a minion");
		}
		if(cardName.compareTo("Edward d'Eath") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Edward d'Eath")) == 0)
		{
			cardName = _cardName;
			symbols.add("Assassination");
			symbols.add("Take money $3");
			symbols.add("Place a building");
		}
		if(cardName.compareTo("Errol") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Errol")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Roll a die. On the roll of '7' or more you remove a minion of your choice from an area containing a trouble marker. On a roll of a '1' you must remove one of your own minions";
		}
		if(cardName.compareTo("Gargoyles") == 0  || cardName.compareTo(BROWNBORDERED_CARD_NAMES.get("Gargoyles")) == 0)
		{
			cardName = _cardName;
			symbols.add("Scroll");
			symbols.add("Place a building");
			explanation = "Draw one card for each building you have on the board";
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

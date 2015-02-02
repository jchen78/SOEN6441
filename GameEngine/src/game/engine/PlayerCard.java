package game.engine;

import java.util.Arrays;

import game.error.EntityNotSetException;
import game.error.InvalidEntityNameException;

/**
 * This class represents the green- or brown-bordered player cards. It is yet incomplete: only arbitrary (temporary) names and border color are stored.
 */
public class PlayerCard extends Card {
	public static final String[] greenBorderedCardNames = {
		"MrBoggis",
		"MrBent",
		"TheBeggarsGuild",
		"TheBankOfAnkhMorpork",
		"TheAnkhMorporkDragonSanctuary",
		"SergeantAngua",
		"TheAgonyAunts",
		"TheDysk",
		"TheDuckman",
		"Drumknott",
		"DrCruces",
		"CaptainCarrot",
		"MsCake",
		"Groat",
		"GimletsDwarfDelicatessen",
		"Gaspode",
		"FreshStartClub",
		"FourOleRon",
		"TheFoolsGuild",
		"TheFireBrigade",
		"InigoSkimmer",
		"HistoryMonks",
		"Hex",
		"HereNNow",
		"HarryKing",
		"HargasHouseOfRibs",
		"MrGryle",
		"ThePeeledNuts",
		"TheOperaHouse",
		"NorryNobbs",
		"Modo",
		"TheMendedDrum",
		"Librarian",
		"LeonardOfQuirm",
		"ShonkyShop",
		"SacharissaCrisplock",
		"RosiePalm",
		"RinceWind",
		"TheRoyalMint",
		"QueenMolly",
		"PinkPussycatClub",
		"ZorgoTheRetrophrenologist",
		"DrWhiteface",
		"WallaceSponky",
		"TheSeamstressesGuild",
		"MrPinAndMrTulip",
		"TheThievesGuild",
		"Green47"
	};
	
	public static final String[] brownBorderedCardNames = {
		"Brown00",
		"Brown01",
		"Brown02",
		"Brown03",
		"Brown04",
		"Brown05",
		"Brown06",
		"Brown07",
		"Brown08",
		"Brown09",
		"Brown10",
		"Brown11",
		"Brown12",
		"Brown13",
		"Brown14",
		"Brown15",
		"Brown16",
		"Brown17",
		"Brown18",
		"Brown19",
		"Brown20",
		"Brown21",
		"Brown22",
		"Brown23",
		"Brown24",
		"Brown25",
		"Brown26",
		"Brown27",
		"Brown28",
		"Brown29",
		"Brown30",
		"Brown31",
		"Brown32",
		"Brown33",
		"Brown34",
		"Brown35",
		"Brown36",
		"Brown37",
		"Brown38",
		"Brown39",
		"Brown40",
		"Brown41",
		"Brown42",
		"Brown43",
		"Brown44",
		"Brown45",
		"Brown46",
		"Brown47",
		"Brown48",
		"Brown49",
		"Brown50",
		"Brown51",
		"Brown52"
	};
	
	private String _borderColor = null;
	
	@Override
	public void setEntity(String entityName) throws InvalidEntityNameException {
		if (entityName == null)
			throw new InvalidEntityNameException();
		
		if (Arrays.asList(greenBorderedCardNames).contains(entityName))
			_borderColor = "Green";
		else if (Arrays.asList(brownBorderedCardNames).contains(entityName))
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
}

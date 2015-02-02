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
		"SergeantCheeryLittlebottom",
		"OttoChriek",
		"TheClacks",
		"SergeantColon",
		"CosmoLavish",
		"TheDean",
		"HELLO",
		"BurleighAndStronginthearm",
		"TheBursar",
		"CableStreetParticulars",
		"CantingCrew",
		"Carcer",
		"TheChairOfIndefiniteStudies",
		"SirCharlesLavatory",
		"Dorfl",
		"SergeantDetritus",
		"DeepDwarves",
		"AdoraBelleheart",
		"TheAlchemistsGuild",
		"TheAuditors",
		"BaggySwipes",
		"Susan",
		"SybilVines",
		"MrTeatime",
		"TheWatch",
		"WeeMadArthur",
		"WilliamDeWorde",
		"Willikins",
		"ArchchancellorRidcully",
		"Ruby",
		"MrShine",
		"MrSlant",
		"TheSmokingGnu",
		"Stanley",
		"MoistVonLipwig",
		"DoctorMossyLawn",
		"PatriciansPalace",
		"PonderStibbons",
		"ThePostOffice",
		"ReacherGilt",
		"ProfessorOfRecentRunes",
		"DoctorHix",
		"HobsonsLiveryStable",
		"Hubert",
		"Igor",
		"TheLuggage",
		"TheMob",
		"LordDowney",
		"TheDwarves",
		"EdwardDearth",
		"Errol",
		"Gargoyles",
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

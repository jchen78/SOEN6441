package game.core.enums;

public enum PlayerCardName implements ICardName {
	
MrBoggis("MrBoggis", "Green"),
MrBent("MrBent", "Green"),
TheBeggarsGuild("TheBeggarsGuild"),
TheBankOfAnkhMorpork("TheBankOfAnkhMorpork"),
TheAnkhMorporkSunshineDragonSanctuary("TheAnkhMorporkSunshineDragonSanctuary"),
SergeantAngua("SergeantAngua"),
TheAgonyAunts("TheAgonyAunts"),
TheDysk("TheDysk"),
TheDuckman("TheDuckman"),
Drumknott("Drumknott"),
CMOTDibbler("CMOTDibbler"),
DrCruces("DrCruces"),
CaptainCarrot("CaptainCarrot"),
MrsCake("MrsCake"),
Groat("Groat"),
GimletsDwarfDelicatessen("GimletsDwarfDelicatessen"),
Gaspode("Gaspode"),
FreshStartClub("FreshStartClub"),
FourOleRon("FourOleRon"),
TheFoolsGuild("TheFoolsGuild"),
TheFireBrigade("TheFireBrigade"),
InigoSkimmer("Inigo Skimmer"),
HistoryMonks("HistoryMonks"),
Hex("Hex"),
HereNNow("HereNNow"),
HarryKing("HarryKing"),
HargasHouseOfRibs("HargasHouseOfRibs"),
MrGryle("MrGrylet"),
ThePeeledNuts("ThePeeledNuts"),
TheOperaHouse("TheOperaHouse"),
NorryNobbs("NorryNobbs"),
Modo("Modo"),
TheMendedDrum("TheMendedDrum"),
Librarian("Librarian"),
LeonardOfQuirm("LeonardOfQuirm"),
ShonkyShop("ShonkyShop"),
SacharissaCrisplock("SacharissaCrisplock"),
RosiePalm("RosiePalm"),
Rincewind("Rincewind"),
TheRoyalMint("TheRoyalMint"),
QueenMolly("QueenMolly"),
PinkPussycatClub("PinkPussycatClub"),
ZorgoTheRetrophrenologist("ZorgoTheRetrophrenologist"),
DrWhiteface("DrWhiteface"),
WallaceSponky("WallaceSponky", "Green"),
TheSeamstressesGuild("TheSeamstressesGuild"),
MrPinAndMrTulip("MrPinAndMrTulip"),
TheThievesGuild("TheThievesGuild"),
SergeantCheeryLittlebottom("SergeantCheeryLittlebottom"),
OttoChriek("OttoChriek"),
TheClacks("TheClacks"),
SergeantColon("SergeantColon"),
CosmoLavish("CosmoLavish"),
TheDean("TheDean"),
HELLO("HELLO"),
BurleighAndStronginthearm("BurleighAndStronginthearm"),
TheBursar("TheBursar"),
CableStreetParticulars("CableStreetParticulars"),
CantingCrew("CantingCrew"),
Carcer("Carcer"),
TheChairOfIndefiniteStudies("TheChairOfIndefiniteStudies"),
SirCharlesLavatory("SirCharlesLavatory"),
Dorfl("Dorfl"),
SergeantDetritus("SergeantDetritus"),
DeepDwarves("DeepDwarves"),
AdoraBelleDearheart("AdoraBelleDearheart"),
TheAlchemistsGuild("TheAlchemistsGuild"),
TheAuditors("TheAuditors"),
BaggySwipes("BaggySwipes"),
Susan("Susan"),
SybilVimes("SybilVimes"),
MrTeatime("MrTeatime"),
TheWatch("TheWatch"),
WeeMadArthur("WeeMadArthur"),
WilliamDeWorde("WilliamDeWorde"),
Willikins("Willikins"),
ArchchancellorRidcully("ArchchancellorRidcully"),
Ruby("Ruby"),
TheSeniorWrangler("TheSeniorWrangler"),
MrShine("MrShine"),
MrSlant("MrSlant"),
TheSmokingGnu("TheSmokingGnu"),
Stanley("Stanley"),
MoistVonLipwig("MoistVonLipwig"),
DoctorMossyLawn("DoctorMossyLawn"),
PatriciansPalace("PatriciansPalace"),
PonderStibbons("PonderStibbons"),
ThePostOffice("ThePostOffice"),
ReacherGilt("ReacherGilt"),
ProfessorOfRecentRunes("ProfessorOfRecentRunes"),
DoctorHix("DoctorHix"),
HobsonsLiveryStable("HobsonsLiveryStable"),
Hubert("Hubert"),
Igor("Igor"),
TheLuggage("TheLuggage"),
TheMob("TheMob"),
LordDowney("LordDowney"),
Dwarves("Dwarves"),
EdwardDeath("EdwardDeath"),
Errol("Errol"),
Gargoyles("Gargoyles");

private final String _cardName;
private final String _borderColor;
	
	private PlayerCardName(String cardName, String borderColor) {
		_cardName = cardName;
		_borderColor = borderColor;
	}
	
	private PlayerCardName(String cardName) {
		_cardName = cardName;
		_borderColor = "Brown";
	}
	
	@Override
	public String getValue() {
		return _cardName;
	}

	@Override
	public String getDescriptiveName() {
		switch (_cardName) {
		case "MrBoggis":
				return "Mr Boggis";
		case "MrBent":
				return "Mr Bent";
		case "TheBeggarsGuild":
				return "The Beggar's Guild";
		case "TheBankOfAnkhMorpork":
				return "The Bank of Ankh-Morpork";
		case "TheAnkhMorporkSunshineDragonSanctuary":
				return "The Ankh-Morpork Sunshine Dragon Sanctuary";
		case "SergeantAngua":
				return "Sergeant Angua";
		case "TheAgonyAunts":
				return "The Agony Aunts";
		case "TheDysk":
				return "The Dysk";
		case "TheDuckman":
				return "The Duckman";
		case "CMOTDibbler":
				return "CMOT Dibbler";
		case "DrCruces":
				return "Dr Cruces";
		case "CaptainCarrot":
				return "Captain Carrot";
		case "MrsCake":
				return "Mrs Cake";
		case "GimletsDwarfDelicatessen":
				return "Gimlet's Dwarf Delicatessen";
		case "Gaspode":
				return "Gaspode";
		case "FreshStartClub":
				return "Fresh Start Club";
		case "FourOleRon":
				return "Four Ole Ron";
		case "TheFoolsGuild":
				return "The Fools' Guild";
		case "TheFireBrigade":
				return "The Fire Brigade";
		case "InigoSkimmer":
				return "Inigo Skimmer";
		case "HistoryMonks":
				return "History Monks";
		case "HereNNow":
				return "Here'n'Now";
		case "HarryKing":
				return "Harry King";
		case "HargasHouseOfRibs":
				return "Harga's House of Ribs";
		case "MrGryle":
				return "Mr Gryle";
		case "ThePeeledNuts":
				return "The Peeled Nuts";
		case "TheOperaHouse":
			return "The Opera House";
		case "NorryNobbs":
				return "Norry Nobbs";
		case "TheMendedDrum":
				return "The Mended Drum";
		case "LeonardOfQuirm":
				return "Leonard of Quirm";
		case "ShonkyShop":
				return "Shonky Shop";
		case "SacharissaCrisplock":
				return "Sacharissa Crisplock";
		case "RosiePalm":
				return "Rosie Palm";
		case "TheRoyalMint":
				return "The Royal Mint";
		case "QueenMolly":
				return "Queen Molly";
		case "PinkPussycatClub":
				return "Pink Pussycat Club";
		case "ZorgoTheRetrophrenologist":
				return "Zorgo the Retro-phrenologist";
		case "DrWhiteface":
				return "Dr Whiteface";
		case "WallaceSponky":
				return "Wallace Sponky";
		case "TheSeamstressesGuild":
				return "The Seamstresses' Guild";
		case "MrPinAndMrTulip":
				return "Mr Pin & Mr Tulip";
		case "TheThievesGuild":
				return "The Thieves' Guild";
		case "SergeantCheeryLittlebottom":
				return "Sergeant Cheery Littlebottom";
		case "OttoChriek":
				return "Otto Chriek";
		case "TheClacks":
				return "The Clack";
		case "SergeantColon":
				return "Sergeant Colon";
		case "CosmoLavish":
				return "Cosmo Lavish";
		case "TheDean":
				return "The Dean";
		case "HELLO":
				return "'HELLO'";
		case "BurleighAndStronginthearm":
				return "Burleigh & Stronginthearm";
		case "TheBursar":
				return "The Bursar";
		case "CableStreetParticulars":
				return "Cable Street Particulars";
		case "CantingCrew":
				return "Canting Crew";
		case "TheChairOfIndefiniteStudies":
				return "The Chair of Indefinite Studies";
		case "SirCharlesLavatory":
				return "Sir Charles Lavatory";
		case "SergeantDetritus":
				return "Sergeant Detritus";
		case "DeepDwarves":
				return "Deep Dwarves";
		case "AdoraBelleDearheart":
				return "Adora Belle Dearheart";
		case "TheAlchemistsGuild":
				return "The Alchemists' Guild";
		case "TheAuditors":
				return "The Auditors";
		case "BaggySwipes":
				return "Baggy Swipes";
		case "SybilVimes":
				return "Sybil Vimes";
		case "MrTeatime":
				return "Mr Teatime";
		case "TheWatch":
				return "The Watch";
		case "WeeMadArthur":
				return "Wee Mad Arthur";
		case "WilliamDeWorde":
				return "William de Worde";
		case "ArchchancellorRidcully":
				return "Archchancellor Ridcully";
		case "TheSeniorWrangler":
				return "The Senior Wrangler";
		case "MrShine":
				return "Mr Shine";
		case "MrSlant":
				return "Mr Slant";
		case "TheSmokingGnu":
				return "The Smoking Gnu";
		case "Stanley":
				return "Stanley";
		case "MoistVonLipwig":
				return "Moist von Lipwig";
		case "DoctorMossyLawn":
				return "Doctor Mossy Lawn";
		case "PatriciansPalace":
				return "Patrician's Palace";
		case "PonderStibbons":
				return "Ponder Stibbons";
		case "ThePostOffice":
				return "The Post Office";
		case "ReacherGilt":
				return "Reacher Gilt";
		case "ProfessorOfRecentRunes":
				return "Professor of Recent Runes";
		case "DoctorHix":
				return "Doctor Hix";
		case "HobsonsLiveryStable":
				return "Hobsons' Livery Stable";
		case "TheLuggage":
				return "The Luggage";
		case "TheMob":
				return "The Mob";
		case "LordDowney":
				return "Lord Downey";
		case "EdwardDeath":
				return "Edward d'Eath";
		default:
				return _cardName;
		}
	}

	public String getColor() {
		return _borderColor;
	}
};

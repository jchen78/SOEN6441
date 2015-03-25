package game.core.enums;

public enum RandomEventCardName implements ICardName {
	Fog("Fog"),
	Fire("Fire"),
	MysteriousMurder("MysteriousMurder"),
	Riots("Riots"),
	Subsidence("Subsidence"),
	Troll("Troll"),
	BloodyStupidJhonson("BloodyStupidJhonson"),
	DemonsFromDungeonDimensions("DemonsFromDungeonDimensions"),
	TheDragon("TheDragon"),
	Earthquake("Earthquake"),
	Explosion("Explosion"),
	Flood("Flood");
	
	private String _cardName;
	
	private RandomEventCardName(String cardName) {
		_cardName = cardName;
	}
	
	@Override
	public String getValue() {
		return _cardName;
	}
	
	@Override
	public String getDescriptiveName() {
		switch (_cardName) {
		case "Fog":
			return "Fire";
		case "Fire":
			return "Fire";
		case "MysteriousMurder":
			return "MysteriousMurder";
		case "Riots":
			return "Riots";
		case "Subsidence":
			return "Subsidence";
		case "Troll":
			return "Troll";
			default:
				return _cardName;
			case "BloodyStupidJhonson":
				return "Bloody Stupid Jhonson";
			case "DemonsFromDungeonDimensions":
				return "Demons From Dungeon Dimensions";
			case "TheDragon":
				return "The Dragon";
			case "Earthquake":
				return "Earthquake";
			case "Explosion":
				return "Explosion";
			case "Flood":
					return "Flood";
		}
	}
}

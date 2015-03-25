package game.core.enums;

public enum PersonalityCardName implements ICardName {
	CommanderVimes("CommanderVimes"),
	LordDeWorde("LordDeWorde"),
	LordVetinari("LordVetinari"),
	LordSelachii("LordSelachii"),
	LordRust("LordRust"),
	DragonKingOfArms("DragonKingOfArms"),
	Chrysoprase("Chrysoprase");
	
	private String _cardName;
	
	private PersonalityCardName(String name) {
		_cardName = name;
	}
	
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return _cardName;
	}
	
	@Override
	public String getDescriptiveName() {
		switch (_cardName) {
			case "CommanderVimes":
				return "Commander Vimes";
		
			case "LordDeWorde":
				return "Lord De Worde";
			
			case "Lord Vetinari":
				return "Lord Vetinari";
				
			case "LordSelachii":
				return "Lord Selachii";
				
			case "LordRust":
				return "Lord Rust";
				
			case "DragonKingOfArms":
				return "Dragon King Of Arms";
				
			case "Chrysoprase":
				return "Chrysoprase";
			
			default:
				return _cardName;
		}
	}
}

package game.core.enums;

public enum PersonalityCardName implements ICardName {
	CommanderVimes("CommanderVimes");
	
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
			default:
				return _cardName;
		}
	}
}

package game.core.enums;

public enum PlayerCardName implements ICardName {
	MrGibbs("MrGibbs");
	
	private final String _cardName;
	
	private PlayerCardName(String cardName) {
		_cardName = cardName;
	}
	
	@Override
	public String getValue() {
		return _cardName;
	}

	@Override
	public String getDescriptiveName() {
		switch (_cardName) {
			case "MrGibbs":
				return "Mr. Gibbs";
			default:
				return _cardName;
		}
	}
};

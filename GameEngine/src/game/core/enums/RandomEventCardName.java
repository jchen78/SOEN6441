package game.core.enums;

public enum RandomEventCardName implements ICardName {
	Fog("Fog");
	
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
			default:
				return _cardName;
		}
	}
}

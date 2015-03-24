package game.core.enums;

public enum CityAreaData implements ICardName {
	DollySisters("DollySisters");
	
	private String
		_internalName,
		_descriptiveName;
	
	private int
		_buildingCost,
		_index;
	
	private CityAreaData(String name) {
		_internalName = name;
		
		switch (_internalName) {
			case "DollySisters":
				_descriptiveName = "Dolly Sisters";
				_buildingCost = 6;
				_index = 1;
				break;
		}
	}
	
	@Override
	public String getValue() {
		return _internalName;
	}
	
	@Override
	public String getDescriptiveName() {
		return _descriptiveName;
	}
	
	public int getBuildingCost() {
		return _buildingCost;
	}
}

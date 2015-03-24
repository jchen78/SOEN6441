package game.core.enums;

public enum CityAreaData {
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
	
	public String getValue() {
		return _internalName;
	}
	
	public String getText() {
		return _descriptiveName;
	}
	
	public int getBuildingCost() {
		return _buildingCost;
	}
}

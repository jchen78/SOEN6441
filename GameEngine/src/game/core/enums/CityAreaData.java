package game.core.enums;

public enum CityAreaData implements ICardName {
	DollySisters("DollySisters"),
	UnrealEstate("UnrealEstate"),
	DragonsLanding("DragonsLanding"),
	SmallGod("SmallGod"),
	TheScours("TheScours"),
	TheHippo("TheHippo"),
	TheShades("TheShades"),
	Dimwell("Dimwell"),
	LongWall("Longwall"),
	IsleOfGod("IsleOfGod"),
	SevenSleepers("SevenSleepers"),
	NapHill("NapHill");
	
	private String
		_internalName,
		_descriptiveName;
	
	private int
		_buildingCost,
		_index;
	
	private CityAreaData(String name) {
		String _internalName = name;
		
		switch (_internalName) {
			case "DollySisters":
				_descriptiveName = "Dolly Sisters";
				_buildingCost = 6;
				_index = 1;
				break;
			case "UnrealEstate":
				_descriptiveName = "Unreal Estate";
				_buildingCost = 18;
				_index = 2;
				break;
			case "DragonsLanding":
				_descriptiveName = "Dragons Landing";
				_buildingCost = 12;
				_index = 3;
				break;	
			case "SmallGod":
				_descriptiveName = "Small God";
				_buildingCost = 18;
				_index = 4;
				break;
			case "The Scours":
				_descriptiveName = "The Scours";
				_buildingCost = 6;
				_index = 5;
				break;
			case "TheHippo":
				_descriptiveName = "The Hippo";
				_buildingCost = 12;
				_index = 6;
				break;
			case "TheShades":
				_descriptiveName = "The Shades";
				_buildingCost = 6;
				_index = 7;
				break;
			case "Dimwell":
				_descriptiveName = "Dimwell";
				_buildingCost = 6;
				_index = 8;
				break;
			case "Longwall":
				_descriptiveName = "Longwall";
				_buildingCost = 12;
				_index = 9;
				break;
			case "IsleOfGod":
				_descriptiveName = "IsleOfGod";
				_buildingCost = 12;
				_index = 10;
				break;
			case "SevenSleepers":
				_descriptiveName = "Seven Sleepers";
				_buildingCost = 18;
				_index = 11;
				break;
			case "NapHill":
				_descriptiveName = "Nap Hill";
				_buildingCost = 12;
				_index = 12;
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

package tests;

import static org.junit.Assert.*;
import game.core.enums.CityAreaData;
import game.engine.CityAreaCard;
import game.engine.MapArea;
import game.error.code.BankError;

import org.junit.Test;

public class AdjacentArea {

	@Test
	public void checkForAdjacentArea() {
		
		CityAreaData areaName1 = CityAreaData.UnrealEstate;
		MapArea ma=new MapArea();
		
		assertFalse(ma.isAdjacent(areaName1));
	}

}

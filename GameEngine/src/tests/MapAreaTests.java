package tests;

import static org.junit.Assert.*;
import game.engine.MapArea;
import game.error.InvalidEntityNameException;
import game.error.InvalidOperationException;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class MapAreaTests {
	private String[] _internalNames = new String[12];
	private String[] _userFriendlyNames = new String[12];
	private int[] _buildingCosts = new int[12];
	
	private MapArea[] _allMapAreas;
	
	public MapAreaTests() {
		_internalNames[0] = "DollySisters";
		_internalNames[1] = "UnrealEstate";
		_internalNames[2] = "DragonsLanding";
		_internalNames[3] = "SmallGods";
		_internalNames[4] = "TheScours";
		_internalNames[5] = "TheHippo";
		_internalNames[6] = "TheShades";
		_internalNames[7] = "Dimwell";
		_internalNames[8] = "Longwall";
		_internalNames[9] = "IsleOfGods";
		_internalNames[10] = "SevenSleepers";
		_internalNames[11] = "NapHill";
		
		_userFriendlyNames[0] = "Dolly Sisters";
		_userFriendlyNames[1] = "Unreal Estate";
		_userFriendlyNames[2] = "Dragon's Landing";
		_userFriendlyNames[3] = "Small Gods";
		_userFriendlyNames[4] = "The Scours";
		_userFriendlyNames[5] = "The Hippo";
		_userFriendlyNames[6] = "The Shades";
		_userFriendlyNames[7] = "Dimwell";
		_userFriendlyNames[8] = "Longwall";
		_userFriendlyNames[9] = "Isle Of Gods";
		_userFriendlyNames[10] = "Seven Sleepers";
		_userFriendlyNames[11] = "Nap Hill";
		
		_buildingCosts[0] = 6;
		_buildingCosts[1] = 18;
		_buildingCosts[2] = 12;
		_buildingCosts[3] = 18;
		_buildingCosts[4] = 6;
		_buildingCosts[5] = 12;
		_buildingCosts[6] = 6;
		_buildingCosts[7] = 6;
		_buildingCosts[8] = 12;
		_buildingCosts[9] = 12;
		_buildingCosts[10] = 18;
		_buildingCosts[11] = 12;
	}
	
	@Before public void InitializeAllMapAreas() {
		_allMapAreas = new MapArea[12];
		_allMapAreas[0] = new MapArea();
		_allMapAreas[1] = new MapArea();
		_allMapAreas[2] = new MapArea();
		_allMapAreas[3] = new MapArea();
		_allMapAreas[4] = new MapArea();
		_allMapAreas[5] = new MapArea();
		_allMapAreas[6] = new MapArea();
		_allMapAreas[7] = new MapArea();
		_allMapAreas[8] = new MapArea();
		_allMapAreas[9] = new MapArea();
		_allMapAreas[10] = new MapArea();
		_allMapAreas[11] = new MapArea();

		try {
			_allMapAreas[0].setEntity("DollySisters");
			_allMapAreas[1].setEntity("UnrealEstate");
			_allMapAreas[2].setEntity("DragonsLanding");
			_allMapAreas[3].setEntity("SmallGods");
			_allMapAreas[4].setEntity("TheScours");
			_allMapAreas[5].setEntity("TheHippo");
			_allMapAreas[6].setEntity("TheShades");
			_allMapAreas[7].setEntity("Dimwell");
			_allMapAreas[8].setEntity("Longwall");
			_allMapAreas[9].setEntity("IsleOfGods");
			_allMapAreas[10].setEntity("SevenSleepers");
			_allMapAreas[11].setEntity("NapHill");
		} catch (InvalidEntityNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test public void WhenInitializingWithInvalidNameShouldThrowInvalidEntityNameException() {
		InvalidEntityNameException expectedException = null;
		MapArea mapArea = new MapArea();
		
		try { mapArea.setEntity(UUID.randomUUID().toString()); } catch (InvalidEntityNameException e) { expectedException = e; }
		
		assertNotNull(expectedException);
	}
	
	@Test public void WhenInitializingWithCorrectNameShouldLoadCorrectMapArea() {
		for (int i = 0; i < 12; i++) {
			assertEquals(i + 1, _allMapAreas[i].getNumber());
			assertEquals(_userFriendlyNames[i], _allMapAreas[i].getName());
			assertEquals(_buildingCosts[i], _allMapAreas[i].getBuildingCost());
		}
	}
	
	@Test public void WhenSettingTroubleMarkerMapAreaUpdatesItself() {
		MapArea mapArea = new MapArea();
		assertFalse(mapArea.hasTroubleMarker());
		
		try { mapArea.setTroubleMarker(true); } catch (InvalidOperationException e) { }
		
		assertTrue(mapArea.hasTroubleMarker());
	}
	
	@Test public void WhenSettingBuildingMapAreaUpdatesItself() throws InvalidOperationException {
		MapArea mapArea = new MapArea();
		assertNull(mapArea.getBuildingOwner());
		
		mapArea.addBuilding(0);
		
		assertEquals(0, (int)mapArea.getBuildingOwner());
	}
	
	@Test public void WhenSettingBuildingWithNegativePlayerIdShouldThrowException() {
		MapArea mapArea = new MapArea();
		InvalidOperationException expectedException = null;
		
		try { mapArea.addBuilding(-1); } catch (InvalidOperationException e) { expectedException = e; }
		
		assertEquals("Player ID must be valid.", expectedException.getMessage());
	}
	
	@Test public void WhenSettingBuildingWithTooLargePlayerIdShouldThrowException() {
		MapArea mapArea = new MapArea();
		InvalidOperationException expectedException = null;
		
		try { mapArea.addBuilding(4); } catch (InvalidOperationException e) { expectedException = e; }
		
		assertEquals("Player ID must be valid.", expectedException.getMessage());
	}
	
	@Test public void GivenMapAreaWithTroubleMarkerAlreadySetWhenAddingBuildingShouldThrowException() throws InvalidOperationException {
		MapArea mapArea = new MapArea();
		mapArea.setTroubleMarker(true);
		InvalidOperationException expectedException = null;
		
		try { mapArea.addBuilding(0); } catch (InvalidOperationException e) { expectedException = e; }
		
		assertEquals("Cannot add building while trouble marker is set.", expectedException.getMessage());
	}
	
	@Test public void GivenMapAreaWithBuildingAlreadyConstructedWhenSettingTroubleMarkerShouldThrowException() throws InvalidOperationException {
		MapArea mapArea = new MapArea();
		mapArea.addBuilding(0);
		InvalidOperationException expectedException = null;
		
		try { mapArea.setTroubleMarker(true); } catch (InvalidOperationException e) { expectedException = e; }
		
		assertEquals("Cannot set trouble marker when a building is constructed.", expectedException.getMessage());
	}
	
	@Test public void WhenAddingMinionMapAreaShouldUpdateItself() throws InvalidOperationException {
		MapArea mapArea = new MapArea();
		mapArea.addMinions(1, 4);
		assertEquals(4, mapArea.getMinions()[1]);
	}
	
	@Test public void WhenAddingMinionWithNegativePlayerIDShouldThrowException() {
		MapArea mapArea = new MapArea();
		InvalidOperationException expectedException = null;
		
		try { mapArea.addMinions(-1, 4); } catch (InvalidOperationException e) { expectedException = e; }
		
		assertEquals("Player ID must be valid.", expectedException.getMessage());
	}
	
	@Test public void WhenAddingMinionWithPlayerIDTooLargeShouldThrowException() {
		MapArea mapArea = new MapArea();
		InvalidOperationException expectedException = null;
		
		try { mapArea.addMinions(4, 4); } catch (InvalidOperationException e) { expectedException = e; }
		
		assertEquals("Player ID must be valid.", expectedException.getMessage());
	}
	
	@Test public void WhenAddingMinionWithNegativeNumberOfMinionsShouldThrowException() {
		MapArea mapArea = new MapArea();
		InvalidOperationException expectedException = null;
		
		try { mapArea.addMinions(2, -1); } catch (InvalidOperationException e) { expectedException = e; }
		
		assertEquals("The number of minions must be valid.", expectedException.getMessage());
	}
	
	@Test public void WhenAddingMinionWithTooManyMinionsShouldThrowException() {
		MapArea mapArea = new MapArea();
		InvalidOperationException expectedException = null;
		
		try { mapArea.addMinions(2, 13); } catch (InvalidOperationException e) { expectedException = e; }
		
		assertEquals("The number of minions must be valid.", expectedException.getMessage());
	}
	
	@Test public void WhenAddingDemonPiecesShouldUpdateMapArea() throws InvalidOperationException {
		MapArea mapArea = new MapArea();
		mapArea.setNumberDemons(3);
		assertEquals(3, mapArea.getNumberDemons());
	}
	
	@Test public void WhenSettingNegativeNumberOfDemonsShouldThrowException() {
		MapArea mapArea = new MapArea();
		InvalidOperationException expectedException = null;
		
		try { mapArea.setNumberDemons(-1); } catch (InvalidOperationException e) { expectedException = e; }
		
		assertEquals("The number of demons must be valid.", expectedException.getMessage());
	}
	
	@Test public void WhenAddingTooManyDemonsShouldThrowException() {
		MapArea mapArea = new MapArea();
		InvalidOperationException expectedException = null;
		
		try { mapArea.setNumberDemons(5); } catch (InvalidOperationException e) { expectedException = e; }
		
		assertEquals("The number of demons must be valid.", expectedException.getMessage());
	}
	
	@Test public void WhenAddingTrollPiecesShouldUpdateMapArea() throws InvalidOperationException {
		MapArea mapArea = new MapArea();
		mapArea.setNumberTrolls(3);
		assertEquals(3, mapArea.getNumberTrolls());
	}
	
	@Test public void WhenSettingNegativeNumberOfTrollsShouldThrowException() {
		MapArea mapArea = new MapArea();
		InvalidOperationException expectedException = null;
		
		try { mapArea.setNumberTrolls(-1); } catch (InvalidOperationException e) { expectedException = e; }
		
		assertEquals("The number of trolls must be valid.", expectedException.getMessage());
	}
	
	@Test public void WhenAddingTooManyTrollsShouldThrowException() {
		MapArea mapArea = new MapArea();
		InvalidOperationException expectedException = null;
		
		try { mapArea.setNumberTrolls(4); } catch (InvalidOperationException e) { expectedException = e; }
		
		assertEquals("The number of trolls must be valid.", expectedException.getMessage());
	}
	
	@Test public void GivenMapAreaWithTroubleMarkerWhenGettingCurrentStateShouldSerializeDataCorrectly() throws InvalidEntityNameException, InvalidOperationException {
		MapArea mapArea = new MapArea();
		mapArea.setEntity("TheScours");
		mapArea.setTroubleMarker(true);
		mapArea.addMinions(1, 9);
		mapArea.setNumberDemons(2);
		mapArea.setNumberTrolls(1);
		
		String serializedData = mapArea.getCurrentState();
		
		assertEquals("1;-1;0;9;0;0;2;1", serializedData);
	}
	
	@Test public void GivenMapAreaWithBuildingWhenGettingCurrentStateShouldSerializeDataCorrectly() throws InvalidEntityNameException, InvalidOperationException {
		MapArea mapArea = new MapArea();
		mapArea.setEntity("TheScours");
		mapArea.addBuilding(2);;
		mapArea.addMinions(1, 9);
		mapArea.setNumberDemons(2);
		mapArea.setNumberTrolls(1);
		
		String serializedData = mapArea.getCurrentState();
		
		assertEquals("0;2;0;9;0;0;2;1", serializedData);
	}
}

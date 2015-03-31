package tests;

import static org.junit.Assert.*;
import junit.framework.Assert;
import game.action.sequence.interfaces.IVisitor;
import game.action.sequence.visitee.GameOverException;
import game.core.enums.CityAreaData;
import game.core.enums.PersonalityCardName;
import game.core.enums.PlayerCardName;
import game.core.interfaces.ICityArea;
import game.core.interfaces.IGameInstance;
import game.core.interfaces.IPlayer;
import game.engine.CityAreaCard;
import game.engine.MapArea;
import game.error.InvalidOperationException;
import game.error.code.BankError;

import org.junit.Test;

public class MapAreaTest {
	MapArea ma=new MapArea();
	
	int mockPlayerIndex = 2;
	
	IPlayer mockPlayer = new IPlayer() {
		
		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void accept(IVisitor visitor) throws GameOverException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void setplayercolor(String playercolor)
				throws InvalidOperationException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void setPersonality(PersonalityCardName personality) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void setName(String name) throws InvalidOperationException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void setCurrentState(String serializedData)
				throws NumberFormatException, InvalidOperationException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void returnMinionsToHand(int numberMinions)
				throws IllegalArgumentException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void removeMoney(int _amount) throws InvalidOperationException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void removeMinionsFromHand(int numberMinions)
				throws IllegalArgumentException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public String getplayercolor() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ICityArea[] getPopulatedAreas(IGameInstance gameInstance) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public PlayerCardName[] getPlayerCards() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public PersonalityCardName getPersonality() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getNumberOfMinionsInHand() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public int getNumberOfBuildingsInHand() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getMoney() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public int getIndex() {
			return mockPlayerIndex;
		}
		
		@Override
		public String getCurrentState() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public CityAreaData[] getCityCards() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void addPlayerCard(PlayerCardName drawPlayerCard) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void addMoney(int amount) throws InvalidOperationException {
			// TODO Auto-generated method stub
			
		}
	};

	@Test
	public void checkForAdjacentAreaTest() {
		
		CityAreaData areaName1 = CityAreaData.UnrealEstate;
		
		
		assertFalse(ma.isAdjacent(areaName1));
	}
	
	
	@Test
	public void testAddMinions() throws InvalidOperationException {
		final int minionsToAdd = 2;
		int valueBefore = ma.getMinions()[mockPlayerIndex];
		ma.addMinions(mockPlayer, minionsToAdd);
		int valueAfter = ma.getMinions()[mockPlayerIndex];
		assertEquals(minionsToAdd, valueAfter - valueBefore);
		String s = ma.getCurrentState();
		System.out.println(s);
	}
	
	@Test(expected=InvalidOperationException.class)
	public void testAddMinionsWithMoreThanAllowed() throws InvalidOperationException {
		final int minionsToAdd = 13;
		int valueBefore = ma.getMinions()[mockPlayerIndex];
		ma.addMinions(mockPlayer, minionsToAdd);
		int valueAfter = ma.getMinions()[mockPlayerIndex];
		assertEquals(minionsToAdd, valueAfter - valueBefore);
		
	}

	
	
	@Test
	public void testSetCurrentState() throws InvalidOperationException {
		
		String s = ma.getCurrentState();
		System.out.println(s);
		String serializedData = "1;0;0;2;0;0;0;";
		
		ma.setCurrentState(serializedData);
		assertEquals(2, ma.getMinions()[mockPlayerIndex]);
		
	}
	
	
	
}

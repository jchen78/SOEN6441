package game.engine;

import game.action.sequence.visitee.TurnIterator;
import game.core.io.MenuSelector;
import game.core.io.PersistanceManager;
import game.error.BankException;
import game.error.InvalidOperationException;

import java.io.*;

public class GameTest
{
	public static void main(String[] args) throws IOException, InvalidOperationException, NumberFormatException, BankException
	{
		GameTest x = new GameTest();
		GameManager gm = new GameManager(new ConcreteCreator(), x.new TestPersistanceManager(), new MenuSelector());
		try {
			gm.initializeGame();
			gm.visit(new TurnIterator());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			gm.close();
		}
		//gm.printMenu();
	}
	
	private class TestPersistanceManager extends PersistanceManager
	{
		@Override
		public String[] retrieve(String identifier) {
			return new String[] {
				"Players",
				"one;red;6;12;CommanderVimes;10;MrBoggis" + PersistanceManager.ROW_SEPARATOR + "two;green;6;12;DragonKingOfArms;10;MrBoggis" + PersistanceManager.ROW_SEPARATOR + "three;blue;6;12;LordDeWorde;10;WallaceSponky" + PersistanceManager.ROW_SEPARATOR + "four;yellow;6;12;LordRust;10;MrBoggis",
				"",
				"ActivePlayerCardDeck",
				"MrsCake",
				"",
				"DiscardedPlayerCardDeck",
				"",
				"",
				"ActiveRandomEventCardDeck",
				"BloodyStupidJohnson",
				"",
				"DiscardedRandomEventCardDeck",
				"",
				"",
				"ActivePersonalityCardDeck",
				"",
				"",
				"DiscardedPersonalityCardDeck",
				"",
				"",
				"CityAreas",
				"DollySisters;0;0;0;0;0;0;0" + PersistanceManager.ROW_SEPARATOR +
				"UnrealEstate;0;0;0;0;0;0;0" + PersistanceManager.ROW_SEPARATOR +
				"DragonsLanding;0;0;0;0;0;0;0" + PersistanceManager.ROW_SEPARATOR +
				"SmallGod;0;0;0;0;0;0;0" + PersistanceManager.ROW_SEPARATOR +
				"TheScours;0;0;0;0;0;0;0" + PersistanceManager.ROW_SEPARATOR +
				"TheHippo;0;0;0;0;0;0;0" + PersistanceManager.ROW_SEPARATOR +
				"TheShades;0;0;0;0;0;0;0" + PersistanceManager.ROW_SEPARATOR +
				"Dimwell;0;0;0;0;0;0;0" + PersistanceManager.ROW_SEPARATOR +
				"LongWall;0;0;0;0;0;0;0" + PersistanceManager.ROW_SEPARATOR +
				"IsleOfGod;0;0;0;0;0;0;0" + PersistanceManager.ROW_SEPARATOR +
				"SevenSleepers;0;0;0;0;0;0;0" + PersistanceManager.ROW_SEPARATOR +
				"NapHill;0;0;0;0;0;0;0",
				""
			};
		}
	}
}

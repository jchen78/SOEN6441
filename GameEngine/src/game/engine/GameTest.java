package game.engine;

import game.core.io.PersistanceManager;
import game.error.BankException;
import game.error.InvalidOperationException;

import java.util.*;
import java.io.*;

public class GameTest
{
	public static void main(String[] args) throws IOException, InvalidOperationException, NumberFormatException, BankException
	{
		GameManager gm = new GameManager();

		//gm.printMenu();
	}
	
	private class TestPersistanceManager extends PersistanceManager
	{
		@Override
		public String[] retrieve(String identifier) {
			return new String[] {
				"Players",
				"",
				"",
				"ActivePlayerCardDeck",
				"",
				"",
				"DiscardedPlayerCardDeck",
				"",
				"",
				"ActiveRandomEventCardDeck",
				"",
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
				"",
				""
			};
		}
	}
}

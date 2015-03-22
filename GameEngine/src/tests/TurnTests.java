package tests;

import static org.junit.Assert.*;

import java.util.*;

import game.engine.*;
import game.error.InvalidOperationException;

import org.junit.Test;

public class TurnTests {

	@Test
	public void test() {
		GameManager board = setUpBoard();
	}
	
	private GameManager setUpBoard()
	{
		return new GameManagerMock();
	}
	
	private class GameManagerMock extends GameManager {
		private boolean isFirstPlayerTurn;
		private List<Player> mockPlayers;
		public GameManagerMock() {
			mockPlayers = new LinkedList<Player>();
			mockPlayers.add(new PlayerMock(0));
			mockPlayers.add(new PlayerMock(1));
			try {
				mockPlayers.get(0).setName("One");
				mockPlayers.get(1).setName("Two");
			} catch (InvalidOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	private class PlayerMock extends Player {

		public PlayerMock(int index) {
			super(index);
			// TODO Auto-generated constructor stub
		}
	}
}

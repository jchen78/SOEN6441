package Game;

import java.util.Random;
import java.util.Scanner;

public class Die {
	
	public final static int faces = 12;
    public int numOfPlayers;
	
	private static Die instance = null;
	
	private static Random randomGen = new Random();

	private void Die() {}
	
	public static Die getDie() {
		if (instance == null) {
			instance = new Die();
		}
		
		return instance;
	}

	/**
	 * @return an integer between one and twelve
	 */
	public static int roll() {
		return randomGen.nextInt(faces + 1);
	}
	
	/**
	 * Determine which player which should play first.
	 * @param numberOfPlayers
	 * @return an integer one and number of players
	 */
	public static int FirstPlayer(int numberOfPlayers) {
		return (roll() % numberOfPlayers) + 1;
	}

	public static void main(String [] args) {
    int result;
    System.out.println("please enter number of players of game");
    
    Scanner keyboard = new Scanner(System.in);
    int numberOfPlayers = keyboard.nextInt();
    result= FirstPlayer(numberOfPlayers);
    System.out.println("player number "+ result + " start the game ");

         
   }
}

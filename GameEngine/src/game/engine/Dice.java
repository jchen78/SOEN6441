package game.engine;

import java.util.Random;
import java.util.Scanner;

public class Dice {
	
	public final static int faces = 12;
    public int numOfPlayers;
	
	private static Dice instance = null;
	
	private static Random randomGen = new Random();

	private void Dice() {}
	
	public static Dice getDice() {
		if (instance == null) {
			instance = new Dice();
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

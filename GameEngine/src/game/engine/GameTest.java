package game.engine;

import game.error.BankException;
import game.error.InvalidOperationException;

import java.util.*;
import java.io.*;

public class GameTest
{
	public static void main(String[] args) throws IOException, InvalidOperationException, NumberFormatException, BankException
	{
		GameManager gm = new GameManager();

		gm.printMenu();
	}
}

package game.core.io;

import java.util.Scanner;

public class MenuSelector {
	public String getValue() {
		return null;
	}
	
	public int getSelection(String... choices) {
		int selection = -1;
		boolean isSelectionValid = true;
		
		Scanner input = new Scanner(System.in);
		
		do {
			if (!isSelectionValid)
				System.out.println("The input was not recognized. Please try again.");
			
			System.out.println("The following options are available: ");
			for (int i = 0; i < choices.length; i++)
				System.out.println("\t" + (i + 1) + " - " + choices[i]);
			
			System.out.print(System.lineSeparator() + "Please enter the number corresponding to your choice: ");
			selection = input.nextInt();
		} while (selection > choices.length || selection < 1);
		
		
		input.close();
		return selection - 1;
	}
	
	public int getSelection(String[] choices, String[] nonSelectableChoices) {
		int selection = -1;
		boolean isSelectionValid = true;
		
		Scanner input = new Scanner(System.in);
		
		do {
			if (!isSelectionValid)
				System.out.println("The input was not recognized. Please try again.");
			
			System.out.println("The following options are available: ");
			for (int i = 0; i < choices.length; i++)
				System.out.println("\t" + (i + 1) + " - " + choices[i]);
			for (int i = 0; i < nonSelectableChoices.length; i++)
				System.out.println("\t (N/A) - " + choices[i]);
			
			System.out.print(System.lineSeparator() + "Please enter the number corresponding to your choice: ");
			selection = input.nextInt();
		} while (selection > choices.length || selection < 1);
		
		
		input.close();
		return selection - 1;
	}
}

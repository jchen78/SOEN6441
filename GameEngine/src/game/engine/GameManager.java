package game.engine;

import game.error.BankException;
import game.error.InvalidOperationException;

import java.util.*;
import java.io.*;

public class GameManager
{
	private int currentTurn;
	private int numberOfPlayers;
	private char[] color= new char[4];
	private String[] personality = new String[7];


	private MapArea[] cityArea = new MapArea[12];
	private String[] area = {
			"Dolly Sisters", 
			"Unreal Estate", 
			"Dragon's Landing", 
			"Small Gods",
			"The Scours", 
			"The Hippo",
			"The Shades",
			"Dimwell",
			"Longwall", 
			"Isle of Gods",
			"Seven Sleepers",
			"Nap Hill"
	};

	private int[] playerMinion = new int[4];
	private int[] playerBuilding = new int[4];
	private int[] playerMoney = new int[4];
	private String[] playerCard = new String[4];
	private String[] playerAreaCard = new String[4];

	private boolean[] greenCard = new boolean[100];
	private boolean[] brownCard = new boolean[100];

	private List<Player> players = new ArrayList<Player>();
	Bank gameBank;

	//private Bank gameBank;
	//private Area[] cityArea;
	//private Card[] personalityCards;
	//private Card[] cityAreaCards;
	//private Card[] eventsCards;
	//private PlayerCard[] playerCards;

	private Random randNum = new Random();
	private String tmpStr;

	GameManager()
	{
		//		cityArea = new MapArea[12];
		//		for(int i = 0; i < 12; i ++)
		//			cityArea[i] = new MapArea();
		//cityArea = new Map().createMap();
		gameBank = new Bank();
	}

	private int getPlayerIndex(char ch)
	{
		for(int i = 0; i < numberOfPlayers; i++)
		{
			if(ch == color[i])
				return i;
		}
		return -1;
	}

	private String whatColorIsThis(char ch)
	{
		if(ch == 'r' || ch == 'R')
			return "red";

		if(ch == 'y' || ch == 'Y')
			return "yellow";

		if(ch == 'g' || ch == 'G')
			return "green";

		if(ch == 'b' || ch == 'B')
			return "blue";

		return "ERROR";
	}

	public void start() throws InvalidOperationException
	{
		Scanner input = new Scanner(System.in);

		System.out.println("Enter number of players:");
		numberOfPlayers = Integer.parseInt(input.next());

		for(int i = 0; i < numberOfPlayers; i++)
			players.add(new Player(i));

		System.out.println("Enter name of players and color:");
		for(int i = 0; i < numberOfPlayers; i++)
		{
			String playerName = input.nextLine();
			String playerColor = input.nextLine(); 
			players.get(i).setName(playerName);
			players.get(i).setplayercolor(playerColor);
			players.get(i).setNumberBuildings(6);
			players.get(i).setMinion(12);
			players.get(i).setPlayerMoney(10);
		}

		currentTurn = Dice.FirstPlayer(numberOfPlayers);

		System.out.printf("%s starts the game!\n\n", players.get(currentTurn).getName());

		input.close();
	}

	public void printCurrentPlayerProperties()
	{
		Scanner input = new Scanner(System.in);

		for(int i = 0; i < players.size(); i++)
		{
			System.out.printf("%s: \n", players.get(i).getplayercolor());
			System.out.printf("\tMinions: %s\n", players.get(i).getNumberOfMinionsInHand());
			System.out.printf("\tBuildings: %s\n", players.get(i).getNumberOfBuildingsInHand());
			System.out.printf("\tMoney: %s\n", players.get(i).getMoney());
			System.out.print("\n\tCity Area Card:\n");
			System.out.print("\n\tPlayer Cards:\n");
			// TODO
			///// System.out.print("%s\n", players[i].getPlayerCards());
			System.out.printf("\n");

			System.out.println("Which card you wish to play?");
			String card = input.nextLine();
		}

		input.close();
	}

	public void printMenu() throws IOException, InvalidOperationException, NumberFormatException, BankException
	{
		int n;
		Scanner input = new Scanner(System.in);

		while(true)
		{
			System.out.println("1 - Load a game from file");
			System.out.println("2 - Save this game to file");
			System.out.println("3 - Show this game in console");
			System.out.println("4 - Start a new Game");
			System.out.println("5 - exit");

			n = input.nextInt();
			if(n == 1) {
				loadGame();
			}
			else if(n == 2) {
				saveGame();
			}
			else if(n == 3) {
				showGame();
			}
			else if(n == 4) {
				// start();
			}
			else if(n == 5) {
				// exit
				break;
			}
			else {
				System.out.println("Invalid number");
			}
		}
	}

	public void loadGame() throws IOException, InvalidOperationException, NumberFormatException, BankException
	{

		Scanner console = new Scanner(System.in);
		String loadFileName;

		System.out.println("Enter file name for loading:");
		//loadFileName = console.nextLine();
		loadFileName = "input.txt";
		File inputFile = new File(loadFileName);
		Scanner inFile = new Scanner(inputFile);

		String line = inFile.nextLine();
		line = line.replaceAll("\\D+", "");
		numberOfPlayers = Integer.parseInt(line);

		for(int i = 0; i < numberOfPlayers; i++)
		{
			tmpStr = inFile.nextLine();	//ignoring blank line
			tmpStr = inFile.nextLine(); //ignoring blank line

			String str = inFile.nextLine();
			str = str.replaceAll("\\s+","");
			str = str.toUpperCase();
			color[i] = str.charAt(0);

			str = inFile.nextLine();
			str = str.replaceAll("\\s+","");
			personality[i] = str;
			//System.out.printf("Personality of Player %d: %s\n\n", i+1, personality[i]);
		}

		tmpStr = inFile.nextLine();
		tmpStr = inFile.nextLine();

		//loading values of Area
		for(int i = 0; i < 12; i++)
		{
			//reading empty line before the name of area
			tmpStr = inFile.nextLine();

			//reading name of area
			String str = inFile.nextLine();
			for(int j = 0; j < 12; j++)
			{
				if(str.equals(area[j]))
				{
					boolean ok = false;
					String temp, mnn = "";
					temp = inFile.nextLine();

					for(int k = 0; k < temp.length(); k++)
					{
						if(temp.charAt(k) != ':' && ok == false)
							continue;
						else if(temp.charAt(k) == ':')
							ok = true;
						else if(temp.charAt(k) != ':' && ok == true)
							mnn += temp.charAt(k);
					}

					mnn = mnn.replaceAll("\\s+", "");

					if(mnn.equals("None"))
					{
						//minion[j] = "";
					}
					else
					{
						for(int h = 0; h < mnn.length(); h ++)
						{
							int playerIndex = getPlayerIndex( mnn.charAt(h) );
							if(playerIndex != -1)
								cityArea[j].addMinions(getPlayer(playerIndex), 1);
						}
					}

					temp = inFile.nextLine();
					temp = temp.replaceAll("\\D+", "");
					// cityArea[j].setTroubleMarker(Integer.parseInt(temp) ==  1); // The city area will manage itself

					temp = inFile.nextLine();
					temp = temp.replaceAll("\\D+", "");
					///////////////In Build 1 it's not important who owns this building!
					if(Integer.parseInt(temp) == 1)
						cityArea[j].addBuilding(0);
					//building[j] = Integer.parseInt(temp);

					temp = inFile.nextLine();
					temp = temp.replaceAll("\\D+", "");
					cityArea[j].setNumberDemons(Integer.parseInt(temp));
					//demons[j] = Integer.parseInt(temp);

					temp = inFile.nextLine();
					temp = temp.replaceAll("\\D+", "");
					cityArea[j].setNumberTrolls(Integer.parseInt(temp));
					//troll[j] = Integer.parseInt(temp);

					break;
				}
			}

		}

		tmpStr = inFile.nextLine();
		tmpStr = inFile.nextLine();

		//Loading values of Players
		for(int i = 0; i < numberOfPlayers; i++)
		{
			tmpStr = inFile.nextLine();

			//We suppose that players were written in order
			String str = inFile.nextLine();
			str = inFile.nextLine();

			str = str.replaceAll("\\D+", "");
			int numberOfMinion = Integer.parseInt(str);
			playerMinion[i] = numberOfMinion;

			str = inFile.nextLine();
			str = str.replaceAll("\\D+", "");
			int numberOfBuild = Integer.parseInt(str);
			playerBuilding[i] = numberOfBuild;

			str = inFile.nextLine();
			str = str.replaceAll("\\D+", "");
			int dlr = Integer.parseInt(str);
			playerMoney[i] = dlr;

			//ignore 2 lines
			tmpStr = inFile.nextLine();
			tmpStr = inFile.nextLine();


			while(true)
			{
				String areaCard = inFile.nextLine();

				if(areaCard.equals(""))
					break;
				else
					playerAreaCard[i] += areaCard;
			}

			//ignore following line
			tmpStr = inFile.nextLine();

			//Green Card
			str = inFile.nextLine();
			str = str.replaceAll("\\s+", "");
			str += ',';
			String cardNum = "";
			for(int j = 0; j < str.length(); j++)
			{
				if(Character.isDigit(str.charAt(j)))
				{
					cardNum += str.charAt(j);
				}
				else if(str.charAt(j) == ',')
				{
					int ind = Integer.parseInt(cardNum);
					greenCard[ind] = true;
					cardNum = "";
				}
				else
					continue;
			}


			//Brown Card
			str = inFile.nextLine();
			str = str.replaceAll("\\s+", "");
			str += ',';
			cardNum = "";
			for(int j = 0; j < str.length(); j++)
			{
				if(Character.isDigit(str.charAt(j)))
				{
					cardNum += str.charAt(j);
				}
				else if(str.charAt(j) == ',')
				{
					int ind = Integer.parseInt(cardNum);
					brownCard[ind] = true;
					cardNum = "";
				}
				else
					continue;
			}
		}

		//ignoring following line
		tmpStr = inFile.nextLine();
		String str = inFile.nextLine();
		str = str.replaceAll("\\D+", "");
		gameBank.setBalance(Integer.parseInt(str));
		//bank = Integer.parseInt(str);
	}

	public void saveGame() throws IOException
	{
		String saveFileName;
		Scanner console = new Scanner(System.in);

		//saveFileName = console.nextLine();
		saveFileName = "output.txt";

		File outFile = new File(saveFileName);
		FileWriter out = new FileWriter(outFile);
		//Scanner coin = new Scanner(System.in);

		//Clear previous data????
		out.flush();

		out.write("Number of players: " + (numberOfPlayers));
		out.write("\n\n");

		for(int pid = 0; pid < numberOfPlayers; pid ++)
		{
			out.write("Player " + (pid+1) + ":\n");

			out.write("\t");
			out.write(whatColorIsThis(color[pid]) + "\n");

			out.write("\t");
			out.write( personality[pid] + "\n\n" );
		}

		out.write("Current state of the game board:\n\n");


		for(int aid = 0; aid < 12; aid ++)
		{
			out.write( area[ aid ] + "\n");

			out.write("\t" + "minions:" );
			int[] minioins = cityArea[aid].getMinions();
			char separator = ' ';
			for(int pid = 0; pid < numberOfPlayers; pid ++)
			{
				for(int cnt = 0; cnt < minioins[pid]; cnt ++)
				{
					out.write(separator);
					separator = ',';
					out.write( color[pid] );
				}
			}
			if(separator == ' ')
				out.write(" none");
			out.write("\n");

			if(cityArea[aid].hasTroubleMarker() == true)
				out.write("\t" + "Trouble: 1\n");
			else
				out.write("\t" + "Trouble: 0\n");

			Integer buildingNumber = cityArea[aid].getBuildingOwner();
			if(buildingNumber == null)
				out.write("\t" + "Building: 0" + "\n");
			else
				out.write("\t" + "Building: 1" + "\n");

			out.write("\t" + "Demons: " + cityArea[aid].getNumberDemons() + "\n");

			out.write("\t" + "Trolls: " + cityArea[aid].getNumberTrolls() + "\n\n");
		}
		// Player Status......
		out.write("Players Status:\n\n");
		for(int pid = 0; pid < numberOfPlayers; pid++)
		{
			out.write("Player " + (pid+1) + ":\n");

			out.write("\t");
			out.write(playerMinion[pid] + " minions\n");

			out.write("\t");
			out.write(playerBuilding[pid] + " buildings\n");

			out.write("\t");
			out.write(playerMoney[pid] + " $\n\n");

			out.write("\tCity Area cards:\n");
			//////////
			out.write("\tPlayer cards:\n");
			//////////
		}

		out.write("Bank: ");
		out.write("" + gameBank.getBalance());
		out.write(" $");

		out.flush();
		out.close();
	}

	public void showGame()
	{

		System.out.print("Number of players: " + (numberOfPlayers));
		System.out.print("\n\n");

		for(int pid = 0; pid < numberOfPlayers; pid ++)
		{
			System.out.print("Player " + (pid+1) + ":\n");

			System.out.print("\t");
			System.out.print(whatColorIsThis(color[pid]) + "\n");

			System.out.print("\t");
			System.out.print( personality[pid] + "\n\n" );
		}

		System.out.print("Current state of the game board:\n\n");


		for(int aid = 0; aid < 12; aid ++)
		{
			System.out.print( area[ aid ] + "\n");

			System.out.print("\t" + "minions:" );
			int[] minioins = cityArea[aid].getMinions();
			char separator = ' ';
			for(int pid = 0; pid < numberOfPlayers; pid ++)
			{
				for(int cnt = 0; cnt < minioins[pid]; cnt ++)
				{
					System.out.print(separator);
					separator = ',';
					System.out.print( color[pid] );
				}
			}
			if(separator == ' ')
				System.out.print(" none");
			System.out.print("\n");

			if(cityArea[aid].hasTroubleMarker() == true)
				System.out.print("\t" + "Trouble: 1\n");
			else
				System.out.print("\t" + "Trouble: 0\n");

			Integer buildingNumber = cityArea[aid].getBuildingOwner();
			if(buildingNumber == null)
				System.out.print("\t" + "Building: 0" + "\n");
			else
				System.out.print("\t" + "Building: 1" + "\n");

			System.out.print("\t" + "Demons: " + cityArea[aid].getNumberDemons() + "\n");

			System.out.print("\t" + "Trolls: " + cityArea[aid].getNumberTrolls() + "\n\n");
		}
		// Player Status......
		System.out.print("Players Status:\n\n");
		for(int pid = 0; pid < numberOfPlayers; pid++)
		{
			System.out.print("Player " + (pid+1) + ":\n");

			System.out.print("\t");
			System.out.print(playerMinion[pid] + " minions\n");

			System.out.print("\t");
			System.out.print(playerBuilding[pid] + " buildings\n");

			System.out.print("\t");
			System.out.print(playerMoney[pid] + " $\n\n");

			System.out.print("\tCity Area cards:\n");
			//////////
			System.out.print("\tPlayer cards:\n");
			//////////
		}

		System.out.print("Bank: ");
		System.out.print("" + gameBank.getBalance());
		System.out.print(" $\n");
	}

	// DONE
	public Player getPlayer(String playerName) {
		for(int i = 0; i < players.size(); i ++)
			if(players.get(i).getName().compareTo(playerName) == 0)
				return players.get(i);
		return null;
	}

	// TODO
	public PersonalityCard getPersonalityCard(String cardName) {
		return null;
	}

	public String getPersonalityCardExplanation(String cardName) {
		return PersonalityCard.getCardExplanation(cardName);
	}

	// DONE
	public PlayerCard getPlayerCard(String cardName) {
		return new PlayerCard( cardName );
	}

	// DONE
	public MapArea getMapArea(String areaName) { 
		String[] allAreas = MapArea.getInternalNames();
		for(int i = 0; i < 12; i ++)
		{
			if(areaName.compareTo( allAreas[i] ) == 0)
			{
				return cityArea[i];
			}
		}

		return null;
	}

	// DONE
	public List<Player> getAllPlayers() {
		return players;
	}

	// DONE
	public List<MapArea> getAllMapAreas() {
		List <MapArea> res = new ArrayList<MapArea>();
		for(int ai = 0; ai < 12; ai ++)
			res.add(cityArea[ai]); 
		return res;
	}

	public Player getPlayer(int i) {
		return players.get(i);
	}

	// DONE
	public Player getNextPlayer() {
		currentTurn = (currentTurn+1) % numberOfPlayers;
		return players.get( (currentTurn-1+numberOfPlayers) % numberOfPlayers );
	}

	public CityAreaCard getCityAreaCard(String cityAreaName) {
		// TODO Auto-generated method stub
		return null;
	}
}

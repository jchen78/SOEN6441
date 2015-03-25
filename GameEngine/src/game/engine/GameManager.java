package game.engine;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.action.sequence.visitee.GameOverException;
import game.action.sequence.visitor.Printer;
import game.action.sequence.visitor.Selector;
import game.core.enums.*;
import game.core.interfaces.*;
import game.core.io.*;
import game.engine.utils.*;
import game.error.BankException;
import game.error.InvalidOperationException;

import java.util.*;
import java.io.*;

public class GameManager implements IVisitor, IGameInstance
{
	private static final int NUMBER_CITY_AREAS = 12;
	private MenuSelector _menuSelector;
	private PersistanceManager _persistanceManager;
	private ConcreteCreator _creator;
	
	private PlayerIterator _playerIterator;
	private Stack<IPlayer> _playerStack;
	
	private PersonalityCardDeck _activePersonalityCardDeck;
	private PersonalityCardDeck _discardedPersonalityCardDeck;
	private HashMap<PersonalityCardName, IPersonalityCard> _personalityCardDatabase;
	
	private PlayerCardDeck _activePlayerCardDeck;
	private PlayerCardDeck _discardedPlayerCardDeck;
	private HashMap<PlayerCardName, IPlayerCard> _playerCardDatabase;
	
	private RandomEventCardDeck _activeRandomEventCardDeck;
	private RandomEventCardDeck _discardedRandomEventCardDeck;
	private HashMap<RandomEventCardName, IRandomEventCard> _randomEventDatabase;
	
	private HashMap<CityAreaData, ICityArea> _cityAreaDatabase;
	
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

	private String[] greenBrownDeck;
	
	private int greenBrownDeckCurrentIndex;	
	
	Bank gameBank;

	//private Bank gameBank;
	//private Area[] cityArea;
	//private Card[] personalityCards;
	//private Card[] cityAreaCards;
	//private Card[] eventsCards;
	//private PlayerCard[] playerCards;

	private Random randNum = new Random();
	private String tmpStr;

	public GameManager()
	{
		this(new ConcreteCreator(), new PersistanceManager());
	}
	
	public GameManager(ConcreteCreator creator, PersistanceManager persistanceManager) {
		//		cityArea = new MapArea[12];
		//		for(int i = 0; i < 12; i ++)
		//			cityArea[i] = new MapArea();
		//cityArea = new Map().createMap();
		gameBank = new Bank();
		_playerStack = new Stack<IPlayer>();
		_creator = creator;
	}

	public void initializeGame() throws Exception {
		initializeEntityDatabase();
		
		int selection = _menuSelector.getSelection("Start a new game.", "Load an existing game.");
		HashMap<String, String> gameData = null;
		if (selection == 1)
			gameData = retrieveNewGameData();
		else
			gameData = retrieveExistingGameData();
		
		setState(gameData);
	}
	
	private void initializeEntityDatabase() {
		// TODO : Set the *Database (playerCardDatabase, cityAreaDatabase, etc.) variables
	}
	
	private HashMap<String, String> retrieveNewGameData() {
		// TODO
		return null;
	}
	
	private HashMap<String, String> retrieveExistingGameData() {
		// TODO Get the filename
		String filename = "";
		return getState(_persistanceManager.retrieve(filename));
	}
	
	private HashMap<String, String> getState(String[] stateContents) throws IllegalArgumentException {
		HashMap<String, String> state = new HashMap<String, String>();
		if (stateContents.length % 3 != 0)
			throw new IllegalArgumentException();
		for (int i = 0; i < stateContents.length; i += 3) {
			if (!stateContents[i + 2].equals(""))
				throw new IllegalArgumentException();
			
			state.put(stateContents[i], stateContents[i + 1]);
		}
		
		return null;
	}
	
	private void setState(HashMap<String, String> currentState) {
		_playerIterator = new PlayerIterator(currentState.get("Players"));
		_activePlayerCardDeck = new PlayerCardDeck(currentState.get("ActivePlayerCardDeck"));
		_discardedPlayerCardDeck = new PlayerCardDeck(currentState.get("DiscardedPlayerCardDeck"));
		_activeRandomEventCardDeck = new RandomEventCardDeck(currentState.get("ActiveRandomEventCardDeck"));
		_discardedRandomEventCardDeck = new RandomEventCardDeck(currentState.get("DiscardedRandomEventCardDeck"));
		_activePersonalityCardDeck = new PersonalityCardDeck(currentState.get("ActivePersonalityCardDeck"));
		_discardedPersonalityCardDeck = new PersonalityCardDeck(currentState.get("DiscardedPersonalityCardDeck"));
		setMapState(currentState.get("CityAreas"));
	}

	private void setMapState(String currentState) {
		_cityAreaDatabase = new HashMap<CityAreaData, ICityArea>(12);
		String[] cityStates = currentState.split(PersistanceManager.ROW_SEPARATOR);
		for (String cityState : cityStates) {
			ICityArea currentCity =  _creator.createCity(cityState);
			_cityAreaDatabase.put(currentCity.getCityAreaName(), currentCity);
		}
	}
	
	public void persistGame() throws Exception {
		LinkedList<String> currentState = new LinkedList<String>();
		addStateData(currentState, "Players", _playerIterator.getCurrentState());
		addStateData(currentState, "ActivePlayerCardDeck", _activePlayerCardDeck.getCurrentState());
		addStateData(currentState, "DiscardedPlayerCardDeck", _discardedPlayerCardDeck.getCurrentState());
		addStateData(currentState, "RandomEventCardDeck", _activeRandomEventCardDeck.getCurrentState());
		addStateData(currentState, "CityAreas", getMapState());
		
		_persistanceManager.persist(currentState.toArray(new String[currentState.size()]));
	}
	
	private String getMapState() {
		String currentState = "";
		for (ICityArea currentArea : _cityAreaDatabase.values())
			currentState += PersistanceManager.ROW_SEPARATOR + currentArea.getCurrentState();
		
		int separatorLength = PersistanceManager.ROW_SEPARATOR.length();
		currentState = currentState.substring(separatorLength, currentState.length() - separatorLength);
		return currentState;
	}

	private void addStateData(LinkedList<String> currentStateData, String stateType, String serializedStateData) {
		currentStateData.add(stateType);
		currentStateData.add(serializedStateData);
		currentStateData.add("");
	}

	// DONE
	public IPlayer getPlayer(String playerName) {
		return _playerIterator.getPlayer(playerName);
	}

	public PersonalityCardName getPersonalityCard(String cardName) {
		return _activePersonalityCardDeck.drawCard();
	}

	public String getPersonalityCardExplanation(String cardName) {
		return PersonalityCard.getCardExplanation(cardName);
	}

	// DONE
	public IPlayerCard getPlayerCard(PlayerCardName cardName) {
		return _playerCardDatabase.get(cardName);
	}
	
	@Override
	public IPlayer getPlayer(int i) {
		return _playerIterator.getPlayer(i);
	}
	
	public ICityArea getCityArea(CityAreaData cityAreaName) {
		return _cityAreaDatabase.get(cityAreaName);
	}
	public static void shuffleArrayOfString(String[] ar)
   {
           Random rnd = new Random();
           for (int i = ar.length - 1; i > 0; i--)
           {
                   int index = rnd.nextInt(i + 1);
                   String tmp = ar[index];
                   ar[index] = ar[i];
                   ar[i] = tmp;
           }
   }
   
   public String drawOneCardFromDeck()
   {
	   return greenBrownDeck[greenBrownDeckCurrentIndex++];
   }
   
	private GameManager _gameInstance;
	private Player _currentPlayer;
	
	private Printer _printer;
	private Selector _selector = new Selector();
	
	public void setMode(Printer currentMode) {
		_printer = currentMode;
	}

	@Override
	public void visit(IVisitee visitee) {
		_printer.print(visitee);
		
		try {
			visitee.accept(this);
		} catch (GameOverException e) {
			// TODO
			// Choose winner according to personality card
			// Display winner
			// Clean up & exit.
		}
	}
	
	@Override
	public IVisitee selectAction(List<IVisitee> choices) {
		_printer.print(choices);
		_selector.select(choices);
		return choices.get(_selector.getSelectedIndex());
	}
	
	@Override
	public IPlayer getCurrentPlayer() {
		return _playerStack.empty() ? _playerIterator.getCurrentPlayer() : _playerStack.peek();
	}
	
	@Override
	public GameManager getGameInstance() {
		// To be removed. All the relevant methods from GameManager will be moved to IVisitor instead (intended for a later refactor).
		return this;
	}

	public PlayerCardName drawPlayerCard() {
		return _activePlayerCardDeck.drawCard();
	}

	@Override
	public void setCurrentPlayer(IPlayer currentPlayer) {
		_playerStack.push(currentPlayer);
	}
	
	@Override
	public void clearCurrentPlayer() {
		_playerStack.pop();
	}
	
	public ICityArea[] getAllCityAreas() {
		return _cityAreaDatabase.values().toArray(new ICityArea[12]);
	}

	@Override
	public PlayerCardName drawDiscardedPlayerCard() {
		return _discardedPlayerCardDeck.drawCard();
	}

	@Override
	public void discardPlayerCard(PlayerCardName discardedCard) {
		_discardedPlayerCardDeck.add(discardedCard);
		_discardedPlayerCardDeck.shuffle();
	}

	@Override
	public CityAreaData[] getAllMapAreas() {
		return _cityAreaDatabase.values().toArray(new CityAreaData[NUMBER_CITY_AREAS]);
	}

	@Override
	public ICityArea getMapArea(CityAreaData selectedArea) {
		return _cityAreaDatabase.get(selectedArea);
	}

	@Override
	public PersonalityCardName drawPersonalityCard() {
		return _activePersonalityCardDeck.drawCard();
	}

	@Override
	public void discardPersonalityCard(PersonalityCardName discardedCard) {
		_discardedPersonalityCardDeck.add(discardedCard);
	}

	@Override
	public IPersonalityCard getPersonalityCard(PersonalityCardName cardName) {
		return _personalityCardDatabase.get(cardName);
	}

	@Override
	public IRandomEventCard getRandomEvent() {
		IRandomEventCard drawnCard = _randomEventDatabase.get(_activeRandomEventCardDeck.drawCard());
		_discardedRandomEventCardDeck.add(drawnCard.getCardName());
		return drawnCard;
	}

	@Override
	public void registerNewPlayer(String playerName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IPlayer[] getAllPlayers() {
		return _playerIterator.getNonCurrentPlayers();
	}

	@Override
	public int rollDie() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IPlayer getNextPlayer() {
		return _playerIterator.iterate();
	}
}
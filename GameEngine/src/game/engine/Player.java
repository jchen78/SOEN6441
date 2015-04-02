package game.engine;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.action.sequence.visitee.GameOverException;
import game.core.enums.CityAreaData;
import game.core.enums.PersonalityCardName;
import game.core.enums.PlayerCardName;
import game.core.interfaces.ICityArea;
import game.core.interfaces.IGameInstance;
import game.core.interfaces.IPlayer;
import game.core.interfaces.IPlayerCard;
import game.core.interfaces.ISelectable;
import game.error.EntityNotSetException;
import game.error.InvalidOperationException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Player implements IMoneyHolder, IPlayer {
	private static final int MAX_NUMBER_BUILDING_PIECES = 6;
	private static final int MAX_NUMBER_MINION_PIECES = 12;
	private int _playerIndex;
	private String _playerName;
	private String _playerColor;
	private int _numOfPlayerBuildings;
	private int _numOfPlayerMinions = 12;
	private PersonalityCardName _playerPersonality;
	private int _playerMoney;
	private LinkedList<PlayerCardName> _playerCards;
	private HashSet<CityAreaData> _cityCards;
	private LinkedList<PlayerCardName> _playerCardsInDisplay;
	
	public Player(int index) {
		_playerIndex = index;
	}

	@Override
	public String getName() {
		return _playerName;
	}

	@Override
	public void setName(String name) throws InvalidOperationException {
		if (name.contains(";"))
			throw new InvalidOperationException("Player name cannot contain the semicolon character.");

		this._playerName = name;
	}

	@Override
	public String getplayercolor() {
		return _playerColor;
	}

	@Override
	public void setplayercolor(String playercolor) throws InvalidOperationException {
		if (_playerColor.contains(";"))
			throw new InvalidOperationException("The player color cannot contain the semicolon character.");

		this._playerColor = playercolor;
	}

	@Override
	public int getNumberOfMinionsInHand() {
		return _numOfPlayerMinions;
	}

	@Override
	public int getNumberOfBuildingsInHand() {
		return _numOfPlayerBuildings;
	}

	/**
	 * Initializes the number of buildings in the player's hand.
	 * @param buildingCount Number of buildings in the player' hand after initialization.
	 * @throws InvalidOperationException Thrown when the number of buildings is not in [0, 6]
	 */
	public void setNumberBuildings(int buildingCount) throws InvalidOperationException {
		if (buildingCount < 0 || buildingCount > MAX_NUMBER_BUILDING_PIECES)
			throw new InvalidOperationException("Number of buildings must be valid.");

		this._numOfPlayerBuildings = buildingCount;
	}

	@Override
	public PersonalityCardName getPersonality() {
		return _playerPersonality;
	}

	private void setPersonality(String personality) throws InvalidOperationException {
		if (personality.contains(";"))
			throw new InvalidOperationException("The personality card name cannot contain the semicolon character.");

		_playerPersonality = PersonalityCardName.valueOf(personality);
	}

	@Override
	public void setPersonality(PersonalityCardName personality) {
		_playerPersonality = personality;
	}

	@Override
	public String getAccountHolderName()
	{
		return _playerName;
	}

	/**
	 * Gets the money currently in the player's hand.
	 */
	@Override
	public int getMoney() {
		return _playerMoney;
	}

	/**
	 * Initializes the money in the player's hand.
	 * @param money Amount in the player's hand after initialization.
	 * @throws InvalidOperationException Thrown when money is not in [0, 120].
	 */
	private void setPlayerMoney(int money) throws InvalidOperationException {
		if (money < 0 || money > 120)
			throw new InvalidOperationException("Amount of money must be valid.");

		_playerMoney = money;
	}

	@Override
	public void addMoney(int amount) throws InvalidOperationException {
		setPlayerMoney(_playerMoney + amount);
	}

	@Override
	public void removeMoney(int amount) throws InvalidOperationException {
		setPlayerMoney(_playerMoney - amount);
	}

	@Override
	public PlayerCardName[] getPlayerCards() {
		return _playerCards.toArray(new PlayerCardName[_playerCards.size()]);
	}

	@Override
	public String getCurrentState() {
		String currentState =
				valueOrEmpty(_playerName) + ";"
						+ valueOrEmpty(_playerColor) + ";"
						+ _numOfPlayerBuildings + ";"
						+ _numOfPlayerMinions + ";"
						+ valueOrEmpty(_playerPersonality.getValue()) + ";"
						+ _playerMoney;

		for (PlayerCardName name : _playerCards)
			currentState += ";" + valueOrEmpty(name.getValue());

		return currentState;
	}

	private String valueOrEmpty(String value) {
		return value == null ? "" : value;
	}

	@Override
	public void setCurrentState(String serializedData) throws NumberFormatException, InvalidOperationException {
		if (serializedData == null)
			throw new InvalidOperationException("Serialized data cannot be null.");

		String[] serializedParts = serializedData.split(";");
		if (serializedParts.length < 6 || serializedParts.length > 106)
			throw new InvalidOperationException("Invalid serialized data.");

		_playerName = serializedParts[0];
		_playerColor = serializedParts[1];
		_numOfPlayerBuildings = Integer.parseInt(serializedParts[2]);
		_numOfPlayerMinions = Integer.parseInt(serializedParts[3]);
		setPersonality(serializedParts[4]);
		_playerMoney = Integer.parseInt(serializedParts[5]);
		_playerCards = new LinkedList<PlayerCardName>();
		for (int i = 6; i < serializedParts.length; i++)
			_playerCards.add(PlayerCardName.valueOf(serializedParts[i]));
	}

	public int getIndex() {
		return _playerIndex;
	}

	@Override
	public CityAreaData[] getCityCards() {
		return _cityCards.toArray(new CityAreaData[_cityCards.size()]);
	}

	@Override
	public void addPlayerCard(PlayerCardName playerCard) {
		_playerCards.add(playerCard);
	}

	@Override
	public void returnMinionsToHand(int numberMinions) throws IllegalArgumentException {
		if (numberMinions < 0 || (numberMinions + _numOfPlayerMinions) > MAX_NUMBER_MINION_PIECES)
			throw new IllegalArgumentException();

		this._numOfPlayerMinions += numberMinions;
	}

	@Override
	public ICityArea[] getPopulatedAreas(IGameInstance gameInstance) {
		int numberAreas = 0;
		List<ICityArea> currentlyPopulatedAreas = new LinkedList<ICityArea>();
		for (CityAreaData area : CityAreaData.values()) {
			ICityArea currentArea = gameInstance.getCityArea(area);
			if (currentArea.getMinions()[_playerIndex] > 0) {
				currentlyPopulatedAreas.add(currentArea);
				numberAreas++;
			}
		}

		return currentlyPopulatedAreas.toArray(new ICityArea[numberAreas]);
	}

	@Override
	public void removeMinionsFromHand(int numberMinions) throws IllegalArgumentException {
		if (numberMinions < 0 || numberMinions > _numOfPlayerMinions)
			throw new IllegalArgumentException();

		this._numOfPlayerMinions -= numberMinions;
	}


	@Override
	public void accept(IVisitor visitor) throws GameOverException, EntityNotSetException, InvalidOperationException {
		// Choose card and remove it from the player's available cards
		_usedCards = new LinkedList<PlayerCardName>();
		_availableCities = new LinkedList<IVisitee>();
		chooseCard(visitor);
		for (ICityArea currentArea : visitor.getAllCityAreas())
			if (currentArea.getBuildingOwner().equals(_playerName))
				_availableCities.add(currentArea);

		// Play card.
		for (List<IVisitee> possibleActions = getActions(); possibleActions.size() > 0; possibleActions = getActions()) {
			IVisitee currentAction = (IVisitee)visitor.selectAction(possibleActions.toArray(new IVisitee[possibleActions.size()])); // Arrays are covariant in Java
			_cardActions.remove(currentAction);
			_availableCities.remove(currentAction);
			visitor.visit(currentAction);
		}

		for (PlayerCardName usedCard : _usedCards) {
			_playerCards.remove(usedCard);
			visitor.discardPlayerCard(usedCard);
		}
	}

	private List<IVisitee> _availableCities;
	private List<PlayerCardName> _usedCards;
	private List<IVisitee> _cardActions;
	private void chooseCard(IVisitor visitor) {
		_cardActions = null;
		LinkedList<ISelectable> activeCards = new LinkedList<ISelectable>();
		LinkedList<ISelectable> inactiveCards = new LinkedList<ISelectable>();

		for (PlayerCardName cardName : _playerCards) {
			IPlayerCard card = visitor.getPlayerCard(cardName);
			CardType cardType;
			try {
				cardType = card.getCardType();
			}
			catch (EntityNotSetException e) {
				throw new RuntimeException(e);
			}

			if (cardType == CardType.Playable)
				activeCards.add(card);
			else
				inactiveCards.add(card);
		}

		if (activeCards.size() == 0)
			return;

		ISelectable[] allActiveCards = activeCards.toArray(new ISelectable[activeCards.size()]);
		ISelectable[] allInactiveCards = inactiveCards.toArray(new ISelectable[inactiveCards.size()]);
		IPlayerCard selectedAction = (IPlayerCard)visitor.selectAction(allActiveCards, allInactiveCards);
		_cardActions = new LinkedList<IVisitee>(Arrays.asList(selectedAction.getActions()));
		_usedCards.add(selectedAction.getName());
	}

	private List<IVisitee> getActions() {
		List<IVisitee> possibleActions = new LinkedList<IVisitee>();
		if (_cardActions != null && _cardActions.size() > 0) {
			possibleActions.add(_cardActions.get(0));
			for (IVisitee cityVisitee : _availableCities) {
				ICityArea currentCity = (ICityArea)cityVisitee;
				if (currentCity.areActionsAvailable())
					possibleActions.add(currentCity);
			}
		}

		return possibleActions;
	}

	@Override
	public String getDescription() {
		return "Choose one player card.";
	}

	@Override
	public void discardPlayerCard(PlayerCardName playerCard) {
		_playerCards.remove(playerCard);
	}

	@Override
	public int getLoanAmount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void putPlayerCardInDisplay(IPlayerCard playerCard) {
		_playerCardsInDisplay.add(playerCard.getName());
	}
}

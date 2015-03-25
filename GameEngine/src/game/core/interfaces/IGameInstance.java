package game.core.interfaces;

import game.core.enums.*;

public interface IGameInstance {
	// Player card management
	public PlayerCardName drawPlayerCard();
	public PlayerCardName drawDiscardedPlayerCard();
	public void discardPlayerCard(PlayerCardName discardedCard);
	public IPlayerCard getPlayerCard(PlayerCardName cardName);
	
	// Board management
	public CityAreaData[] getAllMapAreas();
	public ICityArea getMapArea(CityAreaData selectedArea);
	
	// Personality card management
	public PersonalityCardName drawPersonalityCard();
	public void discardPersonalityCard(PersonalityCardName discardedCard);
	public IPersonalityCard getPersonalityCard(PersonalityCardName cardName);
	
	// Random event management
	public IRandomEventCard getRandomEvent();
	
	// Player management
	public void registerNewPlayer(String playerName);
	public IPlayer getCurrentPlayer();
	public IPlayer getNextPlayer();
	public IPlayer[] getAllPlayers();
	
	// Game utility functions
	public void initializeGame() throws Exception;
	public void persistGame() throws Exception;
	public int rollDie();
	IPlayer getPlayer(int playerIndex);
}
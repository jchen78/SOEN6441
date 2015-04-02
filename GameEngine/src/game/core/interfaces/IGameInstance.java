package game.core.interfaces;

import game.action.sequence.visitee.GameOverException;
import game.core.enums.*;

public interface IGameInstance {
	// Player card management
	public PlayerCardName drawPlayerCard() throws GameOverException;
	public PlayerCardName drawDiscardedPlayerCard();
	public void discardPlayerCard(PlayerCardName discardedCard);
	public IPlayerCard getPlayerCard(PlayerCardName cardName);
	
	// Board management
	public ICityArea[] getAllCityAreas();
	public ICityArea getCityArea(CityAreaData selectedArea);
	ICityArea[] getActiveCityCardsForPlayer(IPlayer player);
	
	// Personality card management
	public PersonalityCardName drawPersonalityCard();
	public void discardPersonalityCard(PersonalityCardName discardedCard);
	public IPersonalityCard getPersonalityCard(PersonalityCardName cardName);
	
	// Random event management
	public IRandomEventCard getRandomEvent();
	
	// Player management
	public void registerNewPlayer(String playerName);
	public IPlayer getCurrentPlayer();
	public IPlayer iteratePlayer();
	public IPlayer[] getAllPlayers();
	IPlayer getPlayer(int playerIndex);
	IPlayer getPlayer(String playerName);
	
	// --------------------- added
	public int getNumberOfPlayers();
	
	// Game utility functions
	public void initializeGame() throws Exception;
	public void persistGame() throws Exception;
	public int rollDie();
	void close();
}

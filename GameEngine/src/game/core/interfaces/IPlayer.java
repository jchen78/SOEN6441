package game.core.interfaces;

import java.util.List;

import game.core.enums.CityAreaData;
import game.core.enums.PlayerCardName;
import game.engine.GameManager;
import game.engine.MapArea;
import game.error.InvalidOperationException;

public interface IPlayer {

	public String getName();

	public int getIndex();

	public int getMoney();

	public void addMoney(int amount) throws InvalidOperationException;

	public void removeMoney(int _amount) throws InvalidOperationException;

	public List<PlayerCardName> getPlayerCards();

	public CityAreaData[] getCityCards();

	public void addPlayerCard(PlayerCardName drawPlayerCard);

	public int getNumberOfMinionsInHand();

	public void returnMinionsToHand(int numberMinions);
	
	public void removeMinionsFromHand(int numberMinions);

	public List<ICityArea> getPopulatedAreas(IGameInstance gameInstance);

}

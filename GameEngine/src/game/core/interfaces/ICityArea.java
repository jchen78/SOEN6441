package game.core.interfaces;

import game.action.sequence.interfaces.IVisitee;
import game.core.enums.CityAreaName;
import game.engine.CardType;
import game.error.InvalidOperationException;

public interface ICityArea extends IVisitee {
	public CityAreaName getCityAreaName();
	public String getCurrentState();
	public CardType getCardType();
	public boolean isAdjacent(CityAreaName areaName);
	public void addMinions(IPlayer currentPlayer, int numberMinions) throws InvalidOperationException;
}

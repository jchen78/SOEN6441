package game.core.interfaces;

import game.action.sequence.interfaces.IVisitee;
import game.core.enums.CityAreaData;
import game.engine.CardType;
import game.error.InvalidOperationException;

public interface ICityArea extends IVisitee {
	public CityAreaData getCityAreaName();
	public String getCurrentState();
	public CardType getCardType();
	public boolean isAdjacent(CityAreaData areaName);
	
	/**
	 * This method completes the action of depositing minions on the city area instance. All
	 * external verifications (making sure that the minion placement is legal, retrieving
	 * a minion from another city area if the player has no minions in hand, etc.) must be
	 * completed prior to this method --only minimal verifications will be performed here.
	 * @param currentPlayer The minion's owner.
	 * @param numberMinions The number of minions (usually one). This number cannot be negative; to remove minions, please use the appropriate method instead.
	 * @throws InvalidOperationException Thrown when the number of minions is invalid.
	 */
	public void addMinions(IPlayer currentPlayer, int numberMinions) throws InvalidOperationException;
	public int[] getMinions();
}
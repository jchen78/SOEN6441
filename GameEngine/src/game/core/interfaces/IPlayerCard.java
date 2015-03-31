package game.core.interfaces;

import game.action.sequence.interfaces.IVisitee;
import game.core.enums.PlayerCardName;
import game.engine.CardType;
import game.error.EntityNotSetException;

public interface IPlayerCard extends ISelectable {
	public CardType getCardType() throws EntityNotSetException;
	public IVisitee[] getActions();
	public PlayerCardName getName();
}

package game.core.interfaces;

import game.action.sequence.interfaces.IVisitee;
import game.engine.CardType;
import game.error.EntityNotSetException;

public interface IPlayerCard extends IVisitee {

	CardType getCardType() throws EntityNotSetException;

}

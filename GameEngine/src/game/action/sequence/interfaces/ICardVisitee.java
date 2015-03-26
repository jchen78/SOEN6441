package game.action.sequence.interfaces;

import game.engine.CardType;
import game.error.EntityNotSetException;

public interface ICardVisitee extends IVisitee {
	CardType getCardType() throws EntityNotSetException;
}




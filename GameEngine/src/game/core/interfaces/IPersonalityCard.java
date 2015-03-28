package game.core.interfaces;

import game.action.sequence.interfaces.IVisitee;

/**
 * This interface represents a personality card. The "accept" method
 * will verify whether the visitor's current player has met the winning
 * conditions required by the specific instance of the personality card.
 * If this is the case, a GameOverException will be thrown.
 * @see IVisitee
 * @author Jason
 *
 */
public interface IPersonalityCard extends IVisitee {
}

/**
 * File: IVisitee.java
 **/
package game.action.sequence.interfaces;

import game.action.sequence.visitee.GameOverException;
import game.core.interfaces.ISelectable;
import game.error.EntityNotSetException;
import game.error.InvalidOperationException;

public interface IVisitee extends ISelectable {
	/**
	 * All of the returned objects MUST be visited. Calling this method means that this is the
	 * action that must be currently evaluated.
	 * @param visitor The visitor through which this action was selected.
	 * @throws EntityNotSetException 
	 * @throws InvalidOperationException Thrown when the game is in an invalid state. Unrecoverable; must restart from previous save.
	 */
	public void accept(IVisitor visitor) throws GameOverException, EntityNotSetException, InvalidOperationException;
}

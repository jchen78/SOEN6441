/**
 * File: IVisitee.java
 **/
package game.action.sequence.interfaces;

import game.action.sequence.visitee.GameOverException;
import game.core.interfaces.ISelectable;
import game.error.EntityNotSetException;

public interface IVisitee extends ISelectable {
	/**
	 * All of the returned objects MUST be visited. Calling this method means that this is the
	 * action that must be currently evaluated.
	 * @param visitor The visitor through which this action was selected.
	 * @throws EntityNotSetException 
	 */
	public void accept(IVisitor visitor) throws GameOverException, EntityNotSetException;
}

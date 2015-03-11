package game.action.sequence.interfaces;

import game.action.sequence.visitee.GameOverException;

import java.util.Queue;

public interface IVisitee {
	/**
	 * All of the returned objects MUST be visited. Calling this method means that this is the
	 * action that must be currently evaluated.
	 * @param visitor The visitor through which this action was selected.
	 * @return List of visitees which are prerequisites for the main functionality exposed via the visitor if any, or an empty list (not null) otherwise.
	 */
	Queue<IVisitee> accept(IVisitor visitor) throws GameOverException;
	
	/**
	 * Gets a description for printing.
	 * @return Description of the current action.
	 */
	String getDescription();
}

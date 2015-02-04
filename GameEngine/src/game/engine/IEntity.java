package game.engine;

import game.error.InvalidEntityNameException;

/**
 * Represents a game entity (defined as an object from the physical game that possesses some predefined characteristics).
 */
public interface IEntity {
	void setEntity(String entityName) throws InvalidEntityNameException;
}

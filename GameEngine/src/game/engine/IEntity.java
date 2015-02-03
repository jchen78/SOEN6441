package game.engine;

import game.error.InvalidEntityNameException;

public interface IEntity {
	void setEntity(String entityName) throws InvalidEntityNameException;
}

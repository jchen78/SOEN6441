package game.action;

import game.engine.GameManager;
import game.error.ActionException;

public interface IExecutableAction extends IAction {
	void execute(GameManager gameInstance, String currentPlayer) throws Exception;
}

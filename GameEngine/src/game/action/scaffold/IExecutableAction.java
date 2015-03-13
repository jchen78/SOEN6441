package game.action.scaffold;

import java.util.Queue;

import game.action.scaffold.dataGathering.IActionDataGatherer;
import game.engine.GameManager;

/**
 * 
 * IExecutableAction.java
 * 
 * /

public interface IExecutableAction extends IAction {
	Queue<IActionDataGatherer> execute(GameManager gameInstance, String currentPlayer) throws Exception;
}

package game.action.scaffold;

import game.engine.GameManager;

public interface IConditionalAction extends IOptionalAction {
	boolean isActionApplicable(GameManager gameInstance, String currentPlayerName) throws Exception;
}

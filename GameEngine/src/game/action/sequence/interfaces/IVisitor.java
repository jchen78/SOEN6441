package game.action.sequence.interfaces;

import game.action.sequence.visitee.GameOverException;
import game.core.interfaces.IGameInstance;
import game.core.interfaces.IPlayer;

public interface IVisitor extends IGameInstance {
	void visit(IVisitee visitee) throws GameOverException;

	IVisitee selectAction(IVisitee[] choices);
	IPlayer getCurrentPlayer();
	void setCurrentPlayer(IPlayer currentPlayer);
	void clearCurrentPlayer();
}
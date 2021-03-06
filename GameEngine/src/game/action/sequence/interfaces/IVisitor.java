package game.action.sequence.interfaces;

import game.action.sequence.visitee.GameOverException;
import game.core.interfaces.IGameInstance;
import game.core.interfaces.IPlayer;
import game.core.interfaces.ISelectable;
import game.error.EntityNotSetException;
import game.error.InvalidOperationException;

public interface IVisitor extends IGameInstance {
	void visit(IVisitee visitee) throws GameOverException, EntityNotSetException, InvalidOperationException;

	ISelectable selectAction(ISelectable[] choices);
	ISelectable selectAction(ISelectable[] availableChoices, ISelectable[] unavailableChoices);
	IPlayer getCurrentPlayer();
	void setCurrentPlayer(IPlayer currentPlayer);
	void clearCurrentPlayer();
}
package game.action.sequence.interfaces;

import java.util.List;

import game.core.interfaces.IGameInstance;
import game.core.interfaces.IPlayer;
import game.engine.GameManager;
import game.engine.Player;

public interface IVisitor extends IGameInstance {
	void visit(IVisitee visitee);

	IVisitee selectAction(List<IVisitee> choices);
	IPlayer getCurrentPlayer();
	void setCurrentPlayer(IPlayer currentPlayer);
	void clearCurrentPlayer();
}

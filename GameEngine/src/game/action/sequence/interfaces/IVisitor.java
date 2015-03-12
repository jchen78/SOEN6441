package game.action.sequence.interfaces;

import java.util.List;

import game.engine.GameManager;
import game.engine.Player;

public interface IVisitor {
	void visit(IVisitee visitee);

	IVisitee selectAction(List<IVisitee> choices);
	Player getCurrentPlayer();
	void setCurrentPlayer(Player currentPlayer);
	GameManager getGameInstance();
}

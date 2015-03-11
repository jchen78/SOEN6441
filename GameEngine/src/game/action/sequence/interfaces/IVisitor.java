package game.action.sequence.interfaces;

import game.engine.GameManager;
import game.engine.Player;

import java.util.List;

public interface IVisitor {
	void visit(IVisitee visitee);
	void visit(List<IVisitee> concurrentlyPossibleVisitees);
	
	IVisitee getCurrentAction();
	Player getCurrentPlayer();
	void setCurrentPlayer(Player currentPlayer);
	GameManager getGameInstance();
}

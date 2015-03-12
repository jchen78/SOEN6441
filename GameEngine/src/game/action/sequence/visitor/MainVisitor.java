package game.action.sequence.visitor;

import java.util.List;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.action.sequence.visitee.GameOverException;
import game.engine.GameManager;
import game.engine.Player;

public class MainVisitor implements IVisitor {
	private GameManager _gameInstance;
	private Player _currentPlayer;
	
	private Printer _printer;
	private Selector _selector;
	
	public MainVisitor(GameManager gameInstance, Printer printer, Selector selector) {
		_gameInstance = gameInstance;
		_printer = printer;
		_selector = selector;
	}

	@Override
	public void visit(IVisitee visitee) {
		_printer.print(visitee);
		
		try {
			visitee.accept(this);
		} catch (GameOverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Player getCurrentPlayer() {
		return _currentPlayer;
	}

	@Override
	public void setCurrentPlayer(Player currentPlayer) {
		_currentPlayer = currentPlayer;
	}

	@Override
	public GameManager getGameInstance() {
		return _gameInstance;
	}

	@Override
	public IVisitee selectAction(List<IVisitee> choices) {
		_printer.print(choices);
		_selector.select(choices);
		return null;
	}

}

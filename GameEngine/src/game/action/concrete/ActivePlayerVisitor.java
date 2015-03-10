package game.action.concrete;

import java.util.List;
import java.util.Queue;

import game.action.scaffold.*;
import game.action.scaffold.dataGathering.*;
import game.engine.GameManager;
import game.engine.MapArea;
import game.error.ActionException;

public class ActivePlayerVisitor implements IActionVisitor {
	private GameManager _gameInstance;
	private String _currentPlayerName;
	private IActionPrinter _printer;
	private IActionSelector _selector;
	
	public ActivePlayerVisitor(GameManager gameInstance, String currentPlayerName, IActionPrinter printer, IActionSelector selector) {
		_gameInstance = gameInstance;
		_currentPlayerName = currentPlayerName;
		_printer = printer;
		_selector = selector;
	}
	
	
	@Override
	public void visit(IActionVisitee visitee) throws ActionException {
		List<IAction> listOfActions = visitee.getActions();
		if (listOfActions.size() == 0)
			return;
		
		_printer.print(listOfActions);
		int selection = _selector.getSelection();
		if (selection == listOfActions.size())
			return;
		
		IAction selectedAction = listOfActions.get(Math.abs(selection));
		visitee.registerAction(selectedAction);
		
		if (selection < 0 && selectedAction instanceof IOptionalAction) {
			visit(visitee);
			return;
		}
		
		if (selectedAction instanceof IExecutableAction)
			try { executeAction((IExecutableAction)selectedAction); }
			catch (Exception e) { throw new ActionException(this, visitee, e); }
	}
	
	protected void executeAction(IExecutableAction action) throws Exception {
		Queue<IActionDataGatherer> dataRequiredForAction = action.execute(_gameInstance, _currentPlayerName);
		for (IActionDataGatherer gatherer : dataRequiredForAction) {
			if (gatherer instanceof IInterruptibleDataGatherer) {
				gatherInterruptibleData((IInterruptibleDataGatherer)gatherer);
				return;
			}
			if (gatherer instanceof IActionWithEntityRemoval)
				effectEntityRemoval((IActionWithEntityRemoval)gatherer);
		}
	}


	private void gatherInterruptibleData(IInterruptibleDataGatherer gatherer) throws ActionException {
		String interruptorName = gatherer.getInterruptorName();
		if (interruptorName == null || interruptorName.equals(_currentPlayerName))
			return;
		
		new ActivePlayerVisitor(_gameInstance, interruptorName, _printer, _selector).visit(gatherer);
	}


	private void effectEntityRemoval(IActionWithEntityRemoval gatherer) throws Exception {
		// TODO: Determine choices available.
		gatherer.setAreaForRemoval(MapArea.getInternalNames()[_selector.getSelection()]);
	}
}

package game.action.concrete;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import game.action.scaffold.ActionType;
import game.action.scaffold.IExecutableAction;
import game.action.scaffold.IOptionalAction;
import game.action.scaffold.InterruptType;
import game.action.scaffold.dataGathering.IActionDataGatherer;
import game.engine.GameManager;
import game.engine.InterruptPlayerCard;
import game.engine.PlayerCard;

public class PlayInterruptCardAction implements IOptionalAction {
	private IExecutableAction _interruptedAction;
	private ActionType _typeToInterrupt;
	private InterruptType _typeOfInterruption;
	
	public PlayInterruptCardAction(IExecutableAction interruptedAction, ActionType typeOfActionToInterrupt, InterruptType typeOfInterruption) {
		_typeToInterrupt = typeOfActionToInterrupt;
		_typeOfInterruption = typeOfInterruption;
	}
	
	@Override
	public String getDescription() {
		return "Abort the current action with an interrupt card. Current action: " + _interruptedAction.getDescription();
	}
	
	@Override
	public Queue<IActionDataGatherer> execute(GameManager gameInstance, String currentPlayer) throws Exception {
		Queue<IActionDataGatherer> possibleInterruptActions = new LinkedList<IActionDataGatherer>();
		List<String> allPlayerCardNames = gameInstance.getPlayer(currentPlayer).getPlayerCards();
		for (String cardName : allPlayerCardNames) {
			PlayerCard currentCard = gameInstance.getPlayerCard(cardName);
			if (!(currentCard instanceof InterruptPlayerCard))
				continue;
			
			InterruptPlayerCard currentInterruptCard = (InterruptPlayerCard)currentCard;
			for (ActionType interruptibleAction : currentInterruptCard.getInterruptibleActionTypes()) {
				if (interruptibleAction != _typeToInterrupt)
					continue;
				
				for (InterruptType interruptType : currentInterruptCard.getInterruptTypes()) {
					if (interruptType != _typeOfInterruption)
						continue;
					
					possibleInterruptActions.add(new PlayCard(currentCard));
					break;
				}
			}
		}
		
		return possibleInterruptActions;
	}
	
	public void completeExecution(GameManager gameInstance, String currentPlayer) throws Exception {
		_interruptedAction.execute(gameInstance, currentPlayer);
	}
}
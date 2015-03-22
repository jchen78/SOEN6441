package game.action.sequence.visitee;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.core.interfaces.IPlayer;
import game.engine.ActionName;
import game.engine.ActionType;
import game.engine.MapArea;
import game.engine.Player;
import game.error.InvalidOperationException;

public class RemoveMinionVisitee implements IVisitee {
	private String _targetPlayerName;
	private String _targetAreaName;
	private ActionType _sourceOfAction;
	
	public RemoveMinionVisitee(String targetPlayerName, String targetAreaName, ActionType sourceOfAction) {
		_targetPlayerName = targetPlayerName;
		_targetAreaName = targetAreaName;
		_sourceOfAction = sourceOfAction;
	}

	@Override
	public void accept(IVisitor visitor) throws GameOverException {
		IPlayer currentPlayer = visitor.getCurrentPlayer();
		IPlayer targetPlayer = visitor.getGameInstance().getPlayer(_targetPlayerName);
		MapArea targetArea = visitor.getGameInstance().getMapArea(_targetAreaName);
		
		if (_targetPlayerName != currentPlayer.getName()) {
			EvaluateEscapeActionVisitee interruptVisitee = new EvaluateEscapeActionVisitee(targetPlayer, _sourceOfAction, ActionName.RemoveMinion);
			visitor.visit(interruptVisitee);
			
			if (interruptVisitee.isActionInterrupted())
				return;
		}
		
		try {
			targetArea.removeMinions(targetPlayer, 1);
		} catch (InvalidOperationException e) {
			// TODO Gracefully exit and alerts the user that an error has occurred.
			e.printStackTrace();
		}
	}

	@Override
	public String getDescription() {
		return "Remove one of " + _targetPlayerName + "'s minions from " + _targetAreaName;
	}
}

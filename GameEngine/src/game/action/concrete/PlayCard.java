package game.action.concrete;

import java.util.List;

import game.action.scaffold.ActionType;
import game.action.scaffold.IAction;
import game.action.scaffold.IActionVisitee;
import game.action.scaffold.IActionVisitor;
import game.action.scaffold.dataGathering.IActionDataGatherer;
import game.engine.PlayerCard;
import game.error.ActionException;

public class PlayCard implements IActionDataGatherer, IActionVisitee {
	PlayerCard _cardToPlay;
	
	public PlayCard(PlayerCard cardToPlay) {
		_cardToPlay = cardToPlay;
	}

	@Override
	public void accept(IActionVisitor visitor) throws ActionException {
		visitor.visit(this);
	}

	@Override
	public ActionType getType() {
		return ActionType.PlayerCard;
	}

	@Override
	public List<IAction> getActions() throws ActionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerAction(IAction action) throws ActionException {
		// TODO Auto-generated method stub

	}
}
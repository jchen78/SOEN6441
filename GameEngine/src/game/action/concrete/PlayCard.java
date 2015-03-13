package game.action.concrete;

import java.util.List;
import java.util.Queue;

import game.action.scaffold.ActionType;
import game.action.scaffold.IAction;
import game.action.scaffold.IActionVisitee;
import game.action.scaffold.IActionVisitor;
import game.action.scaffold.dataGathering.IActionDataGatherer;
import game.engine.PlayerCard;
import game.error.ActionException;

/**
 * PlayCard class to select the card 
 *
 */
public class PlayCard implements IActionDataGatherer, IActionVisitee {
	PlayerCard _cardToPlay;
	
	public PlayCard(PlayerCard cardToPlay) {
		_cardToPlay = cardToPlay;
	}

	@Override
	public void accept(IActionVisitor visitor) throws ActionException {
		// TODO Auto-generated method stub

	}

	@Override
	public ActionType getType() {
		// TODO Auto-generated method stub
		return null;
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

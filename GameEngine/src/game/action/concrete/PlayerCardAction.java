package game.action.concrete;

import game.action.scaffold.ActionType;
import game.action.scaffold.IAction;
import game.action.scaffold.IActionVisitee;
import game.action.scaffold.IActionVisitor;
import game.engine.*;
import game.error.ActionException;
import game.error.EntityNotSetException;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PlayerCardAction implements IActionVisitee, IAction {
	private Player _owner;
	private PlayerCard _card;
	private int _cardIndex;
	private IActionVisitor _visitor;
	private Queue<IAction> _actions;
	
	public PlayerCardAction(GameManager gameInstance, Player owner, PlayerCard card) {
		_owner = owner;
		_card = card;
		_cardIndex = 0;
		for (String ownedCardName : _owner.getPlayerCards())
			if (ownedCardName.equals(card.getCardname()))
				break;
			else
				_cardIndex++;
	}
	
	@Override
	public String getDescription() {
		return "Play a card: " + _card.getCardname() + ".";
	}

	@Override
	public void accept(IActionVisitor visitor) throws ActionException {
		_visitor = visitor;
		visitor.visit(this);
	}

	@Override
	public ActionType getType() {
		return ActionType.ActionGroup;
	}

	@Override
	public List<IAction> getActions() throws ActionException {
		_owner.getPlayerCards().remove(_cardIndex);
		LinkedList<IAction> _currentHead = new LinkedList<IAction>();
		try {
			initializeActions();
		} catch (EntityNotSetException e) {
			e.printStackTrace();
			throw new ActionException(_visitor, this, e);
		}
		
		if (!_actions.isEmpty()) {
			_currentHead.add(_actions.peek());
		}
		
		return _currentHead;
	}

	private void initializeActions() throws EntityNotSetException {
		if (_actions == null)
			_actions = new LinkedList<IAction>(_card.getActions());
	}

	@Override
	public void registerAction(IAction action) {
		_actions.poll(); // Equivalent to dequeue, which isn't available in the built-in data structure.
	}
}
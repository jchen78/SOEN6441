package game.action.concrete;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import game.action.scaffold.ActionType;
import game.action.scaffold.IAction;
import game.action.scaffold.IActionVisitor;
import game.action.scaffold.InterruptType;
import game.action.scaffold.dataGathering.IActionDataGatherer;
import game.action.scaffold.dataGathering.IInterruptibleDataGatherer;
import game.engine.GameManager;
import game.engine.IMoneyHolder;
import game.error.ActionException;

public class TransferMoneyDataGatherer implements IInterruptibleDataGatherer {
	private int _transferAmount;
	private IMoneyHolder _source;
	private IMoneyHolder _target;
	private ActionType _type;
	
	public TransferMoneyDataGatherer(int transferAmount, IMoneyHolder sourceAccount, IMoneyHolder targetAccount, ActionType actionType) {
		_transferAmount = transferAmount;
		_type = actionType;
	}

	@Override
	public String getInterruptorName() {
		return _source.getAccountHolderName();
	}

	@Override
	public void accept(IActionVisitor visitor) throws ActionException {
		visitor.visit(this);
	}

	@Override
	public ActionType getType() {
		return _type;
	}

	@Override
	public List<IAction> getActions() throws ActionException {
		LinkedList<IAction> interruptibleAction = new LinkedList<IAction>();
		interruptibleAction.add(new PlayInterruptCardAction(this, _type, InterruptType.Text));
		
		return interruptibleAction;
	}

	@Override
	public void registerAction(IAction action) throws ActionException {
	}

	@Override
	public Queue<IActionDataGatherer> execute(GameManager gameInstance,	String currentPlayer) throws Exception {
		_source.removeMoney(_transferAmount);
		_target.addMoney(_transferAmount);
		return new LinkedList<IActionDataGatherer>();
	}
}
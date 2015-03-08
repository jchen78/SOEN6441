package game.engine;

import game.action.scaffold.ActionType;
import game.action.scaffold.InterruptType;

public class InterruptPlayerCard extends PlayerCard implements IInterruptCard {
	private ActionType[] _interruptibleActionTypes;
	private InterruptType[] _interruptTypes;
	
	public InterruptPlayerCard(ActionType[] interruptibleActionTypes, InterruptType[] interruptTypes) {
		_interruptibleActionTypes = interruptibleActionTypes;
		_interruptTypes = interruptTypes;
	}
	
	@Override
	public ActionType[] getInterruptibleActionTypes() {
		return _interruptibleActionTypes;
	}

	@Override
	public InterruptType[] getInterruptTypes() {
		return _interruptTypes;
	}

}

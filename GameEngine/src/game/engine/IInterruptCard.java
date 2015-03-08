package game.engine;

import game.action.scaffold.ActionType;
import game.action.scaffold.InterruptType;

public interface IInterruptCard {
	ActionType[] getInterruptibleActionTypes();
	InterruptType[] getInterruptTypes();
}

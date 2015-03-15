package game.engine;


public interface IInterruptCard {
	ActionType[] getInterruptibleActionTypes();
	InterruptType[] getInterruptTypes();
}

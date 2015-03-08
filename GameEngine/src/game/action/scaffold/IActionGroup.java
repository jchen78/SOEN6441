package game.action.scaffold;

import java.util.List;

public interface IActionGroup extends IAction {
	List<IAction> getActions();
}
package game.action.sequence.visitee;

import java.util.List;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.error.EntityNotSetException;

public class MultipleActionSelector implements IVisitee {
	private List<IVisitee> _nonExclusiveActions;
	
	public MultipleActionSelector(List<IVisitee> nonExclusiveActions) {
		_nonExclusiveActions = nonExclusiveActions;
	}
	
	@Override
	public void accept(IVisitor visitor) throws GameOverException, EntityNotSetException {
		while (_nonExclusiveActions.size() > 0) {
			IVisitee[] currentChoices = _nonExclusiveActions.toArray(new IVisitee[_nonExclusiveActions.size()]);
			IVisitee selectedAction = (IVisitee)visitor.selectAction(currentChoices);
			_nonExclusiveActions.remove(selectedAction);
			visitor.visit(selectedAction);
		}
	}

	@Override
	public String getDescription() {
		return "Select one option.";
	}
}

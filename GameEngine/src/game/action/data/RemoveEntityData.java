package game.action.data;

import java.util.List;

import game.action.scaffold.dataGathering.IActionDataGatherer;
import game.action.scaffold.dataGathering.IActionWithEntityRemoval;
import game.error.InvalidOperationException;

/**
 * 
 * RemoveEntityData.java
 * 
 */

public class RemoveEntityData implements IActionDataGatherer {
	private List<String> _targetAreaNames;
	private IActionWithEntityRemoval _requestingAction;
	
	public RemoveEntityData(IActionWithEntityRemoval requestingAction, List<String> validAreaNames) {
		_requestingAction = requestingAction;
		_targetAreaNames = validAreaNames;
	}
	
	public List<String> getValidAreas() {
		return _targetAreaNames;
	}
	
	public void setArea(String selectedAreaName, String playerName) throws IllegalArgumentException {
		String validSelectedAreaName = null;
		for (String validAreaName : _targetAreaNames) {
			if (validAreaName.equals(selectedAreaName)) {
				validSelectedAreaName = validAreaName;
				break;
			}
		}
		if (validSelectedAreaName == null)
			throw new IllegalArgumentException();
		
		try {
			_requestingAction.setAreaForRemoval(validSelectedAreaName);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
}

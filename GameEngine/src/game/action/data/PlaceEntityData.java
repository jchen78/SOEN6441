package game.action.data;

import java.util.List;

import game.action.scaffold.dataGathering.IActionDataGatherer;
import game.action.scaffold.dataGathering.IActionWithEntityPlacement;

public class PlaceEntityData implements IActionDataGatherer {
	private IActionWithEntityPlacement _requestingAction;
	private List<String> _targetAreaNames;
	
	public PlaceEntityData(IActionWithEntityPlacement requestingAction, List<String> validAreaNames) {
		_requestingAction = requestingAction;
		_targetAreaNames = validAreaNames;
	}
	
	public void setTargetArea(String selectedAreaName) throws Exception {
		String validSelectedAreaName = null;
		for (String validAreaName : _targetAreaNames) {
			if (validAreaName.equals(selectedAreaName)) {
				validSelectedAreaName = validAreaName;
				break;
			}
		}
		
		if (validSelectedAreaName == null)
			throw new IllegalArgumentException();
		
		_requestingAction.setAreaForPlacement(validSelectedAreaName);
	}
}

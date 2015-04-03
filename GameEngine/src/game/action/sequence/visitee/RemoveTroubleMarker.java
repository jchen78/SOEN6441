package game.action.sequence.visitee;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.core.enums.CityAreaData;
import game.core.interfaces.ICityArea;

public class RemoveTroubleMarker implements IVisitee {
	CityAreaData _areaToRemove;
	
	public RemoveTroubleMarker() {
	}

	@Override
	public void accept(IVisitor visitor) throws GameOverException {
		ICityArea area = visitor.getCityArea(_areaToRemove);
		area.removeTroubleMarker();
	}

	@Override
	public String getDescription() {
		return "Remove any trouble marker from the area affected";
	}

}

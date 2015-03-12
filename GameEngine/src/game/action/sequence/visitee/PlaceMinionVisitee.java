package game.action.sequence.visitee;

import java.util.LinkedList;
import java.util.List;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.engine.*;

public class PlaceMinionVisitee implements IVisitee {
	private List<IVisitee> validAreas;
	
	public PlaceMinionVisitee() {
		
	}

	@Override
	public void accept(IVisitor visitor) throws GameOverException {
		GameManager gameInstance = visitor.getGameInstance();
		Player currentPlayer = visitor.getCurrentPlayer();
		List<MapArea> currentlyPopulatedAreas = new LinkedList<MapArea>();
		for (String mapAreaName : MapArea.getInternalNames()) {
			MapArea currentArea = gameInstance.getMapArea(mapAreaName);
			if (currentArea.getMinions()[currentPlayer.getIndex()] > 0) {
				currentlyPopulatedAreas.add(currentArea);
			}
		}
		
		if (currentPlayer.getNumberOfMinionsInHand() == 0) {
			RemoveMinionVisitee removeMinionVisitee = new RemoveMinionVisitee(currentPlayer.getName());
			removeMinionVisitee.accept(visitor);
			MapArea removedArea = removeMinionVisitee.getRemovedMapArea();
			if (removedArea.getMinions()[currentPlayer.getIndex()] == 0)
				currentlyPopulatedAreas.remove(removedArea);
		}
		
		List<MapArea> possibleChoices;
		List<IVisitee> translatedChoices = new LinkedList<IVisitee>();
		if (currentlyPopulatedAreas.size() == 0) {
			possibleChoices = gameInstance.getAllMapAreas();
			for (MapArea mapArea : possibleChoices)
				translatedChoices.add(new SelectionVisitee(mapArea.getName()));
		}
		else {
			possibleChoices = new LinkedList<MapArea>();
			for (String mapAreaName : MapArea.getInternalNames()) {
				for (MapArea populatedArea : currentlyPopulatedAreas) {
					if (populatedArea.isAdjacent(mapAreaName)) {
						possibleChoices.add(gameInstance.getMapArea(mapAreaName));
						translatedChoices.add(new SelectionVisitee(mapAreaName));
						break;
					}
				}
			}
		}
		
		
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public MapArea getNewlyPopulatedArea() {
		return null;
	}
}

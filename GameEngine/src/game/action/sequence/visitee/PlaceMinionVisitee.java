package game.action.sequence.visitee;

import java.util.LinkedList;
import java.util.List;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.engine.*;
import game.error.InvalidOperationException;

public class PlaceMinionVisitee implements IVisitee {
	private MapArea _selectedArea;
	private ActionType _sourceOfAction;
	
	public PlaceMinionVisitee(ActionType sourceOfAction) {
		_sourceOfAction = sourceOfAction;
	}

	@Override
	public void accept(IVisitor visitor) throws GameOverException {
		GameManager gameInstance = visitor.getGameInstance();
		Player currentPlayer = visitor.getCurrentPlayer();
		List<MapArea> currentlyPopulatedAreas = currentPlayer.getPopulatedAreas(gameInstance);
		List<MapArea> possibleChoices;
		List<IVisitee> translatedChoices = new LinkedList<IVisitee>();
		
		if (currentPlayer.getNumberOfMinionsInHand() == 0) {
			List<IVisitee> areasForRemoval = new LinkedList<IVisitee>();
			for (MapArea populatedArea : currentlyPopulatedAreas)
				areasForRemoval.add(new SelectionVisitee(populatedArea.getName()));
			
			SingleActionSelector removalSelector = new SingleActionSelector(translatedChoices, "map areas");
			removalSelector.accept(visitor);
			SelectionVisitee selection = (SelectionVisitee)removalSelector.getSelection();
			
			RemoveMinionVisitee removeMinionVisitee = new RemoveMinionVisitee(currentPlayer.getName(), selection.getDescription(), _sourceOfAction);
			removeMinionVisitee.accept(visitor);
			MapArea removedArea = gameInstance.getMapArea(selection.getDescription());
			if (removedArea.getMinions()[currentPlayer.getIndex()] == 0)
				currentlyPopulatedAreas.remove(removedArea);
		}
		
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
		
		SingleActionSelector selector = new SingleActionSelector(translatedChoices, "map areas");
		selector.accept(visitor);
		SelectionVisitee selection = (SelectionVisitee)selector.getSelection();
		for (MapArea mapArea : possibleChoices) {
			if (mapArea.getName() == selection.getDescription()) {
				_selectedArea = mapArea;
				try {
					_selectedArea.addMinions(currentPlayer, 1);
				} catch (InvalidOperationException e) {
					// TODO Re-select an area
					e.printStackTrace();
				}
				
				break;
			}
		}
	}

	@Override
	public String getDescription() {
		return "Place a minion on the board.";
	}
	
	public MapArea getNewlyPopulatedArea() {
		return _selectedArea;
	}
}

package game.action.sequence.visitee;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.core.enums.CityAreaData;
import game.core.interfaces.ICityArea;
import game.core.interfaces.IGameInstance;
import game.core.interfaces.IPlayer;
import game.engine.*;
import game.error.EntityNotSetException;
import game.error.InvalidOperationException;

public class PlaceMinionVisitee implements IVisitee {
	private ICityArea _selectedArea;
	private ActionType _sourceOfAction;
	
	public PlaceMinionVisitee(ActionType sourceOfAction) {
		_sourceOfAction = sourceOfAction;
	}

	@Override
	public void accept(IVisitor visitor) throws GameOverException, EntityNotSetException, InvalidOperationException {
		IPlayer currentPlayer = visitor.getCurrentPlayer();
		ICityArea[] originalPopulatedAreas = currentPlayer.getPopulatedAreas(visitor);
		List<ICityArea> currentlyPopulatedAreas = Arrays.asList(originalPopulatedAreas);
		List<ICityArea> possibleChoices;
		List<IVisitee> translatedChoices = new LinkedList<IVisitee>();
		
		if (currentPlayer.getNumberOfMinionsInHand() == 0) {
			List<IVisitee> areasForRemoval = new LinkedList<IVisitee>();
			for (ICityArea populatedArea : currentlyPopulatedAreas)
				areasForRemoval.add(new SelectionVisitee(populatedArea.getCityAreaName().getDescriptiveName()));
			
			SingleActionSelector removalSelector = new SingleActionSelector(originalPopulatedAreas, "map areas");
			removalSelector.accept(visitor);
			SelectionVisitee selection = (SelectionVisitee)removalSelector.getSelection();
			
			ICityArea removedArea = currentlyPopulatedAreas.get(selection.getSelectedIndex());
			try {
				removedArea.removeMinions(currentPlayer, 1);
			} catch (InvalidOperationException e) {
				e.printStackTrace();
			}
			currentPlayer.returnMinionsToHand(1);
			if (removedArea.getMinions()[currentPlayer.getIndex()] == 0)
				currentlyPopulatedAreas.remove(removedArea);
		}
		
		if (currentlyPopulatedAreas.size() == 0) {
			possibleChoices = Arrays.asList(visitor.getAllCityAreas());
			for (ICityArea mapArea : possibleChoices)
				translatedChoices.add(new SelectionVisitee(mapArea.getCityAreaName().getDescriptiveName()));
		}
		else {
			possibleChoices = new LinkedList<ICityArea>();
			for (CityAreaData mapAreaName : CityAreaData.values()) {
				for (ICityArea populatedArea : currentlyPopulatedAreas) {
					if (populatedArea.isAdjacent(mapAreaName)) {
						possibleChoices.add(visitor.getCityArea(mapAreaName));
						translatedChoices.add(new SelectionVisitee(mapAreaName.getDescriptiveName()));
						break;
					}
				}
			}
		}
		
		IVisitee[] areasForPlacement = translatedChoices.toArray(new IVisitee[translatedChoices.size()]);
		SingleActionSelector selector = new SingleActionSelector(areasForPlacement, "map areas");
		selector.accept(visitor);
		SelectionVisitee selection = (SelectionVisitee)selector.getSelection();
		for (ICityArea mapArea : possibleChoices) {
			if (mapArea.getCityAreaName().getDescriptiveName() == selection.getDescription()) {
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
	
	public ICityArea getNewlyPopulatedArea() {
		return _selectedArea;
	}
}

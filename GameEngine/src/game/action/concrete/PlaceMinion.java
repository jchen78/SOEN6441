package game.action.concrete;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import game.action.data.*;
import game.action.scaffold.IOptionalAction;
import game.action.scaffold.dataGathering.IActionDataGatherer;
import game.action.scaffold.dataGathering.IActionWithEntityPlacement;
import game.action.scaffold.dataGathering.IActionWithEntityRemoval;
import game.engine.GameManager;
import game.engine.MapArea;
import game.engine.Player;
import game.error.InvalidOperationException;

public class PlaceMinion implements IOptionalAction, IActionWithEntityPlacement, IActionWithEntityRemoval {
	private GameManager _gameInstance;
	private Player _currentPlayer;
	
	@Override
	public Queue<IActionDataGatherer> execute(GameManager gameInstance, String currentPlayerName) throws Exception {
		_gameInstance = gameInstance;
		_currentPlayer = _gameInstance.getPlayer(currentPlayerName);
		
		int numberMinionsInHand = _currentPlayer.getNumberOfMinionsInHand();
		int currentPlayerIndex = _currentPlayer.getIndex();
		LinkedList<String> currentlyOccupiedAreaNames = new LinkedList<String>();
		for (String currentAreaName : MapArea.getInternalNames()) {
			MapArea currentArea = gameInstance.getMapArea(currentAreaName);
			if (currentArea.getMinions()[currentPlayerIndex] > 0)
				currentlyOccupiedAreaNames.add(currentAreaName);
		}
		
		Queue<IActionDataGatherer> requiredData = new LinkedList<IActionDataGatherer>();
		if (numberMinionsInHand > 0)
			_currentPlayer.setMinion(numberMinionsInHand - 1);
		else
			requiredData.add(new RemoveEntityData(this, currentlyOccupiedAreaNames));
		
		List<String> validTargetAreas = new LinkedList<String>();
		if (currentlyOccupiedAreaNames.size() == 0)
			validTargetAreas = Arrays.asList(MapArea.getInternalNames());
		else
		for (String areaName : MapArea.getInternalNames()) {
			MapArea currentArea = gameInstance.getMapArea(areaName);
			for (String occupiedAreaName : currentlyOccupiedAreaNames)
				if (currentArea.equals(occupiedAreaName) || currentArea.isAdjacent(occupiedAreaName)) {
					validTargetAreas.add(areaName);
					break;
				}
		}
		
		requiredData.add(new PlaceEntityData(this, currentlyOccupiedAreaNames));
		return requiredData;
	}

	@Override
	public void setAreaForRemoval(String selectedAreaName) throws Exception {
		MapArea selectedArea = _gameInstance.getMapArea(selectedAreaName);
		selectedArea.removeMinions(_gameInstance.getPlayer(this._currentPlayer.getIndex()),1);
	}

	@Override
	public void setAreaForPlacement(String targetAreaName) throws Exception {
		MapArea selectedArea = _gameInstance.getMapArea(targetAreaName);
		selectedArea.addMinions(_gameInstance.getPlayer(this._currentPlayer.getIndex()), 1);
	}
}
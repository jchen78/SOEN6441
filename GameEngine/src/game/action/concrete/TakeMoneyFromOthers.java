package game.action.concrete;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import game.action.scaffold.ActionType;
import game.action.scaffold.IOptionalAction;
import game.action.scaffold.dataGathering.IActionDataGatherer;
import game.engine.GameManager;
import game.engine.Player;

/**
 * 
 * TakeMoneycardfromOthers to perform some specific instruction on player cards
 * 
 */
public class TakeMoneyFromOthers implements IOptionalAction {
	private Player _currentPlayer;

	@Override
	public Queue<IActionDataGatherer> execute(GameManager gameInstance, String currentPlayerName) throws Exception {
		_currentPlayer = gameInstance.getPlayer(currentPlayerName);
		
		Queue<IActionDataGatherer> interruptibleAction = new LinkedList<IActionDataGatherer>();
		List<Player> allPlayers = gameInstance.getAllPlayers();
		for (Player player : allPlayers) {
			if (player.getName().equals(currentPlayerName))
				continue;
			
			int playerMoney = player.getMoney();
			int currentAmount = playerMoney > 2 ? 2 : playerMoney;
			if (currentAmount == 0)
				continue;
			
			interruptibleAction.add((IActionDataGatherer)new TransferMoneyDataGatherer(currentAmount, player, _currentPlayer, ActionType.PlayerCardText));
		}
		
		return new LinkedList<IActionDataGatherer>();
	}
}

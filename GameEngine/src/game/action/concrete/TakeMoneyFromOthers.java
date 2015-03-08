package game.action.concrete;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import game.action.scaffold.IOptionalAction;
import game.action.scaffold.dataGathering.IActionDataGatherer;
import game.engine.GameManager;
import game.engine.Player;

public class TakeMoneyFromOthers implements IOptionalAction {
	@Override
	public Queue<IActionDataGatherer> execute(GameManager gameInstance, String currentPlayerName) throws Exception {
		Player currentPlayer = null;
		int totalAmount = 0;
		
		List<Player> allPlayers = gameInstance.getAllPlayers();
		int[] amountToDeduct = new int[allPlayers.size()];
		int currentIndex = 0;
		for (Player player : allPlayers) {
			if (player.getName().equals(currentPlayerName)) {
				currentPlayer = player;
				amountToDeduct[currentIndex++] = 0;
				continue;
			}
			
			int playerMoney = player.getPlayerMoney();
			int currentAmount = playerMoney > 2 ? 2 : playerMoney;
			amountToDeduct[currentIndex++] = currentAmount;
			totalAmount += currentAmount;
		}
		
		if (currentPlayer == null) {
			throw new IllegalArgumentException("currentPlayerName");
		}
		
		currentIndex = 0;
		for (Player player : allPlayers) {
			player.setPlayerMoney(player.getPlayerMoney() - amountToDeduct[currentIndex++]);
		}
		
		currentPlayer.setPlayerMoney(totalAmount + currentPlayer.getPlayerMoney());
		return new LinkedList<IActionDataGatherer>();
	}
}

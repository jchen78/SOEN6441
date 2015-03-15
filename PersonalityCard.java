package game.engine;

import game.error.InvalidEntityNameException;
import game.engine.GameManager;
import game.engine.MapArea;
import game.engine.Player;

import java.util.HashMap;
import java.util.Map;



public enum PersonalityCardWinningCondition{
	
	LordDeWorde(WinningConditionHelper::hasWonByControlledAreas),
	
	CommanderVimes((playerCount, player, gameInstance) -> gameInstance.hasPlayerCardsLeft()),
	
	LordVetinari((playerCount, player, gameInstance) ->
	gameInstance.getTotalMinionCountForPlayer(player) >= WinningConditionHelper.getMinimumRequiredMinions(playerCount)
	),
	
	LordSelachii(WinningConditionHelper::hasWonByControlledAreas),
	
	LordRust(WinningConditionHelper::hasWonByControlledAreas),
	
	DragonKingOfArms((playerCount, player, gameInstance) -> mapArea.getTotalNumberOfTroubleMarkers() == 8),
	
	Chrysoprase((playerCount, player, gameInstance) -> player.getMoney() >= 50),
	;
	
	private WinningCondition<Integer, Player, GameManager, Boolean> winningConditionChecker;
	
	private PersonalityCardWinningCondition(WinningCondition<Integer, Player, GameManager, Boolean> winningConditionChecker) {
		this.winningConditionChecker = winningConditionChecker;
	}
	
	public WinningCondition<Integer, Player, GameManager, Boolean> getWinningConditionChecker() {
		return winningConditionChecker;
	}
	
	/**
	 * Checks if the player holding this personality card has won the game. It
	 * also prints a message on stdout to indicate this.
	 * 
	 * @param numberOfPlayers
	 * @param player
	 * @param gameInstance
	 * @return true if the player holding this personality card has won the game
	 *         by meeting the card's conditions, false otherwise.
	 */
	public boolean hasWon(int numberOfPlayers, Player player, GameManager gameInstance) {
				if (hasPlayerWon) {
			System.out.println(player.getName() + " has won the game with " +name());
		}
		return hasPlayerWon;
	}
	
	/**
	 * This can be used as an aid for lookups as needed by different personality cards.
	 */
	private static class WinningConditionHelper {
		
		private static final int CONDITION_COUNT = 3;

		/**
		 * Depending on the number of players you have to have a certain number
		 * of minions in different areas on the board.
		 */
		
		private static final Map<Integer, Integer> minimumRequiredMinions = 
				new HashMap<>(CONDITION_COUNT);
		static {
			minimumRequiredMinions.put(2, 11);
			minimumRequiredMinions.put(3, 10);
			minimumRequiredMinions.put(4, 9);
		}
		
		private static final Map<Integer, Integer> minimumControlledAreas = 
				new HashMap<>(CONDITION_COUNT);
		static {
			minimumControlledAreas.put(2, 7);
			minimumControlledAreas.put(3, 5);
			minimumControlledAreas.put(4, 4);
		}
	
	private static Boolean hasWonByControlledAreas(Integer numberOfPlayers, Player player, GameManager gameInstance) {
		return gameInstance.getNumberOfAreasControlled(player) >= minimumControlledAreas.get(numberOfPlayers);
	}

}
	public boolean winningConditionChecker(){
		return(hasPlayerWon);
	}
	
}
/**
 * 
 */
package tests;

import static org.junit.Assert.*;
import game.action.sequence.interfaces.IVisitor;
import game.core.enums.CityAreaData;
import game.core.interfaces.ICityArea;
import game.core.interfaces.IGameInstance;
import game.engine.GameManager;




import org.junit.Test;


public class GameManagerTest {
	IGameInstance GI;
	/**
	 * Test method for {@link game.engine.GameManager#getActiveCityCardsForPlayer(game.core.interfaces.IPlayer)}.
	 */
	@Test
	public void testGetActiveCityCardsForPlayer() {


	}

	/**
	 * Test method for {@link game.engine.GameManager#getCityArea(game.core.enums.CityAreaData)}.
	 */
<<<<<<< HEAD
	@Test
	public void testGetCityArea() throws NullPointerException {
		GameManager gm= new GameManager();
		CityAreaData selectedArea1 = CityAreaData.Dimwell;
		CityAreaData selectedArea2 = CityAreaData.DollySisters;
		ICityArea value1 = gm.getCityArea();

		ICityArea value2 = gm.getCityArea(selectedArea2);
		assertNotEquals(value1, value2);
		
		
	}
=======
//	@Test
//	public void testGetCityArea() throws NullPointerException {
//		
//		CityAreaData selectedArea1 = CityAreaData.Dimwell;
//		CityAreaData selectedArea2 = CityAreaData.DollySisters;
//		ICityArea value1 = GI.getCityArea(selectedArea1);
//		ICityArea value2 = GI.getCityArea(selectedArea2);
//		assertNotEquals(value1, value2);
//	}
>>>>>>> 251b19f80ddb9a8ad8713f1b97db0dce37eba0fc

	/**
	 * Test method for {@link game.engine.GameManager#discardPersonalityCard(game.core.enums.PersonalityCardName)}.
	 */
	@Test
	public void testDiscardPersonalityCard() {
		
	}

	/**
	 * Test method for {@link game.engine.GameManager#getPersonalityCard(game.core.enums.PersonalityCardName)}.
	 */
	@Test
	public void testGetPersonalityCardPersonalityCardName() {
		
	}

}

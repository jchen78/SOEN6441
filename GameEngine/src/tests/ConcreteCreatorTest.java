/**
 * 
 */
package tests;

import static org.junit.Assert.*;
import game.core.interfaces.IPlayer;
import game.engine.ConcreteCreator;
import game.engine.Player;
import game.error.InvalidOperationException;

import org.junit.Test;


public class ConcreteCreatorTest {
	 ConcreteCreator cc= new ConcreteCreator();
	/**
	 * Test method for {@link game.engine.ConcreteCreator#createPlayer(int, java.lang.String)}.
	 * @throws InvalidOperationException 
	 * @throws NumberFormatException 
	 */
	 
	@Test
	public void testCreatePlayer() throws NumberFormatException, InvalidOperationException {
		IPlayer valueBefore = new Player(0);
		cc.createPlayer(2, "Tom;b;1;2;LordDeWorde;20");
		IPlayer valueAfter = new Player(2);
		assertEquals(valueAfter, cc.createPlayer(2, "Tom;b;1;2;LordDeWorde;20"));
	}

	/**
	 * Test method for {@link game.engine.ConcreteCreator#createCity(java.lang.String)}.
	 */
	@Test
	public void testCreateCity() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.engine.ConcreteCreator#create(game.core.enums.PersonalityCardName)}.
	 */
	@Test
	public void testCreatePersonalityCardName() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.engine.ConcreteCreator#create(game.core.enums.PlayerCardName)}.
	 */
	@Test
	public void testCreatePlayerCardName() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.engine.ConcreteCreator#create(game.core.enums.RandomEventCardName)}.
	 */
	@Test
	public void testCreateRandomEventCardName() {
		fail("Not yet implemented");
	}

}

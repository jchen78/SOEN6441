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
		// Why would the two values be equal?
		// assertEquals(valueAfter, cc.createPlayer(2, "Tom;b;1;2;LordDeWorde;20"));
		
		// I think you may have wanted to do this:
		IPlayer value1 = cc.createPlayer(0, "Tom;b;1;2;LordDeWorde;20");
		IPlayer value2 = cc.createPlayer(0, "Tom;b;1;2;LordDeWorde;20");
		assertEquals(value1.getIndex(), value2.getIndex());
		// ... or overload the equals method, and call assertEquals(value1, value2);
	}

	/**
	 * Test method for {@link game.engine.ConcreteCreator#createCity(java.lang.String)}.
	 */
	@Test
	public void testCreateCity() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.engine.ConcreteCreator#create(game.core.enums.PersonalityCardName)}.
	 */
	@Test
	public void testCreatePersonalityCardName() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.engine.ConcreteCreator#create(game.core.enums.PlayerCardName)}.
	 */
	@Test
	public void testCreatePlayerCardName() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.engine.ConcreteCreator#create(game.core.enums.RandomEventCardName)}.
	 */
	@Test
	public void testCreateRandomEventCardName() {
		//fail("Not yet implemented");
	}

}

/**
 * 
 */
package tests;

import static org.junit.Assert.*;
import game.core.enums.PersonalityCardName;
import game.core.enums.PlayerCardName;
import game.core.enums.RandomEventCardName;
import game.core.interfaces.ICityArea;
import game.core.interfaces.IPersonalityCard;
import game.core.interfaces.IPlayer;
import game.core.interfaces.IPlayerCard;
import game.core.interfaces.IRandomEventCard;
import game.engine.ConcreteCreator;
import game.engine.Player;
import game.engine.PlayerCard;
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
		IPlayer value1 = cc.createPlayer(0, "Tom;b;1;2;LordDeWorde;20");
		IPlayer value2 = cc.createPlayer(0, "Tom;b;1;2;LordDeWorde;20");
		assertEquals(value1.getIndex(), value2.getIndex());
		// ... or overload the equals method, and call assertEquals(value1, value2);
	}

	/**
	 * Test method for {@link game.engine.ConcreteCreator#createCity(java.lang.String)}.
	 * @throws InvalidOperationException 
	 */
	@Test
	public void testCreateCity() throws InvalidOperationException {
		ICityArea value1= cc.createCity("LongWall;0;0;0;2;0;0;0;");
		ICityArea value2= cc.createCity("LongWall;0;0;0;2;0;0;0;");
		assertEquals(value1.getCurrentState(), value2.getCurrentState());
	}

	@Test
	public void testCreateCityNotValid() {
		ICityArea value1 = null;
		ICityArea value2 = null;
		try {
			value1 = cc.createCity("LongWall;0;0;;2;0;0;0;");
			value2 = cc.createCity("LongWall;0;0;0;2;0;0;0;");
			assertEquals(value1.getCurrentState(), value2.getCurrentState());
		} catch (InvalidOperationException e) {
			System.out.println("String not Matched");
		}
	}

	
	/**
	 * Test method for {@link game.engine.ConcreteCreator#create(game.core.enums.PersonalityCardName)}.
	 */
	@Test
	public void testCreatePersonalityCardName() {
		PersonalityCardName name1 = PersonalityCardName.LordSelachii;
		PersonalityCardName name2 = PersonalityCardName.LordRust;
		IPersonalityCard value1 = cc.create(name1);
		IPersonalityCard value2 = cc.create(name2);
		assertNotEquals(value1, value2);
	}

	/**
	 * Test method for {@link game.engine.ConcreteCreator#create(game.core.enums.PlayerCardName)}.
	 */
	@Test
	public void testCreatePlayerCardName() {
		PlayerCardName name1 = PlayerCardName.TheBeggarsGuild;
		PlayerCardName name2 = PlayerCardName.TheBeggarsGuild;
		IPlayerCard value1 = cc.create(name1);
		IPlayerCard value2 = cc.create(name2);
		assertSame(value1.getName(), value2.getName());
	}

	/**
	 * Test method for {@link game.engine.ConcreteCreator#create(game.core.enums.RandomEventCardName)}.
	 */
	@Test
	public void testCreateRandomEventCardName() {
		RandomEventCardName name1 = RandomEventCardName.BloodyStupidJohnson;
		RandomEventCardName name2 = RandomEventCardName.Riots;
		IRandomEventCard value1 = cc.create(name1);
		IRandomEventCard value2 = cc.create(name1);
		assertEquals(value1.getCardName(), value2.getCardName());
	}

}

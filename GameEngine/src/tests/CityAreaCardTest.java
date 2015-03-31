/**
 * 
 */
package tests;

import static org.junit.Assert.*;
import game.engine.CityAreaCard;
import game.error.InvalidEntityNameException;

import org.junit.Test;


public class CityAreaCardTest {

	CityAreaCard cac = new CityAreaCard();

	/**
	 * Test method for {@link game.engine.CityAreaCard#getCardname()}.
	 * @throws InvalidEntityNameException 
	 */
	@Test
	public void testGetCardname() throws InvalidEntityNameException {
		String valueBefore = cac.getCardname();
		System.out.println(valueBefore);
		String st= "Dimwell";
		cac.setEntity(st);
		assertEquals("Dimwell", cac.getCardname());
		String valueAfter = cac.getCardname();
		System.out.println(valueAfter);
	}
}

	
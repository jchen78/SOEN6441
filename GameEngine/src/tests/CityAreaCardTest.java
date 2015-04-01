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
	//	System.out.println(valueBefore);
		String st= "Dimwell";
		cac.setEntity(st);
		assertEquals("Dimwell", cac.getCardname());
		String valueAfter = cac.getCardname();
	//	System.out.println(valueAfter);
	}



	@Test
	public void testGetCardnameForDifferentValue() throws InvalidEntityNameException {
	String valueBefore = cac.getCardname();
//	System.out.println(valueBefore);
	String st= "UnrealEstate";
	cac.setEntity(st);
	assertNotEquals("NapHill", cac.getCardname());
	String valueAfter = cac.getCardname();
//	System.out.println(valueAfter);
}
	
	@Test
	public void testGetCardnameException() {
	String valueBefore = cac.getCardname();
	String st= "Unrealstate";
	try {
		cac.setEntity(st);
		assertNotEquals("NapHill", cac.getCardname());
	} catch (InvalidEntityNameException e) {
		System.out.println("Invalid AreaCard String");
	}
	
}
}

	
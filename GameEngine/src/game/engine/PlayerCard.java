package game.engine;

import java.util.Arrays;

import game.error.EntityNotSetException;
import game.error.InvalidEntityNameException;

/**
 * This class represents the green- or brown-bordered player cards. It is yet incomplete: only arbitrary (temporary) names and border color are stored.
 */
public class PlayerCard extends Card {
	public static final String[] greenBorderedCardNames = {
		"Green00",
		"Green01",
		"Green02",
		"Green03",
		"Green04",
		"Green05",
		"Green06",
		"Green07",
		"Green08",
		"Green09",
		"Green10",
		"Green11",
		"Green12",
		"Green13",
		"Green14",
		"Green15",
		"Green16",
		"Green17",
		"Green18",
		"Green19",
		"Green20",
		"Green21",
		"Green22",
		"Green23",
		"Green24",
		"Green25",
		"Green26",
		"Green27",
		"Green28",
		"Green29",
		"Green30",
		"Green31",
		"Green32",
		"Green33",
		"Green34",
		"Green35",
		"Green36",
		"Green37",
		"Green38",
		"Green39",
		"Green40",
		"Green41",
		"Green42",
		"Green43",
		"Green44",
		"Green45",
		"Green46",
		"Green47"
	};
	
	public static final String[] brownBorderedCardNames = {
		"Brown00",
		"Brown01",
		"Brown02",
		"Brown03",
		"Brown04",
		"Brown05",
		"Brown06",
		"Brown07",
		"Brown08",
		"Brown09",
		"Brown10",
		"Brown11",
		"Brown12",
		"Brown13",
		"Brown14",
		"Brown15",
		"Brown16",
		"Brown17",
		"Brown18",
		"Brown19",
		"Brown20",
		"Brown21",
		"Brown22",
		"Brown23",
		"Brown24",
		"Brown25",
		"Brown26",
		"Brown27",
		"Brown28",
		"Brown29",
		"Brown30",
		"Brown31",
		"Brown32",
		"Brown33",
		"Brown34",
		"Brown35",
		"Brown36",
		"Brown37",
		"Brown38",
		"Brown39",
		"Brown40",
		"Brown41",
		"Brown42",
		"Brown43",
		"Brown44",
		"Brown45",
		"Brown46",
		"Brown47",
		"Brown48",
		"Brown49",
		"Brown50",
		"Brown51",
		"Brown52"
	};
	
	private String _borderColor = null;
	
	@Override
	public void setEntity(String entityName) throws InvalidEntityNameException {
		if (entityName == null)
			throw new InvalidEntityNameException();
		
		if (Arrays.asList(greenBorderedCardNames).contains(entityName))
			_borderColor = "Green";
		else if (Arrays.asList(brownBorderedCardNames).contains(entityName))
			_borderColor = "Brown";
		else
			throw new InvalidEntityNameException();
	}
	
	/**
	 * Gets the border color, as loaded when the <code>setEntity</code> method was called.
	 * @see setEntity
	 * @return Border color ("Green" or "Brown").
	 * @throws EntityNotSetException Thrown when this method is called before the <code>setEntity</code> method.
	 */
	public String getBorderColor() throws EntityNotSetException {
		if (_borderColor == null)
			throw new EntityNotSetException();
		
		return _borderColor;
	}
}

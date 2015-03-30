package tests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import game.engine.PlayerCard;
import game.error.EntityNotSetException;
import game.error.InvalidEntityNameException;

import org.junit.Before;
import org.junit.Test;

public class PlayerCardTests {
	private PlayerCard _playerCard;
	
	@Before public void InitializePlayerCard() {
		_playerCard = new PlayerCard("playerCardName");
	}

	public void WhenInitializingWithNullShouldThrowException() {
		InvalidEntityNameException expectedException = null;
		try { _playerCard.setEntity(null); } catch (InvalidEntityNameException e) { expectedException = e; }
		assertNotNull(expectedException);
	}

	public void WhenInitializingWithInvalidCardNameShouldThrowException() {
		InvalidEntityNameException expectedException = null;
		String invalidName = UUID.randomUUID().toString();
		
		try { _playerCard.setEntity(invalidName); } catch (InvalidEntityNameException e) { expectedException = e; }
		
		assertNotNull(expectedException);
	}
	
	public void GivenUninitializedCardWhenGettingBorderColorShouldThrowException() {
		EntityNotSetException expectedException = null;
		try { _playerCard.getBorderColor(); } catch (EntityNotSetException e) { expectedException = e; }
		assertNotNull(expectedException);
	}
}

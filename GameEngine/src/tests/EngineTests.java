package tests;

import static org.junit.Assert.*;
import game.engine.Bank;

import org.junit.Test;

public class EngineTests {
	private final static int BANK_INITIAL_AMOUNT = 120;
	
	@Test
	public void GivenNoTransactionsWhenQueryingBankShouldReturnInitialValue() {
		Bank newBank = new Bank(BANK_INITIAL_AMOUNT);
		assertEquals(BANK_INITIAL_AMOUNT, newBank.getBalance());
	}

}

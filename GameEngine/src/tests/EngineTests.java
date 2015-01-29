package tests;

import static org.junit.Assert.*;
import game.engine.Bank;

import org.junit.Before;
import org.junit.Test;

public class EngineTests {
	private final static int BANK_INITIAL_AMOUNT = 120;
	
	private Bank _testBank;
	
	@Before
	public void Initialize()
	{
		_testBank = new Bank(BANK_INITIAL_AMOUNT);
	}
	
	@Test
	public void GivenNoTransactionsWhenQueryingBankShouldReturnInitialValue() {
		assertEquals(BANK_INITIAL_AMOUNT, _testBank.getBalance());
	}
	
	@Test
	public void GivenDebitTransactionWhenQueryingBankShouldReturnCorrectAmount() {
		// Arrange
		int someAmount = 6;
		int newBalance = BANK_INITIAL_AMOUNT - someAmount;
		
		// Act
		_testBank.withdraw(someAmount);
		
		// Assert
		assertEquals(newBalance, _testBank.getBalance());
	}
}

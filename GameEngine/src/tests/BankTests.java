package tests;

import static org.junit.Assert.*;
import game.engine.Bank;
import game.engine.Bank.BankWithdrawalException;

import org.junit.Before;
import org.junit.Test;

public class BankTests {
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
	public void GivenDebitTransactionWhenQueryingBankShouldReturnCorrectAmount() throws BankWithdrawalException {
		// Arrange
		int someAmount = 6;
		int newBalance = BANK_INITIAL_AMOUNT - someAmount;
		
		// Act
		_testBank.withdraw(someAmount);
		
		// Assert
		assertEquals(newBalance, _testBank.getBalance());
	}
	
	@Test
	public void WhenWithdrawingTooMuchMoneyShouldThrowException() {
		BankWithdrawalException expectedException = null;
		try { _testBank.withdraw(BANK_INITIAL_AMOUNT + 1); } catch (BankWithdrawalException e) { expectedException = e; }
		assertEquals("Withdrawal exceeds current balance.", expectedException.getMessage());
	}
	
	@Test
	public void WhenWithdrawingNegativeAmountShouldThrowException() {
		BankWithdrawalException expectedException = null;
		try { _testBank.withdraw(-1); } catch (BankWithdrawalException e) { expectedException = e; }
		assertEquals("Withdrawal must have a positive value.", expectedException.getMessage());
	}
}

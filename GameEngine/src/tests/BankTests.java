package tests;

import static org.junit.Assert.*;
import game.engine.Bank;
import game.engine.Bank.BankException;
import game.engine.BankError;

import org.junit.Before;
import org.junit.Test;

public class BankTests {
	private final static int BANK_INITIAL_AMOUNT = 120;
	
	private Bank _testBank;
	
	@Before
	public void Initialize() throws BankException
	{
		_testBank = new Bank(BANK_INITIAL_AMOUNT);
	}
	
	@Test
	public void WhenInitializingGreaterThanMaximumShouldThrowException() {
		BankException expectedException = null;
		try { _testBank = new Bank(BANK_INITIAL_AMOUNT + 1); } catch (BankException e) { expectedException = e; }
		assertEquals(BankError.InitialAmountExceedsLimit, expectedException.getDetails());
	}
	
	@Test
	public void GivenNoTransactionsWhenQueryingBankShouldReturnInitialValue() {
		assertEquals(BANK_INITIAL_AMOUNT, _testBank.getBalance());
	}
	
	@Test
	public void GivenDebitTransactionWhenQueryingBankShouldReturnCorrectAmount() throws BankException {
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
		BankException expectedException = null;
		try { _testBank.withdraw(BANK_INITIAL_AMOUNT + 1); } catch (BankException e) { expectedException = e; }
		assertEquals(BankError.WithdrawalExceedsLimit, expectedException.getDetails());
	}
	
	@Test
	public void WhenWithdrawingNegativeAmountShouldThrowException() {
		BankException expectedException = null;
		try { _testBank.withdraw(-1); } catch (BankException e) { expectedException = e; }
		assertEquals(BankError.WithdrawalMustBePositive, expectedException.getDetails());
	}
}

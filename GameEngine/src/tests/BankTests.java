package tests;

import static org.junit.Assert.*;

import java.util.Random;

import game.engine.Bank;
import game.error.BankException;
import game.error.code.BankError;

import org.junit.Before;
import org.junit.Test;

public class BankTests {
	private final static int BANK_MAXIMUM_AMOUNT = 120;
	
	private int _bankInitialAmount;
	private Bank _testBank;
	
	@Before public void Initialize() throws BankException
	{
		_bankInitialAmount = BANK_MAXIMUM_AMOUNT - new Random().nextInt(BANK_MAXIMUM_AMOUNT - 7);
		_testBank = new Bank();
		_testBank.setBalance(_bankInitialAmount);
	}
	
	@Test public void WhenInitializingGreaterThanMaximumShouldThrowException() {
		BankException expectedException = null;
		try { _testBank.setBalance(BANK_MAXIMUM_AMOUNT + 1); } catch (BankException e) { expectedException = e; }
		assertEquals(BankError.AMOUNT_EXCEEDS_LIMIT, expectedException.getDetails());
	}
	
	@Test public void WhenInitializingNegativeAmountShouldThrowException() {
		BankException expectedException = null;
		try { _testBank.setBalance(-1); } catch (BankException e) { expectedException = e; }
		assertEquals(BankError.AMOUNT_MUST_BE_POSITIVE, expectedException.getDetails());
	}
	
	@Test public void GivenNoTransactionsWhenQueryingBankShouldReturnInitialValue() {
		assertEquals(_bankInitialAmount, _testBank.getBalance());
	}
	
	@Test public void GivenDebitTransactionWhenQueryingBankShouldReturnCorrectAmount() throws BankException {
		// Arrange
		int someAmount = 6;
		int newBalance = _bankInitialAmount - someAmount;
		
		// Act
		_testBank.withdraw(someAmount);
		
		// Assert
		assertEquals(newBalance, _testBank.getBalance());
	}
	
	@Test public void WhenWithdrawingTooMuchMoneyShouldThrowException() {
		BankException expectedException = null;
		try { _testBank.withdraw(_bankInitialAmount + 1); } catch (BankException e) { expectedException = e; }
		assertEquals(BankError.AMOUNT_EXCEEDS_LIMIT, expectedException.getDetails());
	}
	
	@Test public void WhenWithdrawingNegativeAmountShouldThrowException() {
		BankException expectedException = null;
		try { _testBank.withdraw(-1); } catch (BankException e) { expectedException = e; }
		assertEquals(BankError.AMOUNT_MUST_BE_POSITIVE, expectedException.getDetails());
	}
}

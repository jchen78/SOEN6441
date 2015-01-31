package game.engine;

/**
 * This class is intended to perform bank operations.
 * It currently allows initialization and withdrawal.
 * Some game-based sanity checks are performed on the
 * inputs for the methods; if these verifications fail,
 * a BankException will be thrown.
 */
public class Bank {
	private int _currentBalance;
	private static final int MAXIMUM_AMOUNT = 120;
	
	public Bank() {
		_currentBalance = MAXIMUM_AMOUNT;
	}
	
	/**
	 * The bank is initialized to the specified amount.
	 * If a new game is started, please use the parameterless
	 * constructor.
	 * @param initialAmount Current balance of the entire bank.
	 * @throws BankException Thrown when initial amount is not valid. For details, please refer to the getDetails() method.
	 */
	public Bank(int initialAmount) throws BankException {
		if (initialAmount > MAXIMUM_AMOUNT)
			throw new BankException(BankError.AMOUNT_EXCEEDS_LIMIT);
		
		if (initialAmount < 0)
			throw new BankException(BankError.AMOUNT_MUST_BE_POSITIVE);
		
		_currentBalance = initialAmount;
	}
	
	/**
	 * Performed when a player must withdraw money. This method must not be used
	 * to return money to the bank.
	 * @param amount Amount given to a player.
	 * @throws BankException Thrown when the amount exceeds the funds of the bank, or when the amount is negative.
	 */
	public void withdraw(int amount) throws BankException {
		if (amount > _currentBalance)
			throw new BankException(BankError.AMOUNT_EXCEEDS_LIMIT);
		
		if (amount < 0)
			throw new BankException(BankError.AMOUNT_MUST_BE_POSITIVE);
		
		_currentBalance -= amount;
	}
	
	/**
	 * This method is used to query the current state of the bank.
	 * @return The funds currently held by the bank.
	 */
	public int getBalance() {
		return _currentBalance;
	}
	
	public class BankException extends Exception {
		private static final long serialVersionUID = 1L;
		private BankError _error;
		
		public BankException(BankError error) {
			_error = error;
		}
		
		public BankError getDetails() {
			return _error;
		}
	}
}

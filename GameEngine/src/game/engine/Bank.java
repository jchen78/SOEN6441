package game.engine;

public class Bank {
	private int _currentBalance;
	private static final int MAXIMUM_AMOUNT = 120;
	
	public Bank(int initialAmount) throws BankException {
		if (initialAmount > MAXIMUM_AMOUNT)
			throw new BankException(BankError.InitialAmountExceedsLimit);
		
		_currentBalance = MAXIMUM_AMOUNT;
	}
	
	public void withdraw(int amount) throws BankException {
		if (amount > _currentBalance)
			throw new BankException(BankError.WithdrawalExceedsLimit);
		
		if (amount < 0)
			throw new BankException(BankError.WithdrawalMustBePositive);
		
		_currentBalance -= amount;
	}
	
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

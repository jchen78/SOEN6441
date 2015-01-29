package game.engine;

public class Bank {
	private int _currentBalance;
	
	public Bank(int initialAmount) {
		_currentBalance = initialAmount;
	}
	
	public void withdraw(int amount) throws BankWithdrawalException {
		if (amount > _currentBalance)
			throw new BankWithdrawalException("Withdrawal exceeds current balance.");
		
		if (amount < 0)
			throw new BankWithdrawalException("Withdrawal must have a positive value.");
		
		_currentBalance -= amount;
	}
	
	public int getBalance() {
		return _currentBalance;
	}
	
	public class BankWithdrawalException extends Exception {
		private static final long serialVersionUID = 1L;
		
		public BankWithdrawalException(String message) {
			super(message);
		}
	}
}

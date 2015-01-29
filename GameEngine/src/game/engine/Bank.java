package game.engine;

public class Bank {
	private int _currentBalance;
	
	public Bank(int initialAmount) {
		_currentBalance = initialAmount;
	}
	
	public void withdraw(int amount) {
		_currentBalance -= amount;
	}
	
	public int getBalance() {
		return _currentBalance;
	}
}

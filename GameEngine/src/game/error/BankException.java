package game.error;

import game.error.code.BankError;

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

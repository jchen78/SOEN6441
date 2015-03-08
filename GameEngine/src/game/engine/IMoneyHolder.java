package game.engine;

import game.error.InvalidOperationException;

public interface IMoneyHolder {
	String getAccountHolderName();
	int getMoney();
	void removeMoney(int amount) throws InvalidOperationException;
	void addMoney(int amount) throws InvalidOperationException;
}

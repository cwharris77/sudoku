/**
 * Provides common methods for three types of bank accounts
 * 
 * @author Cooper Harris
 *
 */

public abstract class BankAccount {

	protected double balance;
	protected int accountNumber;

	public BankAccount(int accountNumber) {
		this.balance = 0;
		this.accountNumber = accountNumber;
	}

	public abstract boolean deposit(double amount);

	public abstract boolean withdraw(double amount);

	public abstract double getBalance();

	public abstract int getAccountNum();

}

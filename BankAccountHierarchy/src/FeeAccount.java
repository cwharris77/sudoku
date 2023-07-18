// Cooper Harris

public class FeeAccount extends BankAccount {
	private double fee;

	public FeeAccount(int accountNumber) {
		super(accountNumber);
		this.fee = 2.00;
	}

	/**
	 * Any amount over 0 will be added to the account balance
	 * 
	 * @param amount the amount to add to the account
	 */
	@Override
	public boolean deposit(double amount) {
		if (amount > 0) {
			balance += amount;
		} else {
			System.out.println("The deposit amount should be above 0");
			return false;
		}
		return true;
	}

	/**
	 * If the amount given is greater than 0 and is less than or equal to the
	 * account balance including the two dollar transaction fee the amount will be
	 * taken from the account balance
	 * 
	 * @param amount the amount to withdraw from the account
	 */
	@Override
	public boolean withdraw(double amount) {
		if (amount > 0) {
			amount += fee;

			if (amount <= balance) {
				balance -= amount;
			} else {
				System.out.println("You don't have enough to withdraw this amount");
				return false;
			}
		} else {
			System.out.println("withdraw amount should be greater than 0");
			return false;
		}
		return true;
	}

	/**
	 * Getter for the balance
	 */
	@Override
	public double getBalance() {
		return balance;
	}

	/**
	 * Getter for the account number
	 */
	@Override
	public int getAccountNum() {
		return accountNumber;
	}

}

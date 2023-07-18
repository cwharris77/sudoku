// Cooper Harris

public class SafeAccount extends BankAccount {
	private double credit;

	public SafeAccount(int accountNumber) {
		super(accountNumber);
		this.credit = 1000.00;
	}

	/**
	 * Any amount over 0 will be added to the account balance If there is a loan the
	 * amount will be added to the loan then anything left over is added to the
	 * balance
	 * 
	 * @param amount the amount to add to the account
	 */
	@Override
	public boolean deposit(double amount) {
		if (amount > 0) {
			if (credit < 1000.00) {
				double creditToFill = 1000.00 - credit;

				if (amount <= creditToFill) {
					credit += amount;
					amount = 0;
				} else {
					credit += creditToFill;
					amount = amount - creditToFill;
				}
			}
			balance += amount;
		} else {
			System.out.println("The deposit amount should be above 0");
			return false;
		}
		return true;
	}

	/**
	 * Up to 1000 over the account balance may be withdrawn. Anything over the
	 * account balance will be added to the current loan.
	 * 
	 * @param amount the amount to withdraw from the account
	 */
	@Override
	public boolean withdraw(double amount) {
		if (amount > 0) {
			if (amount <= balance) {
				balance -= amount;
			} else if (amount <= balance + credit) {
				double leftOver = amount - balance;

				balance -= balance;
				credit -= leftOver;
			} else {
				System.out.println("You don't have enough to withdraw this amount");
				return false;
			}
		} else {
			System.out.println("Withdraw amount should be greater than 0");
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
	 * Returns the current loan tied to the account
	 */
	public double getLoan() {
		return 1000.00 - credit;
	}

	/**
	 * Getter for the account number
	 */
	@Override
	public int getAccountNum() {
		return accountNumber;
	}

}

// Cooper Harris

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BankAccountTest {

	BankAccount regularAccount = new RegularAccount(100);
	BankAccount feeAccount = new FeeAccount(200);
	BankAccount safeAccount = new SafeAccount(300);

	@Test
	void testRegular() {
		assertEquals(100, regularAccount.getAccountNum());

		assertEquals(false, regularAccount.deposit(0));
		assertEquals(false, regularAccount.deposit(-4));
		assertEquals(false, regularAccount.deposit(-893));

		regularAccount.deposit(200);
		assertEquals(200.00, regularAccount.getBalance());

		regularAccount.deposit(123.00);
		assertEquals(323.00, regularAccount.getBalance());

		regularAccount.withdraw(10.99);
		assertEquals(312.01, regularAccount.getBalance());

		assertEquals(false, regularAccount.withdraw(0));
		assertEquals(false, regularAccount.withdraw(-4));
		assertEquals(false, regularAccount.withdraw(7000.89));

	}

	@Test
	void testFee() {
		assertEquals(200, feeAccount.getAccountNum());

		assertEquals(false, feeAccount.deposit(0));
		assertEquals(false, feeAccount.deposit(-10));
		assertEquals(false, feeAccount.deposit(-45));

		feeAccount.deposit(10);
		assertEquals(10, feeAccount.getBalance());

		feeAccount.deposit(15.50);
		assertEquals(25.5, feeAccount.getBalance());

		feeAccount.withdraw(1.50);
		assertEquals(22, feeAccount.getBalance());

		assertEquals(false, feeAccount.withdraw(0));
		assertEquals(false, feeAccount.withdraw(-999));
		assertEquals(false, feeAccount.withdraw(42.02));

	}

	@Test
	void testSafe() {
		assertEquals(300, safeAccount.getAccountNum());

		assertEquals(false, safeAccount.deposit(0));
		assertEquals(false, safeAccount.deposit(-10));
		assertEquals(false, safeAccount.deposit(-45));

		safeAccount.deposit(90000);
		safeAccount.withdraw(90000);
		assertEquals(0, safeAccount.getBalance());

		safeAccount.deposit(11);
		assertEquals(11, safeAccount.getBalance());

		safeAccount.deposit(10);
		assertEquals(21, safeAccount.getBalance());

		safeAccount.withdraw(1021.00);
		assertEquals(0, safeAccount.getBalance());

		assertEquals(1000, ((SafeAccount) safeAccount).getLoan());

		safeAccount.deposit(500.00);
		assertEquals(0, safeAccount.getBalance());
		assertEquals(500, ((SafeAccount) safeAccount).getLoan());

		safeAccount.deposit(2000);
		assertEquals(1500, safeAccount.getBalance());
		assertEquals(0, ((SafeAccount) safeAccount).getLoan());

		assertEquals(false, safeAccount.withdraw(0));
		assertEquals(false, safeAccount.withdraw(-999));
		assertEquals(false, safeAccount.withdraw(4000));

	}

}

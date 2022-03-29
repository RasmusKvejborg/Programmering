public class BankAccount {

    private double balance;

    /**
     * BankAccount
     */

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public void deposit(double amount) {
        balance += amount;
    }
    public void withdraw(double amount) {
        balance -= amount;
    }
    public double getBalance() {
        return balance;
    }
}

/**
 * hej med dig
 */
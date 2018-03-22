public class BankAccount {


    private int balance;
    private boolean isOpen;
    // open() open account

    public void open() {
        isOpen = true;
    }

    // close() close account

    public void close() {
        isOpen = false;
    }

    private boolean isPositive(int amount) {
        return amount > 0;
    }

    public BankAccount() {
        balance = 0;
        isOpen = false;
    }
    // deposit()  Cannot deposit or withdraw negative amount

    public synchronized void deposit(int amount) throws BankAccountActionInvalidException {

        if (this.isOpen) { // opened account
            if (isPositive(amount)) { //positive input
                balance = balance + amount;
            } else { // negative input
                throw new BankAccountActionInvalidException("Cannot deposit or withdraw negative amount");
            }
        } else { // not open
            throw new BankAccountActionInvalidException("Account closed");
        }
    }

    // withdraw()
    // Cannot deposit or withdraw negative amount AND Cannot withdraw more money than is currently in the account
    // AND Cannot withdraw money from an empty account

    public synchronized void withdraw(int amount) throws BankAccountActionInvalidException {

        if (balance == 0) {
            throw new BankAccountActionInvalidException("Cannot withdraw money from an empty account");
        } else if (isPositive(amount)) {
            if (this.isOpen) {
                if (balance >= amount) {
                    balance = balance - amount;
                } else {
                    throw new BankAccountActionInvalidException("Cannot withdraw more money than is currently in the account");
                }
            } else {
                throw new BankAccountActionInvalidException("Account closed");
            }
        } else {
            throw new BankAccountActionInvalidException("Cannot deposit or withdraw negative amount");
        }
    }

    //getBalance()
    public synchronized int getBalance() throws BankAccountActionInvalidException {

        if (this.isOpen) {
            return balance;
        } else {
            throw new BankAccountActionInvalidException("Account closed");
        }
    }
}

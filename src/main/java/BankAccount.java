public class BankAccount {


    private int balance;
    private boolean isopen;
    // open() open account

    public void open() {
        isopen = true;
    }

    // close() close account

    public void close() {
        isopen = false;
    }

    private boolean ispositive(int amount) {
        return amount > 0;
    }

    // deposit()  Cannot deposit or withdraw negative amount

    public BankAccount() {
        balance = 0;
        isopen = false;
    }

    public synchronized void deposit(int amount) throws BankAccountActionInvalidException {

        if (this.isopen){ // opened account
            if(ispositive(amount)){ //positive input
                balance = balance+amount;
            }else { // negative input
                throw new BankAccountActionInvalidException("Cannot deposit or withdraw negative amount");
            }
        }else { // not open
            throw new BankAccountActionInvalidException("Account closed");
        }
    }

    // withdraw()  Cannot deposit or withdraw negative amount AND Cannot withdraw more money than is currently in the account
    // AND Cannot withdraw money from an empty account

    public synchronized void withdraw(int amount) throws BankAccountActionInvalidException {

        if (balance == 0) {
            throw new BankAccountActionInvalidException("Cannot withdraw money from an empty account");
        } else if (ispositive(amount)) {
            if (this.isopen) {
                if (balance >= amount) {
                    balance = balance - amount;
                } else {
                    throw new BankAccountActionInvalidException("Cannot withdraw more money than is currently in the account");
                }
            } else {
                throw new BankAccountActionInvalidException("Account closed");
            }
        }else {
            throw new BankAccountActionInvalidException("Cannot deposit or withdraw negative amount");
        }
    }
    //getBalance()

    public synchronized int getBalance() throws BankAccountActionInvalidException {

        if (this.isopen){
            return balance;
        }else {
            throw new BankAccountActionInvalidException("Account closed");
        }
    }
}

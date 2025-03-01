import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BankAccount {

    private String accountID;
    private double balance;

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    ReentrantReadWriteLock.ReadLock readlock = lock.readLock();
    ReentrantReadWriteLock.WriteLock writelock = lock.writeLock();

    public BankAccount(String accountID, double balance) {
        super();
        this.accountID = accountID;
        this.balance = balance;
    }

    public String getAccountID() {
        return accountID;
    }

    // read operation - we will acquire only a read lock
    // advantage since it is read operation multiple threads can read at the same time without blocking each other
    public double getBalance() {
        try {
            readlock.lock();
            return balance;
        } finally {
            readlock.unlock();
        }

    }

    // write operation - we will be acquiring a write lock
    public void deposit(double amount) {
        try {
            writelock.lock();
            if (amount > 0) {
                this.balance += amount;
                System.out.println(Thread.currentThread().getName()+" "
                        + "deposited LKR 25000 and balance after deposit is "+this.getBalance());
            } else {
                throw new IllegalArgumentException("the amount cannot be negative!");
            }
        }finally {
            // irrespective of a Exception is raised or not the finally block is guaranteed to execute
            // that is the reason to release the lock inside the finally block
            writelock.unlock();

        }
    }

    // write operation - we will be acquiring a write lock
    public void withdraw(double amount) {
        try {
            writelock.lock();
            if (amount > 0) {
                if(this.getBalance() - amount >= 0 ) {
                    this.balance -= amount;
                    System.out.println(Thread.currentThread().getName()+" "
                            + "withdraw LKR 25000 and balance after withdraw is "+this.getBalance());
                } else {
                    throw new IllegalArgumentException("Insuffient balance!");
                }
            } else {
                throw new IllegalArgumentException("the amount cannot be negative!");
            }
        } finally {
            writelock.unlock();
        }
    }

}

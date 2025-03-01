public class HouseBasedHusband implements Runnable{

    private BankAccount account;

    public HouseBasedHusband(BankAccount account) {
        super();
        this.account = account;
    }

    @Override
    public void run() {

        for(int i = 0; i < 10; i++) {
            account.withdraw(25000);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println(Thread.currentThread().getName()+" "
            //		+ "withdraw LKR 25000 and balance after withdraw is "+account.getBalance());
        }

    }
    // once the body of the run() method complete the execution the Thread goes from RUNNABLE state to TERMINATED state


}

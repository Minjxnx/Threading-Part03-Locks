public class CareerMindedWife implements Runnable{

    private BankAccount account;

    public CareerMindedWife(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            account.deposit(25000);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println(Thread.currentThread().getName()+" "
            //		+ "deposited LKR 25000 and balance after deposit is "+account.getBalance());
        }

    }

}

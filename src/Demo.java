public class Demo {

    public static void main(String[] args) {
        BankAccount obj = new BankAccount("Acc001", 25000);

        Runnable cmw = new CareerMindedWife(obj);
        Runnable hbh = new HouseBasedHusband(obj);

        Thread cmwThread = new Thread(cmw, "Career Minded Wife"); // When you instantiate a Thread object it enters the NEW state
        Thread hbhThread = new Thread(hbh, "House Based Husband");

        Thread[] readerThreads = new Thread[10];

        for (int i = 0; i < readerThreads.length; i++) {

            readerThreads[i] = new Thread(() -> {

                System.out.println(Thread.currentThread().getName()+" reads the balance "+obj.getBalance());

            }, "Reader Thread - "+i);
        }

        cmwThread.start(); // when the start() method is called the Thread object goes from NEW state to RUNNABLE state
        hbhThread.start();
        //hbhThread.start(); // start method can only be called on the thread which is in the NEW state
        // any attempt to call start() method on  the thread on any other state will result in IllegalThreadStateException

        for (Thread t : readerThreads) {
            t.start();
        }
    }

}

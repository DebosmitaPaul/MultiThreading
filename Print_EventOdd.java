public class Print_EventOdd {
    public static void main(String[] args) {
        int n =10;
        Object lock = new Object();
        PrintOddEven evenThread = new PrintOddEven(n,lock);
        PrintOddEven oddThread = new PrintOddEven(n,lock);

        evenThread.setName("Even");
        oddThread.setName("Odd");

        evenThread.start();
        oddThread.start();

        try {
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Done..");
    }
}
class PrintOddEven extends Thread
{
    static int n ;
    Object lock;
    PrintOddEven(int i, Object obj)
    {
        n =i;
        lock = obj;
    }
    void  PrintEven()
    {
        System.out.println(Thread.currentThread().getName() + "...." + n);
        n = n - 1;
        lock.notify();
        if(n <= 0 ){
            return;
        }
        try {
            lock.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    void PrintOdd()
    {
        System.out.println(Thread.currentThread().getName() + " ..... " + n);
        n = n - 1;
        lock.notify();
        if(n <= 0 ){
            return;
        }
        try {
            lock.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (n>0) {
            synchronized (lock) {
                if ( n % 2 == 0 ) {
                    this.PrintEven();
                }
                if ( n % 2 != 0 ) {
                    this.PrintOdd();
                }
            }
//            System.out.println(Thread.currentThread().getName() + "..rahul.." + n[0]);
        }
    }
}
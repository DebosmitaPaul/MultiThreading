// Using 2 separate Thread

public class OddEven {
    public static int n =10;
    public static Object lock = new Object();

    public static void main(String[] args) {
        System.out.println("Main thread..");
        Even t1 = new Even();
        t1.setName("Even");
        t1.start();

        Odd t2 = new Odd();
        t2.setName("Odd");
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Done..");
    }
}
class Even extends  Thread
{
    @Override
    public void run() {
        while (OddEven.n>0){
            synchronized (OddEven.lock){
             if(OddEven.n%2 ==0)
                {
                    System.out.println(Thread.currentThread().getName()+"..."+OddEven.n);
                    OddEven.n--;
                    OddEven.lock.notify();
                    if(OddEven.n<=0){
                        return;
                    }

                    try {
                        OddEven.lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                   // lock.notify();
                }
            }
        }
    }
}
class Odd extends  Thread
{
    @Override
    public void run() {
        while (OddEven.n>0){
            synchronized (OddEven.lock){
                if(OddEven.n%2 !=0)
                {
                    System.out.println(Thread.currentThread().getName()+"..."+OddEven.n);
                    OddEven.n--;
                    OddEven.lock.notify();

                    if(OddEven.n<=0) {
                        return;
                    }

                    try {
                        OddEven.lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //lock.notify();

                }
            }
        }
    }
}
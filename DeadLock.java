import com.sun.tools.javac.Main;

public class DeadLock {
    public static void main(String[] args) {
        Object l1= new Object();
        Object l2= new Object();
        Thread t1 = new DeadLoackClass1(l1,l2);
        Thread t2 = new DeadLoackClass2(l1,l2);
        t1.start();
        t2.start();
        System.out.println("Started");
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName());
    }
}

class DeadLoackClass1 extends Thread
{
    Object l1;Object l2;
    DeadLoackClass1(Object l1,Object l2)
    {
        this.l1 = l1;
        this.l2 = l2;
    }
    @Override
    public void run(){
        synchronized (l1){
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (l2){
                    System.out.println(Thread.currentThread().getName());
            }
        }
    }
}
class DeadLoackClass2 extends Thread
{
    Object l1;Object l2;
    DeadLoackClass2(Object l1,Object l2)
    {
        this.l1 = l1;
        this.l2 = l2;
    }
    @Override
    public void run(){
        synchronized (l2){
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (l1){
                    System.out.println(Thread.currentThread().getName());
            }
        }
    }
}

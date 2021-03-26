import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest {
    private volatile static int a =1;
    //AtomicInteger k = new AtomicInteger(2);

    public static void main(String[] args) {
        VolatileTest test = new VolatileTest();
        MyClass1 t1 = new MyClass1(test.a);
        t1.start();
        System.out.println(Thread.currentThread().getName() + test.a);

        Thread t2 = new Thread(()->test.a= test.a+1);
        t2.start();
        System.out.println(Thread.currentThread().getName() + test.a);

        Thread t3 = new Thread(()->test.a= test.a+1);
        t3.start();
        System.out.println(Thread.currentThread().getName() + test.a);

        Thread t4 = new Thread(()->test.a= test.a+1);
        t4.start();
        System.out.println(Thread.currentThread().getName() + test.a);

        System.out.printf(String.valueOf(test.a));
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
class MyClass1 extends Thread
{
    int n;
    MyClass1(int x)
    {
       n = x;
    }
    @Override
    public void run() {
        n++;
        System.out.println("dobo.."+n);
    }
}
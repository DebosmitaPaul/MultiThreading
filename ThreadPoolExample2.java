import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Only 10 threads are running in a pool
public class ThreadPoolExample2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=1; i<=20; i++){
            MyThread th = new MyThread("myThread"+i);
            executorService.execute(th);
        }
        executorService.shutdown();
    }
}
class MyThread extends Thread{
    String name;
    MyThread(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("running " + name);
        while (true) {
        }
    }
}

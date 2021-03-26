import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo_ThreadPool
{
    public static void main(String[] args)
    {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i =0; i<10;i++)
        {
            WorkerThread wt = new WorkerThread("Debo.."+i);
            executor.execute(wt);
        }
        executor.shutdown();
        System.out.println("Finished..");
    }
}
class WorkerThread  implements  Runnable
{
    private  String str;
    public WorkerThread(String s)
    {
        str = s;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" Start Msg "+str);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" stop  "+str);
        System.out.println("-----------------------------");
    }
}

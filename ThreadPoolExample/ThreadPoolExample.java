
public class ThreadPoolExample {
    public static void main(String[] args) {
        CustomThreadPool threadPool = new CustomThreadPool(3);
        for (int i =0; i<6; i++)
        {
            Thread th = new Thread("Task "+i);
            System.out.println("Created "+ th.getName());
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadPool.execute(th);
        }
        System.out.println("End");
        threadPool.shutdown();
    }
}


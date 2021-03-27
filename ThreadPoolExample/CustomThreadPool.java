import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPool {
    private final int poolSize;
    private final CustomWorkerThread[] workers;
    private final LinkedBlockingQueue<Runnable> queue;

    CustomThreadPool(int x) {
        poolSize = x;
        queue = new LinkedBlockingQueue<Runnable>();
        workers = new CustomWorkerThread[poolSize];

        for (int i = 0; i < poolSize; i++) {
            workers[i] = new CustomWorkerThread(i);
            workers[i].start();
        }
    }

    public void execute(Runnable task) {
        synchronized (queue) {
            queue.add(task);
            queue.notify();
        }
    }

    class CustomWorkerThread extends Thread {
        int i;
        CustomWorkerThread(int i)
        {
            this.i = i;
        }
        @Override
        public void run() {
            Runnable task;

            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            System.out.println("An error occurred while queue is waiting: " + e.getMessage());
                        }
                    }
                    System.out.println("Execution " +workers[i].getName());
                    task = (Runnable) queue.poll();
                    //System.out.println("Execution " + currentThread().getName());
                }

                try {
                    task.run();
                } catch (RuntimeException e) {
                    System.out.println("Thread pool is interrupted due to an issue: " + e.getMessage());
                }
            }
        }
    }

        public void shutdown() {
            System.out.println("Shutting down thread pool");
            for (int i = 0; i < poolSize; i++) {
                System.out.println("Shutting down " + workers[i].getName() );
                workers[i] = null;
            }
        }

}

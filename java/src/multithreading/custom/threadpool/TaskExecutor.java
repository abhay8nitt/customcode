package multithreading.custom.threadpool;

import java.util.concurrent.BlockingQueue;

class TaskExecutor implements Runnable{

    private final BlockingQueue<Runnable> queue;

    public TaskExecutor(BlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            try {
                String thread = Thread.currentThread().getName();
                System.out.println(thread + " started execution");
                Runnable runnable = queue.take();
                runnable.run();
                System.out.println(thread + " finished execution");
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

package multithreading.custom.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class ThreadPool {
    BlockingQueue<Runnable> queue;
    public ThreadPool(int queueSize, int nThread) {
        queue = new LinkedBlockingQueue<>(queueSize);
        TaskExecutor taskExecutor;
        for(int i =0;i<nThread;i++){
            taskExecutor = new TaskExecutor(queue);
            Thread thread = new Thread(taskExecutor, "Thread-"+i);
            thread.start();
        }
    }

    public void submit(Runnable runnable){
        queue.add(runnable);
    }
}

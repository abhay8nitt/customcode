package multithreading.examples.executors;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorImpl {
    public static void main(String[] args) throws InterruptedException {
        singleThreadExecutor();
    }

    private static void singleThreadExecutor() throws InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Runnable task = () -> System.out.println("Single thread executed Task at: " + Date.from(Instant.now()));
        service.submit(task);
        Thread.sleep(10);
        service.shutdown();
    }
}

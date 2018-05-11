package multithreading.examples.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceImpl {
    public static void main(String[] args) throws InterruptedException {
        schedule();
        scheduleAtFixedRate();
        scheduleWithFixedDelay();
    }

    /**
     * considers the duration of the task as well.
     * For e.g. if the fixed delay interval is 1 sec but the task it self takes two seconds then the
     * next schedule will happen after 3 secs i.e. 0sec,3sec,6sec
     *
    */
    private static void scheduleWithFixedDelay() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Scheduling with Fixed delay: " + System.nanoTime());
            }
            catch (InterruptedException e) {
                System.err.println("task interrupted");
            }
        };

        executor.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * This method doesn't take care of the execution time
     * For e.g. in the below code it is executed every two seconds irrespective of the time taken by the task
     */
    private static void scheduleAtFixedRate() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> System.out.println("Scheduling at fixed rate: " + System.nanoTime());

        int initialDelay = 0;
        int period = 2;
        executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
    }

    private static void schedule() throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
        ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);

        TimeUnit.MILLISECONDS.sleep(1800);

        long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
        System.out.printf("Remaining Delay: %sms\n", remainingDelay);
    }
}

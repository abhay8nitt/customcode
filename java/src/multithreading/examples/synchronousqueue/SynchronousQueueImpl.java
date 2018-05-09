package multithreading.examples.synchronousqueue;

import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue with athletes (threads) running with Olympic torch, they run with torch
 * (object need to be passed) and passes it to other athlete waiting at other end.
 */
public class SynchronousQueueImpl {
    public static void main(String[] args) {
        final SynchronousQueue<Integer> queue = new SynchronousQueue();

        Thread t1 = new Thread(() -> {
            try {
                queue.put(null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " produced 1");
        }, "Producer");

        Thread t2 = new Thread(() -> {
            try {
                System.out.println(queue.size());
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + " consumed "+ queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Consumer");

        t1.start();
        t2.start();
        System.out.println(queue.size());
    }
}

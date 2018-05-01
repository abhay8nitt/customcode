package multithreading.examples.printoddeven;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintOddEvenImpl {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Lock lock = new ReentrantLock();
        AtomicInteger number = new AtomicInteger();
        executor.submit(new PrintOddEven(number, 15, true, lock));
        executor.submit(new PrintOddEven(number, 15, false, lock));
        executor.shutdown();
    }
}

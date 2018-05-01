package multithreading.custom.producerconsumer;

import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class ProducerConsumerV2 {
    private final java.util.concurrent.locks.Lock lock = new ReentrantLock();
    private final Condition produce = lock.newCondition();
    private final Condition consume = lock.newCondition();
    private final int MAX_ITEMS = 10;
    private final Stack<Integer> buffer = new Stack<>();

    public void produce() throws InterruptedException {
        int val = 0;
        while(true) {
            lock.lock();
            while (buffer.size() == MAX_ITEMS) {
                System.out.println("No more data on the buffer to consume");
                produce.await();
            }
            System.out.println(Thread.currentThread().getName() + " produced " + val);
            buffer.push(val++);
            Thread.sleep(10);
            consume.signalAll();
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {
        while(true) {
            lock.lock();
            while (buffer.size() == 0) {
                consume.await();
            }
            System.out.println(Thread.currentThread().getName() + " consumed " + buffer.pop());
            Thread.sleep(10);
            produce.signalAll();
            lock.unlock();
        }
    }
}

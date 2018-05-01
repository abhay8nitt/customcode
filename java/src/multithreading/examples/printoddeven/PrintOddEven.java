package multithreading.examples.printoddeven;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

class PrintOddEven implements Runnable{

    private final boolean even;
    private final Lock lock;
    private AtomicInteger number;
    private final int max;

    public PrintOddEven(AtomicInteger number, Integer max, boolean even, Lock lock) {
        this.even = even;
        this.number = number;
        this.lock = lock;
        this.max = max;
    }

    @Override
    public void run() {
        while (number.get() < max){
            lock.lock();
            if(even){
                if(number.get()%2 ==0) {
                    System.out.println("Even thread:" + number.getAndIncrement());
                }
            }else{
                if(number.get()%2!=0) {
                    System.out.println("Odd thread :" + number.getAndIncrement());
                }
            }
            lock.unlock();
        }
    }
}

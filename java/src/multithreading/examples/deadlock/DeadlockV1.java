package multithreading.examples.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class DeadlockV1  implements Runnable{

    private final Lock lock2;
    private final Lock lock1;

    public DeadlockV1(Lock lock1, Lock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    public static void main(String[] args) throws InterruptedException {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(new DeadlockV1(lock1, lock2));
        service.submit(new DeadlockV1(lock2, lock1));
        Thread.sleep(2000);
        detectDeadlockThreads();
    }

    @Override
    public void run() {
        try {
            manifestDeadlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void manifestDeadlock() throws InterruptedException {
        lock1.lock();
        System.out.println(Thread.currentThread().getName() +" acquired lock1");
        lock2.lock();
        System.out.println(Thread.currentThread().getName() +" acquired lock2");
        Thread.sleep(100);
        lock2.unlock();
        System.out.println(Thread.currentThread().getName() +" releasing lock2");
        lock1.unlock();
        System.out.println(Thread.currentThread().getName() +" releasing lock1");
    }

    public static void detectDeadlockThreads(){
        ThreadMXBean tmx = ManagementFactory.getThreadMXBean();
        long[] ids = tmx.findDeadlockedThreads();
        if (ids != null) {
            ThreadInfo[] infos = tmx.getThreadInfo(ids, true, true);
            System.out.println("The following threads are deadlocked:");
            for (ThreadInfo ti : infos) {
                System.out.println(ti);
            }
        }
    }
}

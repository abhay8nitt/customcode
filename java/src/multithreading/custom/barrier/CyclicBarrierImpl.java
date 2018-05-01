package multithreading.custom.barrier;

import java.util.concurrent.BrokenBarrierException;

public class CyclicBarrierImpl {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            System.out.println("All parties reached hence executing the barrier action");
        });
        Thread t1  =new MyThread(barrier);
        Thread t2  =new MyThread(barrier);
        t1.start();
        t2.start();
    }

    static class MyThread extends Thread{
        private final CyclicBarrier barrier;

        public MyThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " barrier 1");
                barrier.await();
                System.out.println(Thread.currentThread().getName() + " crossed barrier first time");
                System.out.println(Thread.currentThread().getName() + " barrier 2");
                barrier.await();
                System.out.println(Thread.currentThread().getName() + " crossed barrier second time ");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " released");
        }
    }
}

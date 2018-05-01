package multithreading.custom.latch;

public class CountDownLatchImpl {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        new Worker(latch, "Second").start();
        new Worker(latch, "First").start();
        new Worker(latch, "Third").start();
        latch.await();
        System.out.println("Ending");
    }

    static class Worker extends Thread{
        private final CountDownLatch latch;
        public Worker(CountDownLatch latch, String name) {
            this.latch = latch;
            super.setName(name);
        }
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName());
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

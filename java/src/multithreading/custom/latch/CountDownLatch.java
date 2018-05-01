package multithreading.custom.latch;

/**
 * Custom implementation of java.util.concurrent.CountDownLatch
 */
public class CountDownLatch {
    private int parties;

    public CountDownLatch(int parties) {
        this.parties = parties;
    }

    public synchronized void countDown(){
        if(parties > 0){
            parties--;
            if(parties == 0) {
                notifyAll();
            }
        }
    }

    public synchronized void await() throws InterruptedException {
        if(parties > 0){
            wait();
        }
    }

    public synchronized void await(long timeout) throws InterruptedException {
        if(parties > 0){
            wait();
        }
    }
}

package multithreading.custom.semaphore;

/**
 * Custom implementation of java.util.concurrent.Semaphore
 */
public class Semaphore {
    private int permits;

    public Semaphore(int permits) {
        this.permits = permits;
    }

    public synchronized void acquire() throws InterruptedException {
        if(permits > 0){
            permits--;
        }else{
            wait();
            permits--;
        }
    }

    public synchronized void release(){
        permits++;
        if(permits > 0){
            notify();
        }
    }
}

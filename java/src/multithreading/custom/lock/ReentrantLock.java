package multithreading.custom.lock;

import multithreading.custom.readwritelock.Lock;

public class ReentrantLock implements Lock{
    private boolean isLocked;
    private Thread acquiredBy;

    public synchronized void lock() throws InterruptedException {
        while (isLocked && acquiredBy!= Thread.currentThread()){
            wait();
        }
        isLocked = true;
        acquiredBy = Thread.currentThread();
    }

    public synchronized void unLock() throws InterruptedException{
        if(isLocked && acquiredBy == Thread.currentThread()){
            isLocked = false;
            acquiredBy = null;
            notify();
        }
    }
}

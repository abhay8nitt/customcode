package multithreading.custom.readwritelock;

public interface Lock {
    void lock() throws InterruptedException;
    void unLock() throws InterruptedException;
}

package multithreading.custom.readwritelock;

public interface ReadWriteLock {
    ReentrantReadWriteLock.ReadLock readLock();
    ReentrantReadWriteLock.WriteLock writeLock();
}
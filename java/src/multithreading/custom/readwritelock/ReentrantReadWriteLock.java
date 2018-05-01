package multithreading.custom.readwritelock;

public class ReentrantReadWriteLock implements ReadWriteLock{
    private int readLockCount;
    private int writeLockCount;
    private final ReadLock readLock;
    private final WriteLock writeLock;


    public ReentrantReadWriteLock() {
        readLock = new ReadLock();
        writeLock = new WriteLock();
        readLockCount = 0;
        writeLockCount = 0;
    }

    @Override
    public ReadLock readLock() {
        return readLock;
    }

    @Override
    public WriteLock writeLock() {
        return writeLock;
    }


    class ReadLock implements Lock {
        @Override
        public synchronized void lock() {
            if(writeLockCount == 0){
                readLockCount++;
            }else{
                try{
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        @Override
        public synchronized void unLock() {
            readLockCount--;
            if(readLockCount > 0){
                notifyAll();
            }
        }
    }

    class WriteLock implements Lock{
        @Override
        public synchronized void lock() throws InterruptedException{
            if(readLockCount == 0 && writeLockCount ==0){
                writeLockCount++;
            }else{
                    wait();
            }
        }

        @Override
        public synchronized void unLock() throws InterruptedException{
            writeLockCount--;
            notifyAll();
        }
    }
}

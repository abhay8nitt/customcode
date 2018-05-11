package multithreading.custom.exchanger;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * It has the property of naturally recycling the data structures used to pass the work and supports
 * GC-less sharing of work in an efficient manner
 * e.g. passing logs to a background logger
 * there is only one producer/consumer in this case
 */
class Exchanger<U> {
    private volatile U ONE;
    private volatile U TWO;

    private volatile boolean THREAD_ONE_WAS_SET;
    private volatile boolean THREAD_TWO_WAS_SET;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public Exchanger() {
        ONE = TWO = null;
        THREAD_ONE_WAS_SET = THREAD_TWO_WAS_SET = false;
    }

    /**
     * Whichever thread initiates first is set to ONE and the boolean is set accordingly, when the second object is set the
     * first thread wakes up with a signal and then returns data accordingly to the other thread
     *
     * @param obj
     * @return
     * @throws InterruptedException
     */
    public U exchange(U obj) throws InterruptedException{
        boolean setONE = true;

        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName() +" in exchange mode");
            if(!THREAD_ONE_WAS_SET){
                System.out.println(Thread.currentThread().getName() +" setting object one");
                ONE = obj;
                THREAD_ONE_WAS_SET = true;
                System.out.println(Thread.currentThread().getName() +" waiting for thread two to set");
                do {
                    condition.await();
                }while (!THREAD_TWO_WAS_SET);
            }else{
                System.out.println(Thread.currentThread().getName() +" setting second object");
                setONE = false;
                TWO = obj;
                THREAD_TWO_WAS_SET = true;
                System.out.println(Thread.currentThread().getName() +" signalled thread one");
                condition.signal();
            }
            System.out.println(Thread.currentThread().getName() +" ready to return value "+ (setONE ? TWO : ONE));
            return setONE ? TWO : ONE;
        }finally {
            if(!setONE){
                THREAD_ONE_WAS_SET = false;
                ONE = null;
            }else{
                THREAD_TWO_WAS_SET = false;
                TWO = null;
            }
            lock.unlock();
        }
    }
}

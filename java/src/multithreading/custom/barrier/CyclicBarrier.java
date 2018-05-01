package multithreading.custom.barrier;

import java.util.concurrent.BrokenBarrierException;

class CyclicBarrier {
    private final int parties;
    private final Runnable barrierAction;
    private int waitingParties;
    public CyclicBarrier(int parties) {
        this(parties, null);
    }

    public CyclicBarrier(int parties, Runnable barrierAction) {
        this.parties = parties;
        this.barrierAction = barrierAction;
        this.waitingParties = parties;
    }

    public synchronized void await() throws InterruptedException, BrokenBarrierException {
        if(Thread.interrupted()){
            breakBarrier();
            throw new InterruptedException();
        }
        waitingParties--;
        if(waitingParties > 0) {
            this.wait();
        }
        if(waitingParties == 0){
            if(barrierAction !=null){
                resetBarrier();
                this.notifyAll();
                barrierAction.run();
            }
        }else{
            System.out.println(Thread.currentThread().getName()+ " waiting at the barrier");
        }
    }

    public synchronized void resetBarrier() throws BrokenBarrierException {
        waitingParties = 0;
        breakBarrier();
    }

    public int getParties() {
        return parties;
    }

    public synchronized int getNumberWaiting(){
        return parties - waitingParties;
    }

    private synchronized void breakBarrier() throws BrokenBarrierException{
        if(waitingParties != 0)
            throw new BrokenBarrierException();
        waitingParties = parties;
    }
}

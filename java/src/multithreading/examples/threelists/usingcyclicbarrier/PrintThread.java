package multithreading.examples.threelists.usingcyclicbarrier;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;

class PrintThread implements Runnable{
    private final int threadId;
    private final List<Integer> list;
    private final BarrierPrinter barrier;

    public PrintThread(List<Integer> list, int id, BarrierPrinter barrier) {
        this.list = list;
        this.threadId = id;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < list.size()) {
            barrier.add(new Entry(list.get(i++), threadId));
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {

            }
        }
    }
}

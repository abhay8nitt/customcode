package multithreading.examples.threelists.usingcyclicbarrier;

import java.util.List;
import java.util.concurrent.CyclicBarrier;

class BarrierPrinter<T> extends CyclicBarrier {
    private final List<Entry> list;

    public BarrierPrinter(int parties, List<Entry> list) {
        super(parties, new MyRunnable(list));
        this.list = list;
    }
    public synchronized void add(Entry entry){
        list.add(entry);
    }
}

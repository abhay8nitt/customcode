package multithreading.examples.threelists.usingcyclicbarrier;

import java.util.Collections;
import java.util.List;

class MyRunnable implements Runnable{
    private final List<Entry> list;

    public MyRunnable(List<Entry> list) {
        this.list = list;
    }

    @Override
    public void run() {
        //noinspection unchecked,unchecked
        Collections.sort(list);
        list.forEach(System.out::println);
        list.clear();
    }
}

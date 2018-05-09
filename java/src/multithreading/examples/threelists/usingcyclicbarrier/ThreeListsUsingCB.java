package multithreading.examples.threelists.usingcyclicbarrier;

import java.util.*;
import java.util.concurrent.*;

public class ThreeListsUsingCB {
    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> l2 = new ArrayList<>(Arrays.asList(11, 12, 13, 14, 15));
        List<Integer> l3 = new ArrayList<>(Arrays.asList(21, 22, 23, 24, 25));
        BarrierPrinter barrier = new BarrierPrinter(3,new ArrayList<>());
        List<Future> futures = new ArrayList<>();
        ExecutorService service = Executors.newFixedThreadPool(3);
        futures.add(service.submit(new PrintThread(l1, 1, barrier)));
        futures.add(service.submit(new PrintThread(l2, 2, barrier)));
        futures.add(service.submit(new PrintThread(l3, 3, barrier)));

        futures.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println("All printers finished printing");
        service.shutdown();
    }
}


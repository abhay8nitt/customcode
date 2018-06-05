package multithreading.examples.nonblockingalgos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

public class NonBlockingImpl {
    public static void main(String[] args) {
        ExecutorService executors = Executors.newFixedThreadPool(2);
        List<Integer> l1 = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
        List<Integer> l2 = new ArrayList<Integer>(Arrays.asList(11,12,13,14,15));
        List<Integer> l3 = new ArrayList<Integer>(Arrays.asList(21, 22, 23, 24, 25));
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        //queue.add(l1.get())
    }
}

class Print extends Thread{

}

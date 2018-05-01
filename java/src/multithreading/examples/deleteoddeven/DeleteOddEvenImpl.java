package multithreading.examples.deleteoddeven;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeleteOddEvenImpl {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1,1,1,1,2,3,4,5,6,7,8,9,9,9,9));
        Lock lock = new ReentrantLock();
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(new DeleteOddEven("Odd",  list, false, lock));
        service.submit(new DeleteOddEven("Even", list, true,  lock));
    }
}

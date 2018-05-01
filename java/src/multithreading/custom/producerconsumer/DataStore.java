package multithreading.custom.producerconsumer;

import java.util.LinkedList;
import java.util.Random;

class DataStore {
    public int size;
    public LinkedList<Integer> store;
    Random random = new Random(100);

    public DataStore(int size) {
        this.size = size;
        store = new LinkedList<>();
    }


    public synchronized void put() throws InterruptedException {
        if(store.size() == size){
            wait();
        }
        int number = random.nextInt(100);
        store.offer(number);
        notifyAll();
        System.out.println("Putting value "+ number);
    }

    public synchronized void get() throws InterruptedException {
        if(store.size() == 0){
            wait();
        }

        int val = store.poll();
        System.out.println("Getting value: "+ val);
        notifyAll();
    }
}

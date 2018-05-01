package multithreading.custom.producerconsumer;

public class Consumer implements Runnable {
    private final DataStore store;
    private final int itemCount;

    public Consumer(DataStore store, int itemCount) {
        this.store = store;
        this.itemCount = itemCount;
    }

    @Override
    public void run() {
        for(int i =0;i<itemCount;i++){
            try {
                Thread.sleep(1000);
                store.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package multithreading.custom.producerconsumer;

public class Producer implements Runnable {

    private final DataStore store;
    private final int itemCount;

    public Producer(DataStore store, int itemCount) {
        this.store = store;
        this.itemCount = itemCount;
    }

    @Override
    public void run() {
        for (int i = 0; i < itemCount; i++) {
            try {
                store.put();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

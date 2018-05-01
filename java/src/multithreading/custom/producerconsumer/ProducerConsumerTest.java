package multithreading.custom.producerconsumer;

public class ProducerConsumerTest {
    public static void main(String[] args) throws InterruptedException {
        DataStore store = new DataStore(20);
        Thread producer = new Thread(new Producer(store, 5),"Producer");
        Thread consumer = new Thread(new Consumer(store, 5), "Consumer");
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}


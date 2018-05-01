package multithreading.custom.producerconsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumerTest {
    public static void main(String[] args) throws InterruptedException {
        DataStore store = new DataStore(20);
        Thread producer = new Thread(new Producer(store, 5),"Producer");
        Thread consumer = new Thread(new Consumer(store, 5), "Consumer");
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();

        ExecutorService execService = Executors.newFixedThreadPool(5);
        ProducerConsumerV2 service = new ProducerConsumerV2();
        execService.submit(()-> {
            try {
                service.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        execService.submit(()-> {
            try {
                service.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        execService.submit(()-> {
            try {
                service.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}


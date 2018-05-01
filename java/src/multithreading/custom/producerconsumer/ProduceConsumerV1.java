package multithreading.custom.producerconsumer;

import java.util.Stack;

class ProduceConsumerV1 {
    private final Stack<Integer> buffer = new Stack<>();
    private final int MAX_ITEMS = 10;

    public void consume() throws InterruptedException{
        while(true){
            synchronized (this){
                while(buffer.size() == 0){
                    wait();
                }
                System.out.println(Thread.currentThread().getName()+ ":consumed"+buffer.pop());
                notify();
                Thread.sleep(1000);
            }
        }
    }

    public void produce() throws InterruptedException {
        int value = 0;
        while(true){
            synchronized (this){
                while(buffer.size() == MAX_ITEMS){
                    System.out.println("No more buffer to produce");
                    wait();
                }
                value++;
                System.out.println(Thread.currentThread().getName()+ ":produced:"+value);
                buffer.push(value);
                notify();
                Thread.sleep(100);
            }
        }

    }
}
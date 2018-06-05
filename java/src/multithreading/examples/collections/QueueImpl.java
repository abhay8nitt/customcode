package multithreading.examples.collections;

public class QueueImpl {
    public static void main(String[] args) {
        Queue queue = new Queue(10);

        try {
            System.out.println(queue.dequeue());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            queue.enqueue(-1);
            queue.enqueue(2);
            queue.enqueue(3);
            queue.enqueue(3);
            System.out.println(queue.dequeue());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
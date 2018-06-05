package multithreading.examples.collections;

class Queue {
    private final int size;
    private int elements[];
    private int front, rear;

    public Queue(int size) {
        this.elements = new int[size];
        this.front = -1;
        this.rear = -1;
        this.size = size;
    }


    public void enqueue(int data) throws Exception{
        if(rear == size - 1){
            throw new Exception("Overflow encountered");
        }else{

            if(front == -1 && rear == -1){
                rear++;
                front = rear;
                elements[rear] = data;
            }else{
                elements[++rear] = data;
            }
        }
    }

    public int dequeue() throws Exception {
        if(front == size - 1 || front == -1) {
            throw new Exception("Underflow encountered");
        }else{
            return elements[front++];
        }
    }
}





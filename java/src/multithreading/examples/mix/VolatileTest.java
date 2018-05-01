package multithreading.examples.mix;

public class VolatileTest {
    volatile static boolean cancel = false;
    public static void main(String[] args) throws InterruptedException {
        new A("Boss A").start();
        new A("Boss B").start();
        Thread.sleep(5);
        cancel = true;
    }

    static class A extends Thread{
        public A(String name) {
            super(name);
        }
        @Override
        public void run() {
            while(!cancel){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
            System.out.println("Volatile alert");
        }
    }
}

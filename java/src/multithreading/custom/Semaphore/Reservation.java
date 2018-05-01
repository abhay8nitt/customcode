package multithreading.custom.semaphore;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Reservation{
    private Semaphore semaphore;
    private Random random = new Random();

    public Reservation() {
        semaphore = new Semaphore(1);
    }

    public void book(Object object){
        try {
            semaphore.acquire();
            int time = random.nextInt(100);
            System.out.printf("Time taken to book tickets for %s : %d\n",Thread.currentThread().getName(), time);
            sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }


}

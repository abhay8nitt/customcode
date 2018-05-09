package multithreading.examples.phaser;

import java.util.concurrent.Phaser;

public class PhaserImpl {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);
        Thread thread1 = new Thread(new PhaserRunnable(phaser,"First"),"Thread-1");
        Thread thread2 = new Thread(new PhaserRunnable(phaser,"Second"),"Thread-2");
        Thread thread3 = new Thread(new PhaserRunnable(phaser,"Third"),"Thread-3");
        Thread thread4 = new Thread(new PhaserRunnable(phaser,"Fourth"),"Thread-4");

        System.out.println("Phaser has started ");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        int currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase "+ currentPhase + " done");

        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase "+ currentPhase + " done");

        phaser.arriveAndDeregister();
        if(phaser.isTerminated()) {
            System.out.println("Phaser terminated");
        }
    }
}



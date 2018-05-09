package multithreading.examples.phaser;

import java.util.concurrent.Phaser;

/**
 * Phaser is a hybrid of countdownlatch and cyclicbarrier.
 * Phaser can be repeatedly awaited i.e. flexible cyclicbarrier
 */
class PhaserRunnable implements Runnable {

    private final Phaser phaser;
    private final String name;

    PhaserRunnable(Phaser phaser, String name){
        this.phaser = phaser;
        this.phaser.register();
        this.name = name;
        System.out.println(name + " has been registered with the phaser");
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ " has arrived and is working in phase "+ phaser.getPhase());
        phaser.arriveAndAwaitAdvance();
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(name.equals("Second")){
            phaser.arriveAndDeregister();
            System.out.println("Deregistering Second Runnable");
        }else {
            System.out.println(Thread.currentThread().getName() + " has arrived and is working in phase " + phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndDeregister();
        }
    }
}

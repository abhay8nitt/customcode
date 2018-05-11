package multithreading.custom.exchanger;


class ExchangerRunnable<T> implements Runnable {

    Exchanger<T> exchanger = null;
    T object = null;

    public ExchangerRunnable(Exchanger exchanger, T object) {
        this.exchanger = exchanger;
        this.object = object;
    }

    public void run() {
        try {
            Object previous = this.object;
            this.object = this.exchanger.exchange(this.object);
            System.out.println(Thread.currentThread().getName() + " exchanged " + previous + " for " + this.object);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
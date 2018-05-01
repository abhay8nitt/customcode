package multithreading.custom.threadpool;

class MockTask implements Runnable{
    int number;
    public MockTask(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println("Start executing task number "+number);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End executing task number "+number);
    }
}

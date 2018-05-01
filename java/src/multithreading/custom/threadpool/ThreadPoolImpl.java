package multithreading.custom.threadpool;

public class ThreadPoolImpl {
    /**
     * Task Producer --> Submit Task --> BlockingQueue --> T_1 T_2 .... T_n (get tasks from BlockingQueue and execute)
     */
    private static int MAX_TASKS =10;
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(MAX_TASKS,4);
        for(int i = 0;i < MAX_TASKS;i++){
            pool.submit(new MockTask(i));
        }
    }
}

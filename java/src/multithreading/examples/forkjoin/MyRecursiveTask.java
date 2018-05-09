package multithreading.examples.forkjoin;

import java.util.concurrent.RecursiveTask;

public class MyRecursiveTask extends RecursiveTask<Long> {
    private int start, end;

    long[] numberArr;

    public MyRecursiveTask(int start, int end, long[] numberArr) {
        this.start = start;
        this.end = end;
        this.numberArr = numberArr;
    }
    @Override
    protected Long compute() {
        int minimumProcessingSize= 1000;
        long sum=0;
        if(end-start < minimumProcessingSize){
            for (int i = start; i < end; i++) {
                sum += numberArr[i];
            }
        }
        else {
            int mid= (start+end)/2;
            MyRecursiveTask task1 = new MyRecursiveTask(start, mid, numberArr);
            MyRecursiveTask task2 = new MyRecursiveTask(mid, end, numberArr);
            task1.fork();
            task2.fork();
            sum = task1.join() + task2.join();
        }
        return sum;
    }
}

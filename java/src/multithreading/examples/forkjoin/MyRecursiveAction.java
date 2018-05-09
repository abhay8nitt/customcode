package multithreading.examples.forkjoin;

import java.util.concurrent.RecursiveAction;

public class MyRecursiveAction extends RecursiveAction {
    private int start, end;

    long[] numberArr;

    public MyRecursiveAction(int start, int end, long[] numberArr) {
        this.start = start;
        this.end = end;
        this.numberArr = numberArr;
    }

    @Override
    protected void compute() {
        int minimumProcessingSize= 1000;

        if(end-start < minimumProcessingSize){
            for (int i = start; i < end; i++) {
                numberArr[i]=numberArr[i]*numberArr[i];
            }
        }
        else {
            int mid= (start+end)/2;
            invokeAll(new MyRecursiveAction(start, mid, numberArr),
                    new MyRecursiveAction(mid, end, numberArr));
        }
    }
}

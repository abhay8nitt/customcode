package multithreading.examples.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinImpl {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        long startNanoSec;
        long endNanoSec;

        long[] numberArr=new long[100000];
        for(int i=0;i<100000;i++){
            numberArr[i]=i;
        }

        System.out.print("original array : ");
        for(int i=0;i< 20;i++){
            System.out.print(numberArr[i]+" ");
        }

        MyRecursiveAction action=new MyRecursiveAction(0, numberArr.length, numberArr);


        startNanoSec = System.nanoTime();
        forkJoinPool.invoke(action);
        endNanoSec = System.nanoTime();

        System.out.print("\narray after being processed recursively "
                + "using RecursiveAction : ");
        for(int i = 0;i < 20;i++){
            System.out.print(numberArr[i]+" ");
        }

        System.out.println();
        System.out.println("Level of Parallelism :: "+
                forkJoinPool.getParallelism());
        System.out.print("Time taken to complete action : "+
                (endNanoSec - startNanoSec)+" ns");

        long[] numberAr=new long[1000];
        for(int i=0;i<1000;i++){
            numberAr[i]=i;
        }

        MyRecursiveTask task=new MyRecursiveTask(0,numberAr.length,numberAr);

        long sum = forkJoinPool.invoke(task);
        System.out.println("sum of 1000 elements returned by compute() method = "+sum);

    }
}

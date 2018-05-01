package multithreading.examples.threelists;

import java.util.*;

/**
 * Run three threads, each thread prints one element from a list
 * Output for the below example
 * 1
 * 11
 * 21
 * 2
 * 1
 * 12
 * 22
 * .
 * .
 * .
 */
class ThreeLists {
    private int i;

    public ThreeLists(int input) { i = input; }

    public int  getI() {
        return i;
    }

    public void  setI(int value) {
        i = value;
    }

    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
        List<Integer> l2 = new ArrayList<Integer>(Arrays.asList(11,12,13,14,15));
        List<Integer> l3 = new ArrayList<Integer>(Arrays.asList(21, 22, 23, 24, 25));

        ThreeLists t = new ThreeLists(1);

        MasterThread t1 = new MasterThread(l1,t,1);
        MasterThread t2 = new MasterThread(l2,t,2);
        MasterThread t3 = new MasterThread(l3,t,3);

        t1.start();
        t2.start();
        t3.start();

    }
}
class MasterThread extends Thread{
    private List<Integer> l;
    private ThreeLists t;
    private int threadNumber;
    private static Map<Integer, Integer> _typesMap =
            new HashMap<Integer, Integer>() {
                {
                    put(1,2);
                    put(2,3);
                    put(3,1);
                }

            };
    public MasterThread(List<Integer> list, ThreeLists tIN,int threadNumber) {
        l = list;
        t = tIN;
        this.threadNumber = threadNumber;
    }

    public void run() {
        int i = 0;
        while (i < l.size()) {
            synchronized (t) {
                if (t.getI() != threadNumber)
                {
                    try {
                        t.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(threadNumber + ":" + l.get(i++));
                    t.setI(_typesMap.get(threadNumber));
                    t.notifyAll();
                }
            }
        }
    }
}
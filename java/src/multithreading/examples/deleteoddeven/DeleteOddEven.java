package multithreading.examples.deleteoddeven;

import java.util.List;
import java.util.concurrent.locks.Lock;

class DeleteOddEven extends Thread{
    private final boolean oddThread;
    private List<Integer> list;
    private Lock lock ;

    public DeleteOddEven(String name, List<Integer> list, boolean oddThread, Lock lock) {
        super.setName(name);
        this.list = list;
        this.oddThread = oddThread;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (list.size() > 0) {
            lock.lock();
            boolean delete = false;
            if (list.size() > 0) {
                boolean odd = list.get(list.size()-1) % 2 ==0;
                if (oddThread) {
                    if (odd) delete = true;
                } else if (!odd) delete = true;
            }
            if(delete){
                System.out.println(getName() + " thread deleted ::" + list.get(list.size()-1));
                list.remove(list.get(list.size()-1));
            }
            lock.unlock();
        }
    }
}
package lru;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LruCacheImpl {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(20);
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(new CacheImpl(cache, true));
        service.submit(new CacheImpl(cache, false));
        service.shutdown();
    }
}
class CacheImpl implements Runnable{

    private final boolean produce;
    private final LRUCache cache;

    public CacheImpl(LRUCache cache, boolean produce) {
        this.produce = produce;
        this.cache = cache;
    }

    @Override
    public void run() {
        if(produce){
            try {
                System.out.println(Thread.currentThread().getName());
                cache.put(1,2);
                Thread.sleep(10);
                System.out.println(Thread.currentThread().getName());
                cache.put(2,3);
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            cache.put(3,4);
        }else{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":"+ cache.get(2));
            System.out.println(Thread.currentThread().getName() + ":"+ cache.get(3));
        }
    }
}

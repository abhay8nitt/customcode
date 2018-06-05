package ttllru;

import java.util.concurrent.TimeUnit;

class CleanupTask<K,V> implements Runnable{
    private TTLCache<K,V> cache;

    public CleanupTask(TTLCache<K, V> cache) {
        this.cache = cache;
    }


    @Override
    public void run() {
        while(true){
            try {
                TimeUnit.SECONDS.sleep(1);
                cache.cleanup();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

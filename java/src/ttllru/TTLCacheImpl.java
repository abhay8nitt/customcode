package ttllru;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

class TTLCacheImpl<K,V> implements TTLCache<K,V> {

    private final long ttl; // in seconds
    private Map<K, CacheHolder<V>> cache;

    public TTLCacheImpl(long ttl, int capacity) {
        this.ttl = ttl;
        this.cache = new ConcurrentHashMap<>(capacity);
        init();
    }

    private void init() {
        Thread cleanup = new Thread(new CleanupTask<K,V>(this));
        cleanup.start();
    }

    public TTLCacheImpl() {
        this(1, 100);
    }

    @Override
    public V get(K key) {
        Objects.requireNonNull(key, "key==null");
        CacheHolder<V> value = cache.get(key);
        if(value == null){
            return null;
        }
        value.setLastAccessTime(LocalDateTime.now());
        System.out.printf("\nGetting%s ",value.getValue());
        return value.getValue();
    }

    @Override
    public void put(K key, V value) {
        Objects.requireNonNull(key, "key==null");
        System.out.printf("\nPutting %s,%s ",key,value);
        cache.put(key,new CacheHolder<V>(value));
    }

    @Override
    public V remove(K key) {
        Objects.requireNonNull(key, "key==null");
        cache.remove(key);
        return null;
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public void cleanup() {
        LocalDateTime now = LocalDateTime.now();
        Set<K> keys = cache.keySet();
        for(K key : keys){
            CacheHolder<V> value = cache.get(key);
            LocalDateTime lastAccessTime = value.getLastAccessTime();
            if(ChronoUnit.SECONDS.between(lastAccessTime, now) > ttl){
                System.out.printf("\nRemoving key:%s",key);
                cache.remove(key);
                Thread.yield();
            }
        }
    }
}

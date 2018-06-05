package lru;

import java.util.Map;
import java.util.Objects;

class LRUCache<K,V> implements Cache<K,V> {

    private static final int DEFAULT_CAPACITY = 10;
    private int capacity;
    private Map<K,V> cache;

    public LRUCache() {
        this(DEFAULT_CAPACITY);
    }

    public LRUCache(int capacity){
        assert capacity > 0;
        cache = new LruHashMap<>(capacity);
        this.capacity = capacity;
    }

    @Override
    public V get(K key) {
        Objects.requireNonNull(key, "key==null");
        synchronized (this){
            V value = cache.get(key);
            return value;
        }
    }

    @Override
    public void put(K key, V value) {
        Objects.requireNonNull(key, "key==null");
        synchronized (this){
            cache.put(key,value);
        }
    }

    @Override
    public V remove(K key) {
        Objects.requireNonNull(key, "key==null");
        synchronized (this){
            V value = cache.remove(key);
            return value;
        }
    }

    @Override
    public void clear() {
        synchronized (this){
            cache.clear();
        }
    }

    @Override
    public int size() {
        return capacity;
    }
}

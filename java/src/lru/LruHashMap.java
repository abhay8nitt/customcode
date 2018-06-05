package lru;

import java.util.LinkedHashMap;
import java.util.Map;

class LruHashMap<K,V> extends LinkedHashMap<K,V>{
    private final int capacity;

    public LruHashMap(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    protected boolean removeEldestEntry(Map.Entry entry) {
        return size() > capacity;
    }
}

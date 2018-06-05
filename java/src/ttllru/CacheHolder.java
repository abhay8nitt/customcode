package ttllru;

import java.time.LocalDateTime;

class CacheHolder<V> {
    private final V value;
    private LocalDateTime lastAccessTime;

    public CacheHolder(V value) {
        this.value = value;
        this.lastAccessTime = LocalDateTime.now();
    }

    public LocalDateTime getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(LocalDateTime lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "CacheHolder{" +
                "value=" + value +
                ", lastAccessTime=" + lastAccessTime +
                '}';
    }
}

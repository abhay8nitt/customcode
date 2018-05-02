package collections.custom.hashmap;

public class HashMap<K,V> {
    private final float loadFactor;

    static class Entry<K,V> {
        K key;
        V value;
        Entry<K,V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    private Entry<K,V>[] table;
    private static final int INITIAL_CAPACITY = 1 << 4;
    private int capacity;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        table = new Entry[INITIAL_CAPACITY];
        this.capacity = 16;
    }

    public void put(K key, V value){
        if(key == null) return;
        int hash = hash(key);
        Entry<K,V> newEntry = new Entry<>(key, value, null);

        if(table[hash] == null){
            table[hash] = newEntry;
        }else{
            // Place it in the linked list
            Entry<K,V> previous = null;
            Entry<K,V> current = table[hash];
            while (current != null) {
                if (current.key.equals(key)) {
                    if (previous == null) {
                        newEntry.next = current;
                        table[hash] = newEntry;
                        return;
                    } else {
                        newEntry.next = current.next;
                        previous.next = newEntry;
                        return;
                    }
                }
                previous = current;
                current = current.next;
            }
            previous.next = newEntry;
        }
    }

    public V get(K key){
        if(key == null) return null;
        int hash = hash(key);
        if(table[hash] == null) return null;
        else{
            Entry<K,V> temp = table[hash];
            while(temp != null){
                if(temp.key.equals(key)){
                    return temp.value;
                }
                temp = temp.next;
            }
        }
        return null;
    }

    public boolean remove(K key) {
        if (key == null) return false;
        int hash = hash(key);
        if (table[hash] == null) return false;
        Entry<K, V> previous = null;
        Entry<K, V> current = table[hash];
        while (current != null) {
            if (current.key.equals(key)) {
                if (previous == null) {
                    table[hash] = table[hash].next;
                    return true;
                } else {
                    previous.next = current.next;
                    return true;
                }
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public void printAll(){
        for(int i=0;i < capacity;i++){
            if(table[i] != null){
                Entry<K, V> entry = table[i];
                while(entry != null){
                    System.out.println("["+entry.key+"="+entry.value+"]" +"   ");
                    entry=entry.next;
                }
            }
        }
    }

    public boolean containsKey(Object obj) {
      boolean contains = false;
      if(table != null && obj != null){
          int hash = hash(obj);
          contains = table[hash] != null;
      }
      return contains;
    }

    private int hash(Object key){
        return Math.abs(key.hashCode()) % capacity;
    }
}

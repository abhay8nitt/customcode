package collections.custom.hashset;

import collections.custom.hashmap.HashMap;

public class HashSet<E> {

    private transient HashMap<E,Object> map;
    private final Object PRESENT = new Object();
    public HashSet() {
        map = new HashMap<>();
    }

    public boolean add(E obj){
        boolean exists = false;
        if(map.get(obj) != null){
            exists = true;
        }
        map.put(obj,PRESENT);
        return exists;
    }

    public boolean contains(Object obj){
        return map.containsKey(obj);
    }

    public boolean remove(E obj){
        return map.remove(obj);
    }
}

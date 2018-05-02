package collections.custom.arraylist;

import java.io.IOException;
import java.io.Serializable;

class ArrayList<T> implements Serializable, Cloneable{
    private transient Object[] elements;
    private int capacity;
    private int size;
    private static final int DEFAULT_SIZE = 10;
    private final Object[] EMPTY = {};
    private static final float loadFactor = 0.75f;
    public ArrayList() {
        this(DEFAULT_SIZE);
    }

    public ArrayList(int capacity) {
        if(capacity > 0) {
            this.capacity = capacity;
            elements = new Object[capacity];
            this.size = 0;
        }else{
            throw new IllegalArgumentException("Illegal Capacity "+ capacity);
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean contains(T e){
        return indexOf(e) >= 0;
    }

    public void clear(){
        for(int i =0 ;i <elements.length; i++){
            elements[i] = null;
        }
        size = 0;
    }

    private boolean resize(float capacity) {
        return Float.compare(capacity, loadFactor) == 1;
    }

    private void grow() {
        Object []temp = new Object[2* capacity];
        System.arraycopy(elements, 0, temp, 0, size);
        elements = temp;
    }

    public void add(T e){
        float capacity = (float) size / this.capacity;
        if(resize(capacity)){
            grow();
        }
        elements[size++] = e;
    }

    public void add(int index, T e){
        if(index < size){
            Object []temp = new Object[capacity];
            System.arraycopy(elements, 0, temp, 0, index);
            temp[index] = e;
            System.arraycopy(elements, index, temp, index + 1, size - index);
            elements = temp;
            size++;
        }
    }
    public T get(int index){
        if(index >= size){
            throw new IllegalArgumentException("Illegal access at index "+ index);
        }
        return (T)elements[index];
    }

    public int indexOf(T e){
        int index = -1;
        if(e == null){
            for(int i = 0; i < size; i++){
                if(elements[i] == null){
                    index = i;
                    break;
                }
            }
        }else {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(e)) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    public int lastIndexOf(T e){
        int index  = -1;
        if(e == null){
            for(int i = size - 1;i >= 0;i--){
                if(elements[i] == null){
                    index = i;
                    break;
                }
            }
        }else{
            for(int i = size - 1;i >= 0;i--){
                if(e.equals(elements[i])){
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    public T remove(int index){
        if(index > size){
            throw new IllegalArgumentException("Illegal access at index "+ index);
        }
        T e = (T) elements[index];
        if(index < size){
            System.arraycopy(elements, index + 1, elements, index, size -index -1);
        }
        elements[--size] = null;
        return e;
    }

    private void writeObject(java.io.ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(size);
        for(int i = 0;i < size; i++){
            s.writeObject(elements[i]);
        }
    }

    private void readObject(java.io.ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        s.readInt();
        if(size > 0){
            Object []temp = elements;
            for(int i = 0;i < size;i++){
                temp[i] = s.readObject();
            }
        }
    }
}

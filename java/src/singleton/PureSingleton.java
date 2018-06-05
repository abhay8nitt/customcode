package singleton;

import java.io.*;
import java.lang.reflect.Constructor;

public class PureSingleton implements Serializable, Cloneable{

    private static PureSingleton instance = new PureSingleton();
    private PureSingleton(){
        if(PureSingleton.instance != null ) {
            throw new InstantiationError("Creating of a singleton object is prohibited" ); // avoid serialization from reflection or use enum
        }
    }

    public static PureSingleton getInstance(){
        return instance;
    }

    protected Object readResolve(){ // avoid serialization of singleton
        return instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning of singleton object is prohibited");
    }

    public static void main(String[] args) {
        PureSingleton singleton = PureSingleton.getInstance();
        PureSingleton singleton1 = null;
        System.out.println("\nTest reflection for singleton");
        try
        {
            Constructor[] constructors = PureSingleton.class.getDeclaredConstructors();
            for (Constructor constructor : constructors){
                constructor.setAccessible(true);
                singleton1 = (PureSingleton) constructor.newInstance();
                break;
            }
        }catch (Exception e){
            singleton1 = singleton;
            System.out.println(e.getCause());
        }
        System.out.println("instance1.hashCode():- "
                + singleton.hashCode());
        System.out.println("instance2.hashCode():- "
                + singleton1.hashCode());

        System.out.println("\nTest serialization for singleton");
        PureSingleton singleton2 ;
        try {
            ObjectOutput out
                    = new ObjectOutputStream(new FileOutputStream("file.text"));
            out.writeObject(singleton);
            out.close();
            ObjectInput in
                    = new ObjectInputStream(new FileInputStream("file.text"));
            singleton2 = (PureSingleton) in.readObject();
            in.close();

            System.out.println("instance1 hashCode:- "
                    + singleton.hashCode());
            System.out.println("instance2 hashCode:- "
                    + singleton2.hashCode());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("\nTest cloneable for singleton");
        PureSingleton instance1 = singleton;
        PureSingleton instance2 = null;
        try {
            instance2 = (PureSingleton) instance1.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
            instance2 = instance1;
        }
        System.out.println("instance1 hashCode:- "
                + instance1.hashCode());
        System.out.println("instance2 hashCode:- "
                + instance2.hashCode());
    }
}


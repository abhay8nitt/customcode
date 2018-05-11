package multithreading.examples.collections;

import java.util.*;

public class ListImpl {
    public static void main(String[] args) {
        arrayListExample();
        vectorExamples();
        linkedListExamples();
    }

    public static void arrayListExample(){
        System.out.println("Array List Examples");
        System.out.println("--------------------------------");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("Size of the list:"+ list.size());
        System.out.printf("Item at index %d: %d\n", 2, list.get(2));
        System.out.printf("Remove item at index %d: %d\n", 2, list.remove(2));
        System.out.println("Size of the list:"+ list.size());
        list.addAll(Arrays.asList(10,20,30));
        System.out.println("Size of the list:"+ list.size());
        System.out.println("Content of the array list: "+ list);
        System.out.println("--------------------------------");
    }

    public static void vectorExamples(){
        System.out.println("Vector Examples");
        System.out.println("--------------------------------");
        List<Integer> list = new Vector<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("Size of the list:"+ list.size());
        System.out.printf("Item at index %d: %d\n", 2, list.get(2));
        System.out.printf("Remove item at index %d: %d\n", 2, list.remove(2));
        System.out.println("Size of the list:"+ list.size());
        list.addAll(Arrays.asList(10,20,30));
        System.out.println("Size of the list:"+ list.size());
        System.out.println("Content of the vector: "+ list);
        System.out.println("--------------------------------");
    }

    public static void linkedListExamples(){
        System.out.println("Linked List Examples");
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addLast(4);
        list.addLast(5);
        System.out.println("Size of the list:"+ list.size());
        System.out.println("First element in the linked list:"+ list.getFirst());
        System.out.println("Last element in the linked list:"+ list.getLast());
        System.out.println("Is 4 is in linked list?"+ list.contains(4));
        System.out.println("Contets of linked list:"+ list);
        System.out.println("--------------------------------");
    }
}

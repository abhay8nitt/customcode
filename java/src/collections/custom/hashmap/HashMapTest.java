package collections.custom.hashmap;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap<Student, Integer> map = new HashMap<>();
        map.put(new Student("Student 1", 10, "UKG",'n'),10);
        map.put(new Student("Student 1", 11, "UKG",'n'), 11);
        map.put(new Student("Student 2", 12, "LKG",'y'),12);
        Student student = new Student("Student 2", 12, "LKG",'n');
        map.put(student, 13);
        map.printAll();
        System.out.println(map.containsKey(student));
        System.out.println(map.containsKey(null));
    }
}

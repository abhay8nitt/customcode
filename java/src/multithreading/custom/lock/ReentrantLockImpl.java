package multithreading.custom.lock;

public class ReentrantLockImpl {
    public static int count=100;
    public static void main(String[] args) {
        System.out.println("MyLockExample.main()");
        Employee em = new Employee(count, "employee");
        Thread t1 = new Thread(new Employeelock(em));
        Thread t2 = new Thread(new Employeelock(em));
        Thread t3 = new Thread(new Employeelock(em));
        t1.start();
        t2.start();
        t3.start();
    }
}

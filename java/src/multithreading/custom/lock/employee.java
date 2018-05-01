package multithreading.custom.lock;

class Employee {
    int id ;
    String name;
    ReentrantLock cl = new ReentrantLock();
    public Employee(int a, String b ){
        id=a;
        name=b;
    }
    public void printName() throws InterruptedException {
        cl.lock();
        System.out.println(Thread.currentThread().getName()+ " greets hello to " + name);
        cl.unLock();
    }
}



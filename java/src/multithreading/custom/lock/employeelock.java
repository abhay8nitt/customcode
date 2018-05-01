package multithreading.custom.lock;

class Employeelock implements Runnable{
    Employee local;
    public Employeelock(Employee param) {
        local=param;
    }
    @Override
    public void run() {

        try {
           local.printName();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

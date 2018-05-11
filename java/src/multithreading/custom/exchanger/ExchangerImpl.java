package multithreading.custom.exchanger;

public class ExchangerImpl {
    public static void main(String[] args) {
        stringExchanger();
        integerExchanger();
    }

    private static void integerExchanger() {
        ThreadGroup group = new ThreadGroup("Integer Group");
        Exchanger<Integer> integerExchanger = new Exchanger<>();
        ExchangerRunnable exchangerRunnable1 = new ExchangerRunnable(integerExchanger, 123);
        ExchangerRunnable exchangerRunnable2 = new ExchangerRunnable(integerExchanger, 456);
        new Thread(group,exchangerRunnable1,"Integer Thread A").start();
        new Thread(group,exchangerRunnable2,"Integer Thread B").start();

    }

    private static ThreadGroup stringExchanger() {
        ThreadGroup group = new ThreadGroup("String Group");
        Exchanger<String> stringExchanger = new Exchanger<>();
        ExchangerRunnable exchangerRunnable1 = new ExchangerRunnable(stringExchanger, "String A");
        ExchangerRunnable exchangerRunnable2 = new ExchangerRunnable(stringExchanger, "String B");
        new Thread(group,exchangerRunnable1,"String Thread A").start();
        new Thread(group,exchangerRunnable2,"String Thread B").start();
        return group;
    }
}

package multithreading.custom.semaphore;

public class BookingImpl {
    public static void main(String[] args) {
        BookingAgent agent = new BookingAgent(new Reservation());
        for(int i=0;i< 5;i++){
            Thread thread = new Thread(agent,"Passenger "+ (i+1));
            thread.start();
        }
    }
}

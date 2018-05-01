package multithreading.custom.semaphore;

public class BookingAgent implements Runnable{
    private Reservation reservation;

    public BookingAgent(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public void run() {
        System.out.println("Initiate booking for "+Thread.currentThread().getName());
        reservation.book(new Object());
        System.out.println("Successfully booked ticket for "+Thread.currentThread().getName());
    }
}
package org.designPatterns.pubSub;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Seat {

    private final long seatId;

    private boolean booked;

    public Seat(long seatId) {
        this.seatId = seatId;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public long getSeatId() {
        return seatId;
    }
}

public class BrokenSeatBookingSystem {

    private final Map<Long, Seat> seats =
            new ConcurrentHashMap<>();

    public BrokenSeatBookingSystem() {

        seats.put(1L, new Seat(1L));

        seats.put(2L, new Seat(2L));

        seats.put(3L, new Seat(3L));

    }

    public void bookSeat(long seatId, String user) {

        Seat seat = seats.get(seatId);
        synchronized (seat) {
            // STEP-1
            if (seat.isBooked()) {

                System.out.println(
                        user + " FAILED. Already booked; seat" + seatId
                );

                return;
            }

            // Artificial delay to expose race condition
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

            // STEP-2
            seat.setBooked(true);

            System.out.println(
                    user + " SUCCESSFULLY BOOKED seat "
                            + seatId
            );
        }
    }

    public static void main(String[] args)
            throws InterruptedException {


        BrokenSeatBookingSystem system =
                new BrokenSeatBookingSystem();

        Thread t1 = new Thread(() ->
                system.bookSeat(1L, "USER-1")
        );

        Thread t2 = new Thread(() ->
                system.bookSeat(1L, "USER-2")
        );
        Thread t3 = new Thread(() ->
                system.bookSeat(2L, "USER-3")
        );

        Thread t4 = new Thread(() ->
                system.bookSeat(2L, "USER-4")
        );


        t4.start();
        t3.start();
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
    }
}
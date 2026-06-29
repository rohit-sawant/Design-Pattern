package org.designPatterns.solid.singleResponsibility;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

class Seat {
    private final String seatId;
    private boolean booked = false;
    private final ReentrantLock lock = new ReentrantLock();

    public Seat(String seatId) {
        this.seatId = seatId;
    }

    public String getSeatId() {
        return seatId;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public boolean isBooked() {
        return booked;
    }

    public void book(String userId) {
        this.booked = true;
        System.out.println("Seat " + seatId + " booked by " + userId);
    }
}

class BookingService {

    private final Map<String, Seat> seats = new ConcurrentHashMap<>();

    public void addSeat(Seat seat) {
        seats.put(seat.getSeatId(), seat);
    }

    public boolean bookSeats(String userId, List<String> seatIds) {

        List<Seat> requestedSeats = new ArrayList<>();
        for (String id : seatIds) {
            Seat seat = seats.get(id);
            if (seat == null) return false;
            requestedSeats.add(seat);
        }

        // 🔑 Deadlock prevention
        requestedSeats.sort(Comparator.comparing(Seat::getSeatId));

        List<Seat> lockedSeats = new ArrayList<>();

        try {
            for (Seat seat : requestedSeats) {
                seat.getLock().lock();
                lockedSeats.add(seat);
            }

            // Check availability
            for (Seat seat : requestedSeats) {
                if (seat.isBooked()) {
                    System.out.println(userId + " failed (already booked)");
                    return false;
                }
            }

            // Book seats
            for (Seat seat : requestedSeats) {
                seat.book(userId);
            }

            System.out.println(userId + " SUCCESS booking " + seatIds);
            return true;

        } finally {
            for (Seat seat : lockedSeats) {
                seat.getLock().unlock();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {

        BookingService service = new BookingService();

        // Create seats A-E
        for (char c = 'A'; c <= 'E'; c++) {
            service.addSeat(new Seat(String.valueOf(c)));
        }

        ExecutorService executor = Executors.newFixedThreadPool(3);

        // User 1 → tries A, B, C
        Runnable user1 = () -> {
            service.bookSeats("User1", Arrays.asList("A", "B", "C"));
        };

        // User 2 → tries B, C, D (overlap)
        Runnable user2 = () -> {
            service.bookSeats("User2", Arrays.asList("B", "C", "D"));
        };

        // User 3 → tries D, E (no overlap with A,B initially)
        Runnable user3 = () -> {
            service.bookSeats("User3", Arrays.asList("D", "E"));
        };

        executor.submit(user1);
        executor.submit(user2);
        executor.submit(user3);

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("----- FINAL STATUS -----");
    }

}


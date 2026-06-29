package org.LLD.parkingLot;

import org.LLD.parkingLot.entity.ParkingFloor;
import org.LLD.parkingLot.entity.Slot;
import org.LLD.parkingLot.entity.Ticket;
import org.LLD.parkingLot.entity.payment.PaymentMode;
import org.LLD.parkingLot.entity.vehicle.Car;
import org.LLD.parkingLot.entity.vehicle.Vehicle;
import org.LLD.parkingLot.entity.vehicle.VehicleType;
import org.LLD.parkingLot.service.ParkingLot;
import org.LLD.parkingLot.strategy.TimeBasedPricingStrategy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ParkingLotDemo {
    public static void main(String[] args) {
        Vehicle car1 = new Car("123");
        Vehicle car2 = new Car("345");

        Vehicle bike1 = new Car("346");
        Vehicle bike2 = new Car("346");


        ParkingLot parkingLot = ParkingLot.getInstance();
        parkingLot.setPricingStrategy(new TimeBasedPricingStrategy());

        parkingLot.addFloor(
//                first floor
                new ParkingFloor(
                        "1",
                        List.of(
                                new Slot("p1s1", VehicleType.CAR, new AtomicBoolean()),
                                new Slot("p1s2", VehicleType.CAR, new AtomicBoolean())

                        ),
                        1
                )
        );
        parkingLot.addFloor(
//                first floor
                new ParkingFloor(
                        "2",
                        List.of(
                                new Slot("p2s1", VehicleType.BIKE, new AtomicBoolean()),
                                new Slot("p2s2", VehicleType.BIKE, new AtomicBoolean())

                        ),
                        2
                )
        );
        Ticket ticketForCar1  =  parkingLot.parkVehicle(car1, LocalDateTime.now());
        Slot slot = parkingLot.unParkVehicle(ticketForCar1,LocalDateTime.now(), PaymentMode.CARD);

    }
}

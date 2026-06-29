package org.LLD.parkingLot.strategy;

import org.LLD.parkingLot.entity.Ticket;
import org.LLD.parkingLot.entity.vehicle.Vehicle;
import org.LLD.parkingLot.entity.vehicle.VehicleType;

import java.time.LocalDateTime;

public interface PricingStrategy {
    int calculateAmount(VehicleType vehicleType, LocalDateTime entry,LocalDateTime exit);
}

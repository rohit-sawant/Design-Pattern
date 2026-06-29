package org.LLD.parkingLot.entity;

import org.LLD.parkingLot.entity.vehicle.Vehicle;
import org.LLD.parkingLot.entity.vehicle.VehicleType;

import java.time.LocalDateTime;

public interface EntryService {
    Ticket parkVehicle(Vehicle vehicle, LocalDateTime entry);
}

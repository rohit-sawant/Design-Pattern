package org.LLD.parkingLot.entity;

import org.LLD.parkingLot.entity.vehicle.Vehicle;
import org.LLD.parkingLot.entity.vehicle.VehicleType;

import java.time.LocalDateTime;

public class EntryGate extends Gate implements EntryService {
    @Override
    protected GateType getType() {
        return GateType.ENTRY;
    }

    @Override
    public Ticket parkVehicle(Vehicle vehicle, LocalDateTime entry) {
        return null;
    }
}

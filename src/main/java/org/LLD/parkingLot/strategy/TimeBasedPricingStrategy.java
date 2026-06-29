package org.LLD.parkingLot.strategy;

import org.LLD.parkingLot.entity.vehicle.VehicleType;

import java.time.LocalDateTime;

public class TimeBasedPricingStrategy implements PricingStrategy{

    @Override
    public int calculateAmount(VehicleType vehicleType, LocalDateTime entry, LocalDateTime exit) {
        return 0;
    }
}

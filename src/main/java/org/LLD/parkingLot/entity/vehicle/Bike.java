package org.LLD.parkingLot.entity.vehicle;

public class Bike extends Vehicle{

    public Bike(String number){
        super(number);
    }

    @Override
    public VehicleType getType() {
        return VehicleType.BIKE;
    }

}

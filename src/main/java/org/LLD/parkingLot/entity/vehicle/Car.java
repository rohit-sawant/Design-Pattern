package org.LLD.parkingLot.entity.vehicle;

public class Car extends Vehicle{

    public Car(String number){
        super(number);
    }

    @Override
    public VehicleType getType() {
        return VehicleType.CAR;
    }

}

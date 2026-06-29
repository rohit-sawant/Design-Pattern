package org.LLD.parkingLot.entity.vehicle;

public abstract class Vehicle {
    String number;

    public Vehicle(String number) {
        this.number = number;
    }

    public abstract VehicleType getType();


}

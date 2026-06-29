package org.LLD.parkingLot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.LLD.parkingLot.entity.vehicle.VehicleType;

import java.util.concurrent.atomic.AtomicBoolean;

@AllArgsConstructor

public class Slot {
    String id;

    @Getter
    VehicleType vehicleType;

    AtomicBoolean available;

    boolean tryLock(){
        return available.compareAndSet(false,true);
    }

    public void release(){
        available.set(false);
    }



}

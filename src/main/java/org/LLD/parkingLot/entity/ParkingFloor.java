package org.LLD.parkingLot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.LLD.parkingLot.entity.vehicle.VehicleType;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Data
public class ParkingFloor {
    String id;
    List<Slot> slots;
    int floor;

    public Optional<Slot> getAvailableSlot(VehicleType vehicleType){
//         todo: handle this find first error
        return slots.stream().filter((slot -> slot.getVehicleType().equals(vehicleType) && slot.tryLock())).findFirst();
    }

}

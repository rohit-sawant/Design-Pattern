package org.LLD.parkingLot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.LLD.parkingLot.entity.vehicle.Vehicle;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class Ticket {
    String id;
    Vehicle vehicle;
    Slot slot;
    LocalDateTime entryTime;

}

package org.LLD.parkingLot.service;

import lombok.Builder;
import lombok.Setter;
import org.LLD.parkingLot.entity.*;
import org.LLD.parkingLot.entity.payment.PaymentMode;
import org.LLD.parkingLot.entity.vehicle.Vehicle;
import org.LLD.parkingLot.entity.vehicle.VehicleType;
import org.LLD.parkingLot.exception.NoSlootAvailableException;
import org.LLD.parkingLot.strategy.PaymentStrategy;
import org.LLD.parkingLot.strategy.PricingStrategy;
import org.LLD.parkingLot.strategy.TimeBasedPricingStrategy;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class ParkingLot implements EntryService, ExitService {


    Map<String,ParkingFloor> floors = new HashMap<>();
    Map<String,Ticket> activeTickets = new HashMap<>();


    @Setter
    public PricingStrategy pricingStrategy;

    private final static ParkingLot parkingLot = new ParkingLot();
    private ParkingLot(){
        this.pricingStrategy =  new TimeBasedPricingStrategy();
    }
    public static ParkingLot getInstance(){
        return parkingLot;
    }


    public void addFloor(ParkingFloor floor){
        floors.put(floor.getId(),floor);
    }
    @Override
    public Ticket parkVehicle(Vehicle vehicle, LocalDateTime entry) {
      for (ParkingFloor floor: floors.values()){
         Optional<Slot> slot =  floor.getAvailableSlot(vehicle.getType());
         if(slot.isPresent()){
             Ticket ticket =  new Ticket(UUID.randomUUID().toString(),vehicle,slot.get(),entry);
             activeTickets.put(ticket.getId(),ticket);
             return ticket;
         }
      }

      throw new NoSlootAvailableException();
    }

    @Override
    public Slot unParkVehicle(Ticket ticket, LocalDateTime exitType, PaymentMode paymentMode) {
        Slot ticketSlot  = ticket.getSlot();
        ticketSlot.release();
        int amount = pricingStrategy.calculateAmount(ticket.getVehicle().getType(),ticket.getEntryTime(),exitType);
        return ticketSlot;
    }
}

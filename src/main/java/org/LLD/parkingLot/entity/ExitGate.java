package org.LLD.parkingLot.entity;

import org.LLD.parkingLot.entity.payment.PaymentMode;

import java.time.LocalDateTime;

public class ExitGate extends  Gate implements ExitService {
    @Override
    protected GateType getType() {
        return GateType.EXIT;
    }

    @Override
    public Slot unParkVehicle(Ticket ticket, LocalDateTime exitType, PaymentMode paymentMode) {
        return null;
    }
}

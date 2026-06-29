package org.LLD.parkingLot.entity;

import org.LLD.parkingLot.entity.payment.PaymentMode;

import java.time.LocalDateTime;

public interface ExitService {
    Slot unParkVehicle(Ticket ticket, LocalDateTime exitType, PaymentMode paymentMode);
}

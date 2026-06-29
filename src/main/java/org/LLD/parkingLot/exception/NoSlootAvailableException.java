package org.LLD.parkingLot.exception;

public class NoSlootAvailableException extends RuntimeException{
    public NoSlootAvailableException() {
        super("NO SLOTS AVAILABLE FOR BOOKING");
    }
}

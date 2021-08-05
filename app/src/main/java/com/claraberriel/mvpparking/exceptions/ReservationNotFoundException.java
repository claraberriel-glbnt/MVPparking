package com.claraberriel.mvpparking.exceptions;

public class ReservationNotFoundException extends Exception {
    public ReservationNotFoundException() {
        super("Reservation Not Found");
    }
}

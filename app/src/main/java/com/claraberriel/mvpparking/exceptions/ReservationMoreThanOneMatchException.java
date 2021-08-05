package com.claraberriel.mvpparking.exceptions;

public class ReservationMoreThanOneMatchException extends Exception {
    public ReservationMoreThanOneMatchException() {
        super("More than one match");
    }
}

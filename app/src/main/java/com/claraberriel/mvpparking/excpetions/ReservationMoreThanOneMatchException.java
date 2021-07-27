package com.claraberriel.mvpparking.excpetions;

public class ReservationMoreThanOneMatchException extends Exception {
    public ReservationMoreThanOneMatchException() {
        super("More than one match");
    }
}

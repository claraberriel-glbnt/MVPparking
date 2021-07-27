package com.claraberriel.mvpparking.excpetions;

public class ReservationListEmptyException extends Exception{
    public ReservationListEmptyException(){
        super("Reservation list is empty");
    }
}

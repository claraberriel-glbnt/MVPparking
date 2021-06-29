package com.claraberriel.mvpparking.mvp.model;

import com.claraberriel.mvpparking.entities.Reservation;

import java.util.ArrayList;

public class ReservationModel extends Reservation {
    private ArrayList<Reservation> reservations;

    public ReservationModel() {
        this.reservations = new ArrayList<>();
    }

    /*
    // should I use throws IllegalNumberException?
    public boolean addNewReservation(Reservation reservation) {
        if (reservation == null) {
            return false;
        }
        // Check condition if list less or equal to parkingSize
        if (reservation.getParkingNumber() <= getParkingSize()) {
            this.reservations.add(reservation);
        }
        return true;
    }

     */
}

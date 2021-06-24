package com.claraberriel.mvpparking.mvp.model;

import com.claraberriel.mvpparking.entities.Reservation;

import java.util.ArrayList;

public class ParkingModel {
    private int parkingSize;
    private ArrayList<Reservation> reservations;

    public ParkingModel() {
        this.reservations = new ArrayList<>();
    }

    // I have another getParkingSize on the view that was renamed from getEditTextNumber
    // what is going on here?
    public int getParkingSize() {
        return this.parkingSize;
    }

    public void setParkingSize(int size) {
        this.parkingSize = size;
    }

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
}

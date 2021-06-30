package com.claraberriel.mvpparking.mvp.model;

import com.claraberriel.mvpparking.entities.Parking;
import com.claraberriel.mvpparking.entities.Reservation;

import java.util.ArrayList;

public class ReservationModel {
    private Parking parking;

    public ReservationModel(Parking parking) {
        this.parking = parking;
    }

    public Parking getParking() {
        return parking;
    }

    public void addReservation(Reservation reservation) {
        ArrayList<Reservation> reservations = parking.getReservations();
        reservations.add(reservation);
    }
}

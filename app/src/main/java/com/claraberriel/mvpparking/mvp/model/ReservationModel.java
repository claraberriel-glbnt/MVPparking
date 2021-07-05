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

    public ArrayList<Reservation> getReservationsList() {
        return parking.getReservations();
    }

    public void addReservation(Reservation reservation) {
        if (validateAddReservation(reservation)) {
            ArrayList<Reservation> reservations = getReservationsList();
            reservations.add(reservation);
        }
    }

    public boolean validateAddReservation(Reservation reservation) {
        ArrayList<Reservation> reservations = getReservationsList();
        if (reservations.size() == 0) {
            return true;
        }
        for (Reservation reservationInList : reservations) {
            if (reservation.getParkingNumber() != reservationInList.getParkingNumber()) {
                return true;
            } else {
                return reservation.getStartDateTime() > reservationInList.getEndDateTime();
            }
        }
        return true;
    }
}

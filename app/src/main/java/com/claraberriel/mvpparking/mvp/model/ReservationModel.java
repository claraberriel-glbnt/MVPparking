package com.claraberriel.mvpparking.mvp.model;

import com.claraberriel.mvpparking.entities.Parking;
import com.claraberriel.mvpparking.entities.Reservation;

import java.util.ArrayList;

public class ReservationModel {
    private Parking parking;

    public ReservationModel(Parking parking) {
        this.parking = parking;
    }

    public int getParkingNumber(String parkingNumber) throws IllegalArgumentException {
        int result = Integer.parseInt(parkingNumber);
        if (result <= 0) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    public boolean addReservation(Reservation reservation) {
        if (validateAddReservation(reservation)) {
            ArrayList<Reservation> reservations = parking.getReservations();
            reservations.add(reservation);
            return true;
        }
        return false;
    }

    private boolean validateAddReservation(Reservation reservation) {
        if (parking.getReservations().size() == 0) {
            return true;
        }
        for (Reservation reservationInList : parking.getReservations()) {
            if (reservation.getParkingNumber() == reservationInList.getParkingNumber()
                    && reservation.getStartDateTime() < reservationInList.getEndDateTime()) {
                return false;
            } //ToDo fix tests
        }
        return true;
    }

    public int getParkingSize() {
        return parking.getParkingSize();
    }

    public Parking getParking() {
        return parking;
    }
}

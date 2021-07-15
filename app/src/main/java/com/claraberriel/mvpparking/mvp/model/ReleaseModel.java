package com.claraberriel.mvpparking.mvp.model;


import android.util.Log;

import com.claraberriel.mvpparking.entities.Parking;
import com.claraberriel.mvpparking.entities.Reservation;

public class ReleaseModel {

    private final Parking parking;

    public ReleaseModel(Parking parking) {
        this.parking = parking;
    }

    public Parking getParking() {
        return parking;
    }

    public boolean parkingRelease(Reservation reservation) {
        if (getParking().getReservations().size() <= 0) {
            return false;
        }
        for (Reservation reservationInList : getParking().getReservations()) {
            if (reservation.equals(reservationInList)) {
                getParking().getReservations().remove(reservation);
                return true;
            }
        }
        return false;
    }

    public int getParkingNumber(String parkingNumberString) {
        int parkingNumber = 0;
        try {
            parkingNumber = Integer.parseInt(parkingNumberString);
        } catch (NumberFormatException ex) {
            Log.e(ReservationModel.class.getSimpleName(), ex.getLocalizedMessage());
        }
        return parkingNumber;
    }
}

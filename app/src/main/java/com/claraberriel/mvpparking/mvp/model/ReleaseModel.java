package com.claraberriel.mvpparking.mvp.model;


import com.claraberriel.mvpparking.entities.Parking;
import com.claraberriel.mvpparking.entities.Reservation;

import java.util.Date;

public class ReleaseModel {

    private final Parking parking;

    public ReleaseModel(Parking parking) {
        this.parking = parking;
    }

    public Parking getParking() {
        return parking;
    }

    public int getParkingLotNumber(String parkingNumber) throws IllegalArgumentException {
        int result = Integer.parseInt(parkingNumber);
        if (result <= 0) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    public boolean checkIfAnyReservationExists() throws IllegalArgumentException {
        if (getParking().getReservations().size() == 0) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    public boolean parkingRelease(int parkingNumber, String securityCode) {
        Reservation reservation = new Reservation(new Date().getTime(),
                new Date().getTime(), parkingNumber, securityCode);
        for (Reservation reservationInList : getParking().getReservations()) {
            if (reservation.equals(reservationInList)) {
                getParking().getReservations().remove(reservation);
                return true;
            }
        }
        return false;
    }

}

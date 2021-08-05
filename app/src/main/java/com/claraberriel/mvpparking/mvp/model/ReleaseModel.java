package com.claraberriel.mvpparking.mvp.model;


import com.claraberriel.mvpparking.entities.Parking;
import com.claraberriel.mvpparking.entities.Reservation;
import com.claraberriel.mvpparking.exceptions.ReservationListEmptyException;
import com.claraberriel.mvpparking.exceptions.ReservationMoreThanOneMatchException;
import com.claraberriel.mvpparking.exceptions.ReservationNotFoundException;

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

    public void parkingRelease(int parkingNumber, String securityCode) throws ReservationListEmptyException, ReservationMoreThanOneMatchException, ReservationNotFoundException {
        if (getParking().getReservations().size() == 0) {
            throw new ReservationListEmptyException();
        }
        Reservation reservation = new Reservation(new Date().getTime(),
                new Date().getTime(), parkingNumber, securityCode);

        int count = 0;
        for (Reservation reservationInList : getParking().getReservations()) {
            if (reservation.equals(reservationInList)) {
                count++;
            }
        }
        if (count == 1) {
            getParking().getReservations().remove(reservation);
        } else if (count > 1) {
            throw new ReservationMoreThanOneMatchException();
        } else if (count == 0) {
            throw new ReservationNotFoundException();
        }
    }
}

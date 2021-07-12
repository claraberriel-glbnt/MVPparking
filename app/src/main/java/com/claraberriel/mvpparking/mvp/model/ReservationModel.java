package com.claraberriel.mvpparking.mvp.model;

import com.claraberriel.mvpparking.entities.Parking;
import com.claraberriel.mvpparking.entities.Reservation;
import com.claraberriel.mvpparking.utilities.DateUtils;

import java.util.ArrayList;
import java.util.Date;

public class ReservationModel {
    private Parking parking;
    DateUtils dateUtils;

    public ReservationModel(Parking parking, DateUtils dateUtils) {
        this.parking = parking;
        this.dateUtils = dateUtils;
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

    public boolean validateAddReservation(Reservation reservation) {
        ArrayList<Reservation> reservations = parking.getReservations();
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

    public boolean isDateInThePast(Date date) {
        return dateUtils.isDateInThePast(date);
    }

    public boolean isEndDateBeforeStartDate(Date startDate, Date endDate) {
        return dateUtils.isEndDateBeforeStartDate(startDate, endDate);
    }

    public int getParkingSize() {
        return parking.getParkingSize();
    }

    public Parking getParking() {
        return parking;
    }
}

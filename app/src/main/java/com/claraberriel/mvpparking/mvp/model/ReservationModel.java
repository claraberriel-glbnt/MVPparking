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

    public boolean isDateInThePast(Date date) {
        return dateUtils.isDateInThePast(date);
    }

    public boolean isEndDateBeforeStartDate(Date endDate, Date startDate) {
        return dateUtils.isEndDateBeforeStartDate(endDate, startDate);
    }
}

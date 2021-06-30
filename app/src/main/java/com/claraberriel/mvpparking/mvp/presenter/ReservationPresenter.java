package com.claraberriel.mvpparking.mvp.presenter;

import android.util.Log;

import com.claraberriel.mvpparking.entities.Reservation;
import com.claraberriel.mvpparking.mvp.model.ReservationModel;
import com.claraberriel.mvpparking.mvp.view.ReservationView;
import com.claraberriel.mvpparking.utilities.DateUtils;

import java.util.Date;

public class ReservationPresenter {
    private Reservation reservation = new Reservation(); // ToDo construct later
    private final ReservationView reservationView;
    private final ReservationModel reservationModel;

    public ReservationPresenter(ReservationView reservationView, ReservationModel reservationModel) {
        this.reservationView = reservationView;
        this.reservationModel = reservationModel;
    }

    /**
     * Event Handlers
     */

    public void onFrom(){
        reservationView.startDateTimeDialog();
    }

    public void onTo() {
        reservationView.endDateTimeDialog();
    }

    public void onSchedule() {
        setReservationSecurityCode();
        verifyAndSetParkingNumber();
        verifyAndSetDates();
        reservationModel.addReservation(reservation);
    }

    /**
     * Setters: check for error and exceptions and then send the data to reservation
     */

    private void setReservationSecurityCode() {
        String securityCode = reservationView.getSecurityCode();
        reservation.setSecurityCode(securityCode);
    }

    public void verifyAndSetParkingNumber() {
        int parkingNumber = reservationView.getParkingNumber();
        if (parkingNumber <= reservationModel.getParking().getParkingSize()){
            try {
                reservation.setParkingNumber(parkingNumber);
            } catch (NumberFormatException e) {
                Log.e(ReservationPresenter.class.getSimpleName(), e.toString());
                reservationView.showInvalidNumber();
            } catch (IllegalArgumentException ex) {
                Log.e(ReservationPresenter.class.getSimpleName(), ex.toString());
                reservationView.showZeroNotAccepted();
            }
        }
        else {
            reservationView.showLargeNumber(parkingNumber);
        }
    }

    private void verifyAndSetDates(){
        Date startDate = reservationView.getStartDate();
        Date endDate = reservationView.getEndDate();

        if (startDate != null && endDate != null){
            if (!DateUtils.isDateInTheFuture(startDate) || !DateUtils.isDateInTheFuture(endDate)){
                reservationView.showErrorNoReservationInThePast();
            }
            else if (DateUtils.isEndDateBeforeStartDate(endDate, startDate)){
                reservationView.showBackToTheFuture();
            }
            else {
                reservation.setStartDateTime(startDate.getTime());
                reservation.setEndDateTime(endDate.getTime());
            }
        }
        else {
            reservationView.showMissingField();
        }
    }
}

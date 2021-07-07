package com.claraberriel.mvpparking.mvp.presenter;

import android.util.Log;

import com.claraberriel.mvpparking.entities.Parking;
import com.claraberriel.mvpparking.entities.Reservation;
import com.claraberriel.mvpparking.mvp.model.ReservationModel;
import com.claraberriel.mvpparking.mvp.view.ReservationView;

import java.util.Date;

public class ReservationPresenter {
    ReservationView reservationView;
    ReservationModel reservationModel;

    public ReservationPresenter(ReservationView reservationView, ReservationModel reservationModel) {
        this.reservationView = reservationView;
        this.reservationModel = reservationModel;
    }

    public Parking getParkingWithReservations(){
        return reservationModel.getParking();
    }

    /**
     * Event Handlers
     */

    public void onFrom() {
        reservationView.startDateTimeDialog();
    }

    public void onTo() {
        reservationView.endDateTimeDialog();
    }

    public boolean onSchedule() {
        if (areDatesValid() && isParkingNumberValid() && isSecurityCodeValid()) {
            Reservation reservation = new Reservation(reservationView.getStartDate().getTime(),
                    reservationView.getEndDate().getTime(),
                    reservationView.getParkingNumber(),
                    reservationView.getSecurityCode());
            reservationModel.addReservation(reservation);
            reservationView.showReservationSuccess();
            return true;
        }
        return false;
    }

    /**
     * Validators: check for error and exceptions
     */

    boolean areDatesValid() {
        Date startDate = reservationView.getStartDate();
        Date endDate = reservationView.getEndDate();

        if (startDate != null && endDate != null) {
            if (reservationModel.isDateInThePast(startDate) || reservationModel.isDateInThePast(endDate)) {
                reservationView.showErrorNoReservationInThePast();
                return false;
            } else if (reservationModel.isEndDateBeforeStartDate(endDate, startDate)) {
                reservationView.showBackToTheFuture();
                return false;
            } else {
                return true;
            }
        } else {
            reservationView.showMissingField();
            return false;
        }
    }

    public boolean isParkingNumberValid() {
        try {
            int parkingNumber = reservationView.getParkingNumber();
            if (parkingNumber <= reservationModel.getParking().getParkingSize()) {
                return true;
            } else {
                reservationView.showLargeNumber(parkingNumber);
                return false;
            }
        } catch (NumberFormatException e) {
            Log.e(ReservationPresenter.class.getSimpleName(), e.toString());
            reservationView.showInvalidNumber();
            return false;
        } catch (IllegalArgumentException ex) {
            Log.e(ReservationPresenter.class.getSimpleName(), ex.toString());
            reservationView.showZeroNotAccepted();
            return false;
        }
    }

    private boolean isSecurityCodeValid() {
        String securityCode = reservationView.getSecurityCode();

        if (securityCode != null) {
            if (securityCode.length() >= 3)
                return true;
            else {
                reservationView.showLargerThanThree();
                return false;
            }
        } else {
            reservationView.showMissingField();
            return false;
        }
    }
}

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

    public Parking getParkingWithReservations() {
        return reservationModel.getParking();
    }

    /**
     * Event Handlers
     */

    public void onFromClicked() {
        reservationView.startDateTimeDialog();
    }

    public void onToClicked() {
        reservationView.endDateTimeDialog();
    }

    public boolean onSchedule() {
        if (areDatesValid() && isParkingNumberValid() && isSecurityCodeValid()) {
            Reservation reservation = new Reservation(reservationView.getStartDate().getTime(),
                    reservationView.getEndDate().getTime(),
                    reservationModel.getParkingNumber(reservationView.getParkingNumber()),
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

        if (startDate == null || endDate == null) {
            reservationView.showMissingField();
            return false;
        }
        if (reservationModel.isDateInThePast(startDate) || reservationModel.isDateInThePast(endDate)) {
            reservationView.showErrorNoReservationInThePast();
            return false;
        } else if (reservationModel.isEndDateBeforeStartDate(startDate, endDate)) {
            reservationView.showBackToTheFuture();
            return false;
        }
        return true;
    }

    boolean isParkingNumberValid() {
        try {
            int parkingNumber = reservationModel.getParkingNumber(reservationView.getParkingNumber());
            int parkingSize = reservationModel.getParkingSize();
            if (parkingNumber <= parkingSize) {
                return true;
            } else {
                reservationView.showLargeNumber(parkingSize);
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

    boolean isSecurityCodeValid() {
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

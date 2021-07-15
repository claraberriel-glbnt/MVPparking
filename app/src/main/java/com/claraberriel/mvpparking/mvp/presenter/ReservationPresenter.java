package com.claraberriel.mvpparking.mvp.presenter;

import android.util.Log;

import androidx.annotation.VisibleForTesting;

import com.claraberriel.mvpparking.entities.Parking;
import com.claraberriel.mvpparking.entities.Reservation;
import com.claraberriel.mvpparking.mvp.model.ReservationModel;
import com.claraberriel.mvpparking.mvp.view.ReservationView;
import com.claraberriel.mvpparking.utilities.DateUtils;

import java.util.Date;

public class ReservationPresenter {
    @VisibleForTesting
    ReservationView reservationView;
    @VisibleForTesting
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
            reservationModel.addReservation(new Reservation(reservationView.getStartDate().getTime(),
                    reservationView.getEndDate().getTime(),
                    reservationModel.getParkingNumber(reservationView.getParkingNumber()),
                    reservationView.getSecurityCode()));
            reservationView.showReservationSuccess();
            return true;
        }
        return false;
    }

    /**
     * Validators: check for error and exceptions
     */

    @VisibleForTesting
    boolean areDatesValid() {
        Date startDate = reservationView.getStartDate();
        Date endDate = reservationView.getEndDate();

        if (startDate == null || endDate == null) {
            reservationView.showMissingField();
            return false;
        }
        if (DateUtils.isDateInThePast(startDate) || DateUtils.isDateInThePast(endDate)) {
            reservationView.showErrorNoReservationInThePast();
            return false;
        } else if (DateUtils.isEndDateBeforeStartDate(startDate, endDate)) {
            reservationView.showBackToTheFuture();
            return false;
        }
        return true;
    }

    @VisibleForTesting
    boolean isParkingNumberValid() {
        try {
            int parkingNumber = reservationModel.getParkingNumber(reservationView.getParkingNumber());
            int parkingSize = reservationModel.getParkingSize();
            if (parkingNumber > parkingSize) {
                reservationView.showLargeNumber(parkingSize);
                return false;
            }
            return true;
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

    @VisibleForTesting
    boolean isSecurityCodeValid() {
        String securityCode = reservationView.getSecurityCode();
        if (securityCode == null) {
            reservationView.showMissingField();
            return false;
        }
        if (securityCode.length() < 3) {
            reservationView.showLargerThanThree();
            return false;
        }
        return true;
    }

}

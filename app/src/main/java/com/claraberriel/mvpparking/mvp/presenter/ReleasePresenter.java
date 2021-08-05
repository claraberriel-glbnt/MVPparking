package com.claraberriel.mvpparking.mvp.presenter;

import android.util.Log;

import androidx.annotation.VisibleForTesting;

import com.claraberriel.mvpparking.entities.Parking;
import com.claraberriel.mvpparking.exceptions.ReservationListEmptyException;
import com.claraberriel.mvpparking.exceptions.ReservationMoreThanOneMatchException;
import com.claraberriel.mvpparking.exceptions.ReservationNotFoundException;
import com.claraberriel.mvpparking.mvp.model.ReleaseModel;
import com.claraberriel.mvpparking.mvp.view.ReleaseView;

public class ReleasePresenter {

    private static final int LENGTH = 3;
    private final ReleaseModel releaseModel;
    private final ReleaseView releaseView;

    public ReleasePresenter(ReleaseModel releaseModel, ReleaseView releaseView) {
        this.releaseModel = releaseModel;
        this.releaseView = releaseView;
    }

    /*
            Reservation reservation = new Reservation();
        int parkingNumber = releaseModel.getParkingNumber(releaseView.getParkingLotNumber());
        String securityCode = releaseView.getSecurityCode();

        if (validateSecurityCode(securityCode) && validateParkingLot(parkingNumber)) {
            reservation.setSecurityCode(securityCode);
            reservation.setParkingLot(parkingNumber);

            try{
                releaseModel.parkingRelease(reservation) ;
                releaseView.showSuccessMessageReleaseReservation();
                return true;
            } catch (ReservationListEmptyException ex){
                releaseView.showErrorReservationListEmptyException();
            } catch (ReleaseMoreThanOneMatchException ex){
                releaseView.showErrorReleaseMoreThanOneMatchException();
            }catch (ReleaseNoMatchException ex){
                releaseView.showErrorReleaseNoMatchException();
            }
        }
        return false;
    }

     */
    public boolean onRelease() {
        if (isParkingLotNumberValid() && isSecurityCodeValid()) {
            try {
                releaseModel.parkingRelease(releaseModel.getParkingLotNumber(releaseView.getParkingLotNumber()),
                        releaseView.getSecurityCode());
                releaseView.showReservationReleased();
                return true;
            } catch (ReservationListEmptyException emptyException) {
                releaseView.showErrorNoExistingReservations();
                return false;
            } catch (ReservationMoreThanOneMatchException matchException) {
                releaseView.showMoreThanOneReservation();
            } catch (ReservationNotFoundException notFoundException) {
                releaseView.showErrorParkingNumberSecurityCodeNotAMatch();
            }
        }
        return false;
    }

    public Parking getParkingWithReservations() {
        return releaseModel.getParking();
    }

    @VisibleForTesting
    boolean isParkingLotNumberValid() {
        try {
            releaseModel.getParkingLotNumber(releaseView.getParkingLotNumber());
            return true;
        } catch (NumberFormatException x) {
            Log.e(ReleasePresenter.class.getSimpleName(), x.toString());
            releaseView.showInvalidNumber();
            return false;
        } catch (IllegalArgumentException e) {
            Log.e(ReleasePresenter.class.getSimpleName(), e.toString());
            releaseView.showZeroNotAccepted();
            return false;
        }
    }

    @VisibleForTesting
    boolean isSecurityCodeValid() {
        String securityCode = releaseView.getSecurityCode();
        if (securityCode == null) {
            releaseView.showMissingField();
            return false;
        }
        if (securityCode.length() <= LENGTH) {
            releaseView.showLargerThanThree();
            return false;
        }
        return true;
    }
}

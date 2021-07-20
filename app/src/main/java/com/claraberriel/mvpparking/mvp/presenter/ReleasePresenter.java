package com.claraberriel.mvpparking.mvp.presenter;

import android.util.Log;

import androidx.annotation.VisibleForTesting;

import com.claraberriel.mvpparking.entities.Parking;
import com.claraberriel.mvpparking.mvp.model.ReleaseModel;
import com.claraberriel.mvpparking.mvp.view.ReleaseView;

public class ReleasePresenter {

    private final ReleaseModel releaseModel;
    private final ReleaseView releaseView;

    public ReleasePresenter(ReleaseModel releaseModel, ReleaseView releaseView) {
        this.releaseModel = releaseModel;
        this.releaseView = releaseView;
    }

    public boolean onRelease() {
        try {
            releaseModel.checkIfAnyReservationExists();
        } catch (IllegalArgumentException e) {
            releaseView.showErrorNoExistingReservations();
            return false;
        }
        if (releaseModel.checkIfAnyReservationExists()) {
            if (isParkingLotNumberValid() && isSecurityCodeValid()) {
                if (releaseModel.parkingRelease(releaseModel.getParkingLotNumber(releaseView.getParkingLotNumber()),
                        releaseView.getSecurityCode())) {
                    releaseView.showReservationReleased();
                    return true;
                } else {
                    releaseView.showErrorParkingNumberSecurityCodeNotAMatch();
                    return false;
                }
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
        if (securityCode.length() < 3) {
            releaseView.showLargerThanThree();
            return false;
        }
        return true;
    }
}

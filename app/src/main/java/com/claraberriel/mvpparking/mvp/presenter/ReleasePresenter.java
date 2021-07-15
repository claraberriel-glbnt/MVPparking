package com.claraberriel.mvpparking.mvp.presenter;

import com.claraberriel.mvpparking.entities.Parking;
import com.claraberriel.mvpparking.entities.Reservation;
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
        Reservation reservation = new Reservation();

        reservation.setParkingNumber(releaseModel.getParkingNumber(releaseView.getParkingLotNumber()));
        reservation.setSecurityCode(releaseView.getSecurityCode());

        if (releaseModel.parkingRelease(reservation)) {
            releaseView.showReservationReleased();
            return true;
        } else {
            releaseView.showErrorParkingNumberSecurityCodeNotAMatch();
            return false;
        }
    }

    public Parking getParkingWithReservations() {
        return releaseModel.getParking();
    }
}

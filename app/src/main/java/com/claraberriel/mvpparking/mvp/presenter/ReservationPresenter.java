package com.claraberriel.mvpparking.mvp.presenter;

import com.claraberriel.mvpparking.mvp.model.ReservationModel;
import com.claraberriel.mvpparking.mvp.view.ReservationView;
import com.claraberriel.mvpparking.utilities.DateUtils;

import java.util.Date;

public class ReservationPresenter extends DateUtils {

    private final ReservationView reservationView;
    private final ReservationModel reservationModel;

    public ReservationPresenter(ReservationView reservationView, ReservationModel reservationModel) {
        this.reservationView = reservationView;
        this.reservationModel = reservationModel;
    }

    public void onFrom(){
        reservationView.startDateTimeDialog();
    }

    public void onTo() {
        reservationView.endDateTimeDialog();
    }

    public void onParkingNumber() {

    }

    public void onSchedule() {
        Date startDate = reservationView.getStartDate();
        Date endDate = reservationView.getEndDate();

        if (!isDateInTheFuture(startDate) || !isDateInTheFuture(endDate)) {
            reservationView.showErrorNoReservationInThePast();
        }
        else if(isEndDateBeforeStartDate(endDate, startDate)){
            reservationView.showBackToTheFuture();
        }
        else if (startDate != null && endDate != null){
            reservationModel.setStartDateTime(startDate);
            reservationModel.setEndDateTime(endDate);
        }
        else {
            reservationView.showMissingField();
        }
    }
}

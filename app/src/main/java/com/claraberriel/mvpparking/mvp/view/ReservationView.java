package com.claraberriel.mvpparking.mvp.view;

import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.claraberriel.mvpparking.R;
import com.claraberriel.mvpparking.databinding.FragmentReservationBinding;
import com.claraberriel.mvpparking.utilities.Picker;

import java.util.Date;

public class ReservationView extends FragmentView {

    private final FragmentReservationBinding reservationBinding;
    private final Picker startDatePicker;
    private final Picker endDatePicker;

    public ReservationView(Fragment fragmentRef, FragmentReservationBinding reservationBinding) {
        super(fragmentRef);
        this.reservationBinding = reservationBinding;
        startDatePicker = new Picker(reservationBinding.startDateTime);
        endDatePicker = new Picker(reservationBinding.endDateTime);
    }

    /**
     * Show Date and Time Dialogs
     */
    public void startDateTimeDialog() {
        startDatePicker.show();
    }

    public void endDateTimeDialog() {
        endDatePicker.show();
    }

    /**
     * Getters @return values from EditTexts inputs
     */
    public Date getStartDate() {
        return startDatePicker != null ? startDatePicker.getDate() : null;
    }

    public Date getEndDate() {
        return endDatePicker != null ? endDatePicker.getDate() : null;
    }

    public String getParkingNumber() {
        return reservationBinding.parkingNumber.getText().toString();
    }

    public String getSecurityCode() {
        return reservationBinding.securityCode.getText().toString();
    }

    /**
     * Toast Messages @param message to be displayed
     */
    //reusable
    private void showToast(String message) {
        if (getContext() != null) {
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        }
    }

    public void showErrorNoReservationInThePast() {
        showToast((getContext()).getString(R.string.reservationview_err_msg_inthepast));
    }

    public void showMissingField() {
        showToast((getContext()).getString(R.string.reservationview_err_msg_missingfield));
    }

    public void showBackToTheFuture() {
        showToast((getContext()).getString(R.string.reservationview_err_msg_delorean));
    }

    public void showInvalidNumber() {
        showToast((getContext()).getString(R.string.reservationview_err_msg_numberformat));
    }

    public void showZeroNotAccepted() {
        showToast((getContext()).getString(R.string.reservationview_err_msg_zero));
    }

    public void showLargeNumber(int value) {
        showToast((getContext()).getString(R.string.reservationview_err_msg_large, value));
    }

    public void showLargerThanThree() {
        showToast((getContext()).getString(R.string.reservationview_err_msg_largerthan3));
    }

    public void showReservationSuccess() {
        showToast((getContext()).getString(R.string.reservationview_msg_success));
    }
}

package com.claraberriel.mvpparking.mvp.view;

import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.claraberriel.mvpparking.R;
import com.claraberriel.mvpparking.databinding.FragmentReservationBinding;
import com.claraberriel.mvpparking.utilities.Picker;

import java.util.Date;

public class ReservationView extends FragmentView {

    private final FragmentReservationBinding reservationBinding;
    private final Context context;
    private final Picker startDatePicker;
    private final Picker endDatePicker;
    
    public ReservationView(Fragment fragmentRef, FragmentReservationBinding reservationBinding) {
        super(fragmentRef);
        this.reservationBinding = reservationBinding;
        this.context = getContext();
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
     * Getters
     * @return values from EditTexts inputs
     */

    public Date getStartDate() {
        return startDatePicker != null ? startDatePicker.getDate() : null;
    }

    public Date getEndDate(){
        return endDatePicker != null ? endDatePicker.getDate() : null;
    }

    public int getParkingNumber() throws NumberFormatException {
            int result = Integer.parseInt(reservationBinding.parkingNumber.getText().toString());
            if (result <= 0) {
                throw new IllegalArgumentException();
            }
            return result;
    }

    public String getSecurityCode() {
        return reservationBinding.securityCode.getText().toString();
    }

    /**
     * Toast Messages
     * @param message to be displayed
     */

    //reusable
    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public void showErrorNoReservationInThePast() {
        if (context != null){
            showToast((context).getString(R.string.reservationview_err_msg_inthepast));
        }
    }

    public void showMissingField() {
        if (context != null){
            showToast((context).getString(R.string.reservationview_err_msg_missingfield));
        }
    }

    public void showBackToTheFuture() {
        if (context !=null){
            showToast((context).getString(R.string.reservationview_err_msg_delorean));
        }
    }

    public void showInvalidNumber() {
        if (context != null){
            showToast((context).getString(R.string.reservationview_err_msg_numberformat));
        }
    }

    public void showZeroNotAccepted() {
        if (context != null) {
            showToast((context).getString(R.string.reservationview_err_msg_zero));
        }
    }

    public void showLargeNumber(int value) {
        if (context != null) {
            showToast((context).getString(R.string.reservationview_err_msg_large, value));
        }
    }

    public void showLargerThanThree() {
        if (context != null){
            showToast((context).getString(R.string.reservationview_err_msg_largerthan3));
        }
    }
    
    public void showReservationSuccess() {
        if (context != null){
            showToast((context).getString(R.string.reservationview_msg_success));
        }
    }
}

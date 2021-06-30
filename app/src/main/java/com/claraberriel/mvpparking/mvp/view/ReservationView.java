package com.claraberriel.mvpparking.mvp.view;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.claraberriel.mvpparking.R;
import com.claraberriel.mvpparking.databinding.FragmentReservationBinding;
import com.claraberriel.mvpparking.utilities.Picker;

import java.util.Date;

public class ReservationView extends FragmentView {

    private FragmentReservationBinding reservationBinding;
    private EditText start_date_time;
    private EditText end_date_time;
    private EditText parkingNumber;
    private EditText securityCode;
    private Picker startDatePicker;
    private Picker endDatePicker;
    private Context context;


    public ReservationView(Fragment fragmentRef, FragmentReservationBinding reservationBinding) {
        super(fragmentRef);
        this.reservationBinding = reservationBinding;
        this.context = getContext();
    }

    /**
     * Show Date and Time Dialogs
     */

    public void startDateTimeDialog(){
        start_date_time = reservationBinding.startDateTime;
        startDatePicker = new Picker(start_date_time);
    }

    public void endDateTimeDialog() {
        end_date_time = reservationBinding.endDateTime;
        endDatePicker = new Picker(end_date_time);
    }

    /**
     * Getters
     * @return values from EditTexts inputs
     */
    public Date getStartDate() {
        return startDatePicker.getDate();
    }

    public Date getEndDate() {
        return endDatePicker.getDate();
    }

    public int getParkingNumber() throws NumberFormatException {
            parkingNumber = reservationBinding.parkingNumber;
            int result = Integer.parseInt(parkingNumber.getText().toString());
            if (result <= 0) {
                throw new IllegalArgumentException();
            }
            return result;
    }

    public String getSecurityCode() {
        securityCode = reservationBinding.securityCode;
        return securityCode.getText().toString();
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
}

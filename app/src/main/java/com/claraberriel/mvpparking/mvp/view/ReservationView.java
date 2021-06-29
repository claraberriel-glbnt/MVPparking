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
    private Picker startDatePicker;
    private Picker endDatePicker;
    private Context context;


    public ReservationView(Fragment fragmentRef, FragmentReservationBinding reservationBinding) {
        super(fragmentRef);
        this.reservationBinding = reservationBinding;
        this.context = getContext();
    }

    public void startDateTimeDialog(){
        start_date_time = reservationBinding.startDateTime;
        startDatePicker = new Picker(start_date_time);
    }

    public void endDateTimeDialog() {
        end_date_time = reservationBinding.endDateTime;
        endDatePicker = new Picker(end_date_time);
    }

    public Date getStartDate() {
        return startDatePicker.getDate();
    }

    public Date getEndDate() {
        return endDatePicker.getDate();
    }

    /**
     * Toast Messages
     * @param message to be displayed
     */

    //reusable
    public void showToast(String message) {
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
}

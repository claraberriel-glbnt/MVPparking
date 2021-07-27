package com.claraberriel.mvpparking.mvp.view;

import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.claraberriel.mvpparking.R;
import com.claraberriel.mvpparking.databinding.FragmentReleaseBinding;

public class ReleaseView extends FragmentView {

    private final FragmentReleaseBinding binding;

    public ReleaseView(Fragment fragmentRef, FragmentReleaseBinding binding) {
        super(fragmentRef);
        this.binding = binding;
    }

    public String getParkingLotNumber() {
        return binding.inputParkingLotNumber.getText().toString();
    }

    public String getSecurityCode() {
        return binding.inputSecurityCode.getText().toString();
    }

    //reusable
    private void showToast(int id) {
        if (getContext() != null) {
            Toast.makeText(getContext(), id, Toast.LENGTH_LONG).show();
        }
    }

    public void showErrorParkingNumberSecurityCodeNotAMatch() {
        showToast(R.string.release_view_err_msg_no_match);
    }

    public void showReservationReleased() {
        showToast(R.string.reslease_view_msg_success);
    }

    public void showInvalidNumber() {
        showToast(R.string.reservationview_err_msg_numberformat);
    }

    public void showZeroNotAccepted() {
        showToast(R.string.reservationview_err_msg_zero);
    }

    public void showMissingField() {
        showToast(R.string.reservationview_err_msg_missingfield);
    }

    public void showLargerThanThree() {
        showToast(R.string.reservationview_err_msg_largerthan3);
    }

    public void showErrorNoExistingReservations() {
        showToast(R.string.release_view_err_msg_no_reservation);
    }

    public void showMoreThanOneReservation() {
        showToast(R.string.err_msg_more_than_one_match);
    }
}

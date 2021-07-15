package com.claraberriel.mvpparking.mvp.view;

import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.claraberriel.mvpparking.R;
import com.claraberriel.mvpparking.databinding.FragmentReleaseBinding;

public class ReleaseView extends FragmentView {

    private final FragmentReleaseBinding binding;

    public ReleaseView(Fragment fragmentRef, FragmentReleaseBinding binding){
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
    private void showToast(String message) {
        if (getContext() != null) {
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        }
    }

    public void showErrorParkingNumberSecurityCodeNotAMatch() {
        showToast((getContext()).getString(R.string.release_view_err_msg_no_match));
    }

    public void showReservationReleased() {
        showToast((getContext()).getString(R.string.reslease_view_msg_success));
    }
}

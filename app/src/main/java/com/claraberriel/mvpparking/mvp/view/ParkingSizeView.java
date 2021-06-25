package com.claraberriel.mvpparking.mvp.view;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;


import com.claraberriel.mvpparking.R;
import com.claraberriel.mvpparking.databinding.ActivityMainBinding;

/**
 * Displays things.Handles user input and passes it to the Presenter.
 */

public class ParkingSizeView extends ParkingActivityView {
    private final ActivityMainBinding binding;

    public ParkingSizeView(Activity activity, ActivityMainBinding binding) {
        super(activity);
        this.binding = binding;
    }

    // input to int
    public int getParkingSize() throws NumberFormatException {
        EditText edittextNumber = binding.edittextMainNumber;
        int result = Integer.parseInt(edittextNumber.getText().toString());
        if (result <= 0){
            throw new IllegalArgumentException();
        }
        return result;
    }

    //reusable
    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    public void showParkAmount(int amount) {
        if (getContext() != null) {
            showToast((getContext()).getString(R.string.main_msg_toast_set, amount));
        }
    }

    public void showInvalidError() {
        if (getContext() != null) {
            showToast((getContext()).getString(R.string.main_error_show));
        }
    }

    public void showZeroNotAccepted() {
        if (getContext() != null) {
            showToast(getContext().getString(R.string.main_msg_nozero));
        }
    }

}

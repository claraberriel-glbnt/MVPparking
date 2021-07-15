package com.claraberriel.mvpparking.mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.claraberriel.mvpparking.R;
import com.claraberriel.mvpparking.activities.NavActivity;
import com.claraberriel.mvpparking.databinding.ActivityParkingSizeBinding;

import static com.claraberriel.mvpparking.activities.NavActivity.PARKING_SIZE_EXTRA;


/**
 * Displays things. Handles user input and passes it to the Presenter.
 */

public class ParkingSizeView extends ParkingActivityView {
    private final ActivityParkingSizeBinding binding;

    public ParkingSizeView(Activity activity, ActivityParkingSizeBinding binding) {
        super(activity);
        this.binding = binding;
    }

    // input to int
    public int getParkingSize() throws NumberFormatException {
        int result = Integer.parseInt(binding.edittextMainNumber.getText().toString());
        if (result <= 0) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    public void goToNavActivity(int size) {
        if (getContext() != null) {
            Intent intent = new Intent(getContext(), NavActivity.class);
            intent.putExtra(PARKING_SIZE_EXTRA, size);
            getContext().startActivity(intent);
        }
    }

    /**
     * Toast messages @param message to be displayed
     */

    //reusable
    private void showToast(String message) {
        if (getContext() != null) {
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        }
    }

    public void showParkAmount(int amount) {
            showToast((getContext()).getString(R.string.main_msg_toast_set, amount));
    }

    public void showInvalidError() {
            showToast((getContext()).getString(R.string.main_error_show));
    }

    public void showZeroNotAccepted() {
            showToast(getContext().getString(R.string.main_msg_nozero));
    }
}

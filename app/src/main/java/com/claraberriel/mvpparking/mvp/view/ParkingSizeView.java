package com.claraberriel.mvpparking.mvp.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.claraberriel.mvpparking.R;
import com.claraberriel.mvpparking.activities.NavActivity;
import com.claraberriel.mvpparking.databinding.ActivityParkingSizeBinding;

import static com.claraberriel.mvpparking.activities.NavActivity.SIZE_KEY;


/**
 * Displays things. Handles user input and passes it to the Presenter.
 */

public class ParkingSizeView extends ParkingActivityView {
    private final ActivityParkingSizeBinding binding;
    private Context context;

    public ParkingSizeView(Activity activity, ActivityParkingSizeBinding binding) {
        super(activity);
        this.binding = binding;
        this.context = getContext();
    }

    // input to int
    public int getParkingSize() throws NumberFormatException {
        EditText edittextNumber = binding.edittextMainNumber;
        int result = Integer.parseInt(edittextNumber.getText().toString());
        if (result <= 0) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    public void goToNavActivity(int size) {
        if (context != null) {
            Intent intent = new Intent(context, NavActivity.class);
            intent.putExtra(SIZE_KEY, size);
            context.startActivity(intent);
        }
    }

    /**
     * ----- Toast messages -----
     */

    //reusable
    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public void showParkAmount(int amount) {
        if (context != null) {
            showToast((getContext()).getString(R.string.main_msg_toast_set, amount));
        }
    }

    public void showInvalidError() {
        if (context != null) {
            showToast((getContext()).getString(R.string.main_error_show));
        }
    }

    public void showZeroNotAccepted() {
        if (context != null) {
            showToast(getContext().getString(R.string.main_msg_nozero));
        }
    }
}

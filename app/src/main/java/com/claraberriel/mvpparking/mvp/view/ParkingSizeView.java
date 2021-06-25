package com.claraberriel.mvpparking.mvp.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.claraberriel.mvpparking.R;
import com.claraberriel.mvpparking.activities.NavActivity;
import com.claraberriel.mvpparking.databinding.ActivityMainBinding;

/**
 * Displays things. Handles user input and passes it to the Presenter.
 */

public class ParkingSizeView extends ParkingActivityView {
    private final ActivityMainBinding binding;
    private Context context;

    public ParkingSizeView(Activity activity, ActivityMainBinding binding) {
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

/*    public void goToNavActivity(int size) {
        Intent intent = new Intent(context, NavActivity.class);
        if (context != null) {
            intent.putExtra("size key", size);
            context.startActivity(intent);
        }
    }

 */


    /**
     * ----- Toast messages -----
     */

    //reusable
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    public void showParkAmount(int amount) {
        if (context != null) {
            Intent intent = new Intent(context, NavActivity.class);
            intent.putExtra("size key", amount);
            showToast((getContext()).getString(R.string.main_msg_toast_set, amount));
            context.startActivity(intent);
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

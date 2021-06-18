package com.claraberriel.mvpparking.mvp.view;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

import com.claraberriel.mvpparking.R;
import com.claraberriel.mvpparking.databinding.ActivityMainBinding;

public class ParkingSizeView extends ParkingActivityView {

    private ActivityMainBinding binding;
    public EditText edittextNumber = binding.editTextNumber;

    public ParkingSizeView(Activity activity, ActivityMainBinding binding) {
        super(activity);
        this.binding = binding;
    }

    // input to int
    public int getEdittextNumber() throws NumberFormatException{
        int lots = Integer.parseInt(edittextNumber.getText().toString());
        return lots;
    }

    //reusable
    public void showToast(String msgName){
        Toast.makeText(getContext(), msgName, Toast.LENGTH_LONG).show();
    }

    public void showParkAmount(int amount){
        showToast(getContext().getString(R.string.toast_set, amount));
    }

    public void showInvalidError() {
      showToast(getContext().getString(R.string.show_error));
    }

  /*
  View

    displays things.
    handles user input and passes it to the Presenter.

   */
}

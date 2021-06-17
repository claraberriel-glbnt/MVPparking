package com.claraberriel.mvpparking.mvp.view;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

import com.claraberriel.mvpparking.R;
import com.claraberriel.mvpparking.databinding.ActivityMainBinding;

public class ParkingSize_view extends ParkingActivity_view{

    private ActivityMainBinding binding;
    public EditText number = binding.editTextNumber;

    public ParkingSize_view(Activity activity, ActivityMainBinding binding) {
        super(activity);
        this.binding = binding;
    }

    // input to int
    public int getNumber(){
        int lots = Integer.parseInt(number.getText().toString());
        return lots;
    }

    //reusable
    public void showToast(String msgName){
        Toast.makeText(getContext(), msgName, Toast.LENGTH_LONG).show();
    }

    public void showParkAmount(int amount){
        showToast(getContext().getString(R.string.toast_set, amount));
    }

  /*
  View

    displays things.
    handles user input and passes it to the Presenter.

   */
}

package com.claraberriel.mvpparking.mvp.view;

import android.app.Activity;

import com.claraberriel.mvpparking.databinding.ActivityMainBinding;

public class ParkingSize_view extends ParkingActivity_view{

    private final ActivityMainBinding binding;

    public ParkingSize_view(Activity activity, ActivityMainBinding binding) {
        super(activity);
        this.binding = binding;
    }

  /*  public void setCount(String count) {
        binding.countLabel.setText(count);
    }

   */
}

package com.claraberriel.mvpparking.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.claraberriel.mvpparking.databinding.ActivityMainBinding;
import com.claraberriel.mvpparking.mvp.model.ParkingModel;
import com.claraberriel.mvpparking.mvp.presenter.ParkingSizePresenter;
import com.claraberriel.mvpparking.mvp.view.ParkingSizeView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ParkingSizePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new ParkingSizePresenter(new ParkingModel(),
                new ParkingSizeView(this, binding));

        setListeners();
    }

    private void setListeners() {
        binding.buttonMainSubmit.setOnClickListener(view -> presenter.onSubmit());
    }

}
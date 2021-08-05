package com.claraberriel.mvpparking.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.claraberriel.mvpparking.databinding.ActivityParkingSizeBinding;
import com.claraberriel.mvpparking.mvp.model.ParkingModel;
import com.claraberriel.mvpparking.mvp.presenter.ParkingSizePresenter;
import com.claraberriel.mvpparking.mvp.view.ParkingSizeView;

public class ParkingSizeActivity extends AppCompatActivity {

    private ActivityParkingSizeBinding binding;
    private ParkingSizePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityParkingSizeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new ParkingSizePresenter(new ParkingModel(),
                new ParkingSizeView(this, binding));

        setListeners();
    }

    private void setListeners() {
        binding.buttonMainSubmit.setOnClickListener(view -> presenter.onSubmit());
    }
}
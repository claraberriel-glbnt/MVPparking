package com.claraberriel.mvpparking.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.claraberriel.mvpparking.databinding.ActivityParkingSizeBinding;
import com.claraberriel.mvpparking.entities.Parking;
import com.claraberriel.mvpparking.fragments.ReservationFragment;
import com.claraberriel.mvpparking.mvp.model.ParkingModel;
import com.claraberriel.mvpparking.mvp.presenter.NavPresenter;
import com.claraberriel.mvpparking.mvp.presenter.ParkingSizePresenter;
import com.claraberriel.mvpparking.mvp.view.ParkingSizeView;

public class ParkingSizeActivity extends AppCompatActivity implements ReservationFragment.ReservationFragmentDelegate {

    public static final String PARKING_SIZE_EXTRA = "SIZE_KEY";
    private ActivityParkingSizeBinding binding;
    private ParkingSizePresenter presenter;
    private NavPresenter navPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityParkingSizeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new ParkingSizePresenter(new ParkingModel(),
                new ParkingSizeView(this, binding));

        int parkingSize = getIntent().getIntExtra(PARKING_SIZE_EXTRA, 0);
        navPresenter = new NavPresenter(parkingSize);
        setListeners();
    }

    private void setListeners() {
        binding.buttonMainSubmit.setOnClickListener(view -> presenter.onSubmit());
    }

    @Override
    public void onReservationFragmentButtonClicked(Parking parking){
        navPresenter.setParking(parking);
    }
}
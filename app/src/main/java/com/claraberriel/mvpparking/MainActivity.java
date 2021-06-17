package com.claraberriel.mvpparking;

import android.os.Bundle;

import com.claraberriel.mvpparking.mvp.model.ParkingSize_model;
import com.claraberriel.mvpparking.mvp.presenter.ParkingSize_presenter;
import com.claraberriel.mvpparking.mvp.view.ParkingSize_view;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.claraberriel.mvpparking.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

   // private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private ParkingSize_presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       presenter = new ParkingSize_presenter(new ParkingSize_model(), new ParkingSize_view(this, binding));

       setListeners();
    }

    private void setListeners() {
        /*
        binding.countButton.setOnClickListener(view -> presenter.onCountButtonPressed());
        binding.resetButton.setOnClickListener(view -> presenter.onResetButtonPressed());
         */
    }

}
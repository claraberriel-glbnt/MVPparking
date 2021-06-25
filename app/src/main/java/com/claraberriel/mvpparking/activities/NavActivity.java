package com.claraberriel.mvpparking.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.claraberriel.mvpparking.R;
import com.claraberriel.mvpparking.databinding.ActivityNavigationBinding;
import com.claraberriel.mvpparking.fragments.ReleaseFragment;
import com.claraberriel.mvpparking.fragments.ReserveFragment;

public class NavActivity extends AppCompatActivity {

    private static final String RESERVE_FRAGMENT_TAG = "RESERVE_FRAGMENT";
    private static final String RELEASE_FRAGMENT_TAG = "RELEASE_FRAGMENT";
    private ActivityNavigationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle(R.string.main_title_parking_size);

        setListeners();
    }

    public void setListeners() {
        binding.buttonReservation.setOnClickListener(view -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new ReserveFragment(), RESERVE_FRAGMENT_TAG);
            transaction.commit();
        });

        binding.buttonRelease.setOnClickListener(view -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new ReleaseFragment(), RELEASE_FRAGMENT_TAG);
            transaction.commit();
        });
    }
}

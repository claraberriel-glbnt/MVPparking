package com.claraberriel.mvpparking.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.claraberriel.mvpparking.R;
import com.claraberriel.mvpparking.databinding.ActivityNavigationBinding;
import com.claraberriel.mvpparking.fragments.AutoReleaseFragment;
import com.claraberriel.mvpparking.fragments.ReleaseFragment;
import com.claraberriel.mvpparking.fragments.ReserveFragment;

public class NavActivity extends AppCompatActivity {

    private ActivityNavigationBinding binding;
    private static final String RESERVE_FRAGMENT_TAG = "RESERVE_FRAGMENT";
    private static final String RELEASE_FRAGMENT_TAG = "RELEASE_FRAGMENT";
    private static final String AUTO_RELEASE_FRAGMENT_TAG = "AUTO_RELEASE_FRAGMENT";


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
            FragmentTransaction transactionReserve = getSupportFragmentManager().beginTransaction();
            transactionReserve.replace(R.id.fragment_container, new ReserveFragment(), RESERVE_FRAGMENT_TAG);
            transactionReserve.commit();
        });

        binding.buttonRelease.setOnClickListener(view -> {
            FragmentTransaction transactionRelease = getSupportFragmentManager().beginTransaction();
            transactionRelease.replace(R.id.fragment_container, new ReleaseFragment(), RELEASE_FRAGMENT_TAG);
            transactionRelease.commit();
        });

        binding.buttonAutomaticRelease.setOnClickListener(view -> {
            FragmentTransaction transactionAuto = getSupportFragmentManager().beginTransaction();
            transactionAuto.replace(R.id.fragment_container, new AutoReleaseFragment(), AUTO_RELEASE_FRAGMENT_TAG);
            transactionAuto.commit();
        });
    }
}

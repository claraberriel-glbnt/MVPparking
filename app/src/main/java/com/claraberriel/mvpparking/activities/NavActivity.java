package com.claraberriel.mvpparking.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.claraberriel.mvpparking.R;
import com.claraberriel.mvpparking.databinding.ActivityNavigationBinding;
import com.claraberriel.mvpparking.entities.Parking;
import com.claraberriel.mvpparking.fragments.AutoReleaseFragment;
import com.claraberriel.mvpparking.fragments.ReleaseFragment;
import com.claraberriel.mvpparking.fragments.ReservationFragment;
import com.claraberriel.mvpparking.mvp.presenter.NavPresenter;

import static com.claraberriel.mvpparking.fragments.ReservationFragment.PARKING_KEY;

public class NavActivity extends AppCompatActivity implements ReservationFragment.ReservationFragmentDelegate, ReleaseFragment.ReleaseFragmentDelegate {

    public static final String PARKING_SIZE_EXTRA = "SIZE";
    private static final String RESERVE_FRAGMENT_TAG = "RESERVE_FRAGMENT";
    private static final String RELEASE_FRAGMENT_TAG = "RELEASE_FRAGMENT";
    private static final String AUTO_RELEASE_FRAGMENT_TAG = "AUTO_RELEASE_FRAGMENT";

    private ActivityNavigationBinding binding;
    private NavPresenter navPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle(R.string.main_title_parking_size);

        int parkingSize = getIntent().getIntExtra(PARKING_SIZE_EXTRA, 0);
        navPresenter = new NavPresenter(parkingSize);

        setListeners();
    }

    public void setListeners() {
        binding.buttonReservation.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable(PARKING_KEY, navPresenter.getParking());
            ReservationFragment reservationFragment = new ReservationFragment();
            reservationFragment.setArguments(bundle);
            FragmentTransaction transactionReserve = getSupportFragmentManager().beginTransaction();
            transactionReserve.replace(R.id.fragment_container, reservationFragment, RESERVE_FRAGMENT_TAG);
            transactionReserve.commit();
        });

        binding.buttonRelease.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable(PARKING_KEY, navPresenter.getParking());
            ReleaseFragment releaseFragment = new ReleaseFragment();
            releaseFragment.setArguments(bundle);
            FragmentTransaction transactionRelease = getSupportFragmentManager().beginTransaction();
            transactionRelease.replace(R.id.fragment_container, releaseFragment, RELEASE_FRAGMENT_TAG);
            transactionRelease.commit();
        });

        binding.buttonAutomaticRelease.setOnClickListener(view -> {
            FragmentTransaction transactionAuto = getSupportFragmentManager().beginTransaction();
            transactionAuto.replace(R.id.fragment_container, new AutoReleaseFragment(), AUTO_RELEASE_FRAGMENT_TAG);
            transactionAuto.commit();
        });
    }

    @Override
    public void onReservationFragmentButtonClicked(Parking parking){
        navPresenter.setParking(parking);
    }

    @Override
    public void onReleaseFragmentButtonClicked(Parking parking){
        navPresenter.setParking(parking);
    }
}

package com.claraberriel.mvpparking.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.claraberriel.mvpparking.databinding.FragmentReleaseBinding;
import com.claraberriel.mvpparking.entities.Parking;
import com.claraberriel.mvpparking.mvp.model.ReleaseModel;
import com.claraberriel.mvpparking.mvp.presenter.ReleasePresenter;
import com.claraberriel.mvpparking.mvp.view.ReleaseView;

public class ReleaseFragment extends Fragment {
    public static final String PARKING_ARGUMENT = "PARKING";
    private ReleaseFragmentDelegate delegate;
    private ReleasePresenter releasePresenter;
    private FragmentReleaseBinding fragmentReleaseBinding;


    public interface ReleaseFragmentDelegate {
        void onReleaseFragmentButtonClicked(Parking parking);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ReleaseFragmentDelegate) {
            delegate = (ReleaseFragmentDelegate) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentReleaseBinding = FragmentReleaseBinding.inflate(inflater, container, false);

        if(getArguments() != null) {
            Parking parking = getArguments().getParcelable(PARKING_ARGUMENT);
            releasePresenter = new ReleasePresenter(new ReleaseModel(parking), new ReleaseView(this, fragmentReleaseBinding));
        }
        setListeners();
        return fragmentReleaseBinding.getRoot();
    }

    private void setListeners() {
        fragmentReleaseBinding.btnRelease.setOnClickListener(view -> {
            if (releasePresenter.onRelease()) {
                delegate.onReleaseFragmentButtonClicked(releasePresenter.getParkingWithReservations());
            }
        });
    }
}

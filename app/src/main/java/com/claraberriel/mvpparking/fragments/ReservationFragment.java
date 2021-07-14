package com.claraberriel.mvpparking.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.claraberriel.mvpparking.databinding.FragmentReservationBinding;
import com.claraberriel.mvpparking.entities.Parking;
import com.claraberriel.mvpparking.mvp.model.ReservationModel;
import com.claraberriel.mvpparking.mvp.presenter.ReservationPresenter;
import com.claraberriel.mvpparking.mvp.view.ReservationView;

public class ReservationFragment extends Fragment {
    public static final String PARKING_KEY = "PARKING";
    private ReservationPresenter presenter;
    private FragmentReservationBinding binding;
    private ReservationFragmentDelegate delegate;

    public ReservationFragment() {
    }

    public interface ReservationFragmentDelegate {
        void onReservationFragmentButtonClicked(Parking parking);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ReservationFragmentDelegate) {
            delegate = (ReservationFragmentDelegate) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentReservationBinding.inflate(inflater, container, false);

        binding.startDateTime.setInputType(InputType.TYPE_NULL);
        binding.endDateTime.setInputType(InputType.TYPE_NULL);

        Parking parking = getArguments().getParcelable(PARKING_KEY);
        presenter = new ReservationPresenter(new ReservationView(this, binding), new ReservationModel(parking));

        setListeners();

        return binding.getRoot();
    }

    private void setListeners() {
        binding.startDateTime.setOnClickListener(view -> presenter.onFromClicked());
        binding.endDateTime.setOnClickListener(view -> presenter.onToClicked());
        binding.reserveBtnSchedule.setOnClickListener(view -> {
            if (presenter.onSchedule()) {
                delegate.onReservationFragmentButtonClicked(presenter.getParkingWithReservations());
            }
        });
    }
}

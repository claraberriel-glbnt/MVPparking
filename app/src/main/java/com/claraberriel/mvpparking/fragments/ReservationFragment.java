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
    public static final String PARKING_KEY  = "PARKING";
    private ReservationPresenter presenter;
    private FragmentReservationBinding binding;

    public ReservationFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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

    /*
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
     */

    private void setListeners() {
        binding.startDateTime.setOnClickListener(view -> presenter.onFrom());
        binding.endDateTime.setOnClickListener(view -> presenter.onTo());
        binding.reserveBtnSchedule.setOnClickListener(view -> presenter.onSchedule());
    }

    /**
     * ToDo setter in the fragment to instance model
     */
}

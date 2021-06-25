package com.claraberriel.mvpparking.mvp.view;

import androidx.fragment.app.Fragment;

import com.claraberriel.mvpparking.databinding.FragmentReservationBinding;

public class ReservationView extends FragmentView {

    private FragmentReservationBinding binding;

    public ReservationView(Fragment fragmentRef, FragmentReservationBinding binding) {
        super(fragmentRef);
        this.binding = binding;
    }
}

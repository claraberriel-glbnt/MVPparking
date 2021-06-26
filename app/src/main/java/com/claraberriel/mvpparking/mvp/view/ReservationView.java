package com.claraberriel.mvpparking.mvp.view;

import androidx.fragment.app.Fragment;

import com.claraberriel.mvpparking.databinding.FragmentReservationBinding;

public class ReservationView extends FragmentView {

    private FragmentReservationBinding reservationBinding;

    public ReservationView(Fragment fragmentRef, FragmentReservationBinding reservationBinding) {
        super(fragmentRef);
        this.reservationBinding = reservationBinding;
    }

}

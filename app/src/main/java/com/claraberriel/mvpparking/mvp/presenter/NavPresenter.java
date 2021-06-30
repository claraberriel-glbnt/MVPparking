package com.claraberriel.mvpparking.mvp.presenter;

import com.claraberriel.mvpparking.entities.Parking;

public class NavPresenter {

    private Parking parking;

    public NavPresenter(int parkingSize) {
        parking = new Parking(parkingSize);
    }

    public Parking getParking() {
        return parking;
    }
}

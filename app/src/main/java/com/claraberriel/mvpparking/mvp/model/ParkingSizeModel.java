package com.claraberriel.mvpparking.mvp.model;

import java.util.ArrayList;
import com.claraberriel.mvpparking.mvp.presenter.ParkingSizePresenter;

public class ParkingSizeModel {
    private final ParkingSizePresenter presenter;
    private ArrayList<Integer> parkingLots;

    public ParkingSizeModel() {
        this.parkingLots = new ArrayList<>();
        this.presenter = presenter;
    }

    public void setParkingLots() {
        int i = presenter.setParkingLotsLength();
        //setArrayLength
    }
}

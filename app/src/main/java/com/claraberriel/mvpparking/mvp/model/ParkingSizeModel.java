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

    public int setParkingLots() {
        int size = presenter.setParkingLotsLength();
        int i;
        //initializing ArrayList by adding the proper amount of elements
        for (i = 0, i <= size, i ++){
            parkingLots.add(0);
        }
    }
}

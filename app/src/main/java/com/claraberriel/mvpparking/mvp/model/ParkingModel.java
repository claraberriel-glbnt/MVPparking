package com.claraberriel.mvpparking.mvp.model;

import java.util.ArrayList;

public class ParkingModel {
    private int parkingSize;

    public ParkingModel() {
        ArrayList<Integer> parkingLots = new ArrayList<>();
    }

    public int getParkingSize() {
        return this.parkingSize;
    }

    public void setParkingSize(int size) {
        this.parkingSize = size;
    }
}
s
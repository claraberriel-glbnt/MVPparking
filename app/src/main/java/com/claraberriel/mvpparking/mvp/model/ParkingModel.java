package com.claraberriel.mvpparking.mvp.model;

public class ParkingModel {
    private int parkingSize;

    public ParkingModel() {
    }

    // I have another getParkingSize on the view that was renamed from getEditTextNumber
    // what is going on here?
    public int getParkingSize() {
        return this.parkingSize;
    }

    public void setParkingSize(int size) {
        this.parkingSize = size;
    }

}

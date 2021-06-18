package com.claraberriel.mvpparking.mvp.model;

import java.util.ArrayList;

public class ParkingModel {
    private int parkingSize;
    private ArrayList<Integer> parkingLots;

    public ParkingModel() {
        this.parkingLots = new ArrayList<>();
    }

    public int getParkingSize (){
        return this.parkingSize;
    }

    public void setParkingSize (int size){
        this.parkingSize = size;
    }
}

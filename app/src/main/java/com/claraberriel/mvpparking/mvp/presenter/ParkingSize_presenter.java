package com.claraberriel.mvpparking.mvp.presenter;

import android.view.View;
import android.widget.Toast;

import com.claraberriel.mvpparking.mvp.model.ParkingSize_model;
import com.claraberriel.mvpparking.mvp.view.ParkingSize_view;

public class ParkingSize_presenter {

    private ParkingSize_model model;
    private ParkingSize_view view;

    public ParkingSize_presenter(ParkingSize_model model, ParkingSize_view view) {
        this.model = model;
        this.view = view;
    }

    //Event handlers go here

    /*
    Presenter

    decides what to do with user input.
    gathers data from the model.
    tells the View what to do.

     */

}

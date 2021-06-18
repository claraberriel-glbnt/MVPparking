package com.claraberriel.mvpparking.mvp.presenter;

import android.util.Log;

import com.claraberriel.mvpparking.mvp.model.ParkingModel;
import com.claraberriel.mvpparking.mvp.view.ParkingSizeView;

public class ParkingSizePresenter {

    private final ParkingModel model;
    private final ParkingSizeView view;


    public ParkingSizePresenter(ParkingModel model, ParkingSizeView view) {
        this.model = model;
        this.view = view;
    }

    //Event handlers
    public void onSubmit() {
        try {
            view.showParkAmount(view.getEdittextNumber());
        } catch (NumberFormatException e) {
            Log.e(ParkingSizeView.class.getSimpleName(), e.toString());
            view.showInvalidError();
        }
        model.setParkingSize(view.getEdittextNumber());
        view.showParkAmount(model.getParkingSize());
    }

    /*
    Presenter

    decides what to do with user input.
    gathers data from the model.
    tells the View what to do.

     */

}

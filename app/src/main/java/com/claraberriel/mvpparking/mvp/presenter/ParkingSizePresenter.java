package com.claraberriel.mvpparking.mvp.presenter;

import android.util.Log;

import com.claraberriel.mvpparking.mvp.model.ParkingSizeModel;
import com.claraberriel.mvpparking.mvp.view.ParkingSizeView;

public class ParkingSizePresenter {

    private ParkingSizeModel model;
    private ParkingSizeView view;


    public ParkingSizePresenter(ParkingSizeModel model, ParkingSizeView view) {
        this.model = model;
        this.view = view;
    }

    public int setParkingLotsLength() {
        int length = view.getEdittextNumber();
        return length;
    }

    //Event handlers
    public void onSubmit(){
        try {
            view.showParkAmount(view.getEdittextNumber());
        }catch (NumberFormatException e){
            Log.e(ParkingSizeView.class.getSimpleName(), e.toString());
            view.showInvalidError();
        }
        model.setParkingLots();
    }

    /*
    Presenter

    decides what to do with user input.
    gathers data from the model.
    tells the View what to do.

     */

}

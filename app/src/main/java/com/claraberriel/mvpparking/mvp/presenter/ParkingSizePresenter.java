package com.claraberriel.mvpparking.mvp.presenter;

import android.util.Log;

import com.claraberriel.mvpparking.mvp.model.ParkingModel;
import com.claraberriel.mvpparking.mvp.view.ParkingSizeView;

/**
 * Decides what to do with user input. Gathers data from the model. Tells the View what to do.
 */

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
            model.setParkingSize(view.getParkingSize());
            view.showParkAmount(model.getParkingSize());
            view.goToNavActivity(model.getParkingSize());
        } catch (NumberFormatException e) {
            Log.e(ParkingSizePresenter.class.getSimpleName(), e.toString());
            view.showInvalidError();
        } catch (IllegalArgumentException ex) {
            Log.e(ParkingSizePresenter.class.getSimpleName(), ex.toString());
            view.showZeroNotAccepted();
        }
    }
}

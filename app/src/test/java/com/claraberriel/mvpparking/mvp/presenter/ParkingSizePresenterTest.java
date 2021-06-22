package com.claraberriel.mvpparking.mvp.presenter;

import com.claraberriel.mvpparking.mvp.model.ParkingModel;
import com.claraberriel.mvpparking.mvp.view.ParkingSizeView;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ParkingSizePresenterTest {
    private ParkingSizePresenter presenter;
    private ParkingModel model;
    private ParkingSizeView view;

    @Before
    public void setUp() {
        model = mock(ParkingModel.class);
        view = mock(ParkingSizeView.class);
        presenter = new ParkingSizePresenter(model, view);
    }

    @Test
    public void onSumbitClicked() {
        // Given certain size
        when(view.getParkingSize()).thenReturn(10);
        // When Submit button is pressed
        presenter.onSubmit();
        // Then check if message shown is correct and variable is set
        verify(view).showParkAmount(10);
        verify(model).setParkingSize(10);
    }

}
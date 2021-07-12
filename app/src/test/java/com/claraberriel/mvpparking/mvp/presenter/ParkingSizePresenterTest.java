package com.claraberriel.mvpparking.mvp.presenter;

import android.util.Log;

import com.claraberriel.mvpparking.mvp.model.ParkingModel;
import com.claraberriel.mvpparking.mvp.view.ParkingSizeView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ParkingSizePresenterTest {
    private ParkingSizePresenter presenter;
    private ParkingModel model;
    private ParkingSizeView view;
    private MockedStatic<Log> logMockedStatic;

    @Before
    public void setUp() {
        model = mock(ParkingModel.class);
        view = mock(ParkingSizeView.class);
        presenter = new ParkingSizePresenter(model, view);
        logMockedStatic = mockStatic(Log.class);
    }

    @Test
    public void onSubmit_validSize_showParkAmount() {
        // Given certain valid size
        when(view.getParkingSize()).thenReturn(10);
        // When Submit button is pressed
        presenter.onSubmit();
        // Then check if message shown is correct and variable is set
        verify(view).showParkAmount(10);
        verify(model).setParkingSize(10);
    }

    @Test
    public void onSubmit_invalidSize_showInvalidError() {
        // Given an out of range situation
        when(view.getParkingSize()).thenReturn(128);
        doThrow(new NumberFormatException()).when(model).setParkingSize(128);
        // When Submit button is pressed
        presenter.onSubmit();
        // Then throw error message
        verify(view).showInvalidError();
        verify(view, never()).showParkAmount(anyInt());
    }

    @Test
    public void onSubmit_zeroSize_showZeroNotAccepted() {
        // Given size equal or less than zero
        when(view.getParkingSize()).thenReturn(0);
        doThrow(new IllegalArgumentException()).when(model).setParkingSize(0);
        // When Submit button is pressed
        presenter.onSubmit();
        // Then show message
        verify(view).showZeroNotAccepted();
        verify(view, never()).showParkAmount(anyInt());
    }

    @After
    public void tearDown() {
        logMockedStatic.close();
    }
}
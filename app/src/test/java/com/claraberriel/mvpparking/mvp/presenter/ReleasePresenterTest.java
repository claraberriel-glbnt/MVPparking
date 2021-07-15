package com.claraberriel.mvpparking.mvp.presenter;

import android.util.Log;

import com.claraberriel.mvpparking.entities.Reservation;
import com.claraberriel.mvpparking.mvp.model.ReleaseModel;
import com.claraberriel.mvpparking.mvp.view.ReleaseView;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReleasePresenterTest {
    private ReleasePresenter releasePresenter;
    @Mock
    private ReleaseModel releaseModel;
    @Mock
    private ReleaseView releaseView;
    private MockedStatic<Log> logMockedStatic;

    @Before
    public void setUp(){
        releasePresenter = new ReleasePresenter(releaseModel, releaseView);
        logMockedStatic = mockStatic(Log.class);
    }

    @After
    public void tearDown(){
        logMockedStatic.close();
    }

    @Test //NPE
    public void onRelease_inputValidated_isTrue() {
        when(releaseModel.parkingRelease(new Reservation())).thenReturn(true);

        boolean release = releasePresenter.onRelease();

        verify(releaseView).showReservationReleased();
        Assert.assertTrue(release);
    }

    @Test
    public void getParkingWithReservations() {
    }
}
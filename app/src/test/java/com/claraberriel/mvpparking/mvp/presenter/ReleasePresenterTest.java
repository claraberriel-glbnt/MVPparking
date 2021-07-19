package com.claraberriel.mvpparking.mvp.presenter;

import android.util.Log;

import com.claraberriel.mvpparking.mvp.model.ReleaseModel;
import com.claraberriel.mvpparking.mvp.view.ReleaseView;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReleasePresenterTest {
    private ReleasePresenter releasePresenter;
    private ReleaseModel releaseModel;
    private ReleaseView releaseView;
    private MockedStatic<Log> logMockedStatic;

    @Before
    public void setUp(){
        releaseModel = mock(ReleaseModel.class);
        releaseView = mock(ReleaseView.class);
        releasePresenter = new ReleasePresenter(releaseModel, releaseView);
        logMockedStatic = mockStatic(Log.class);
    }

    @After
    public void tearDown(){
        logMockedStatic.close();
    }

    @Test
    public void onRelease_inputValidated_isTrue() {
        when(releaseModel.checkIfAnyReservationExists()).thenReturn(true);
        when(releaseView.getParkingLotNumber()).thenReturn("3");
        when(releaseModel.getParkingLotNumber("3")).thenReturn(3);
        when(releaseView.getSecurityCode()).thenReturn("code");
        when(releaseModel.parkingRelease(3, "code")).thenReturn(true);

        boolean release = releasePresenter.onRelease();

        verify(releaseView).showReservationReleased();
        Assert.assertTrue(release);
    }

}
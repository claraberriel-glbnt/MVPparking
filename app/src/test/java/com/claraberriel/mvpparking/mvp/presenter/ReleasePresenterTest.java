package com.claraberriel.mvpparking.mvp.presenter;

import android.util.Log;

import com.claraberriel.mvpparking.entities.Parking;
import com.claraberriel.mvpparking.excpetions.ReservationListEmptyException;
import com.claraberriel.mvpparking.mvp.model.ReleaseModel;
import com.claraberriel.mvpparking.mvp.view.ReleaseView;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.doThrow;
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
    public void onRelease_inputValidated_isTrue() throws ReservationListEmptyException {
        when(releaseView.getParkingLotNumber()).thenReturn("3");
        when(releaseModel.getParkingLotNumber("3")).thenReturn(3);
        when(releaseView.getSecurityCode()).thenReturn("code");
        when(releaseModel.parkingRelease(3, "code")).thenReturn(true);

        boolean release = releasePresenter.onRelease();

        verify(releaseView).showReservationReleased();
        Assert.assertTrue(release);
    }

    @Test
    public void onRelease_NotAMatch_isFalse() throws ReservationListEmptyException {
        when(releaseView.getParkingLotNumber()).thenReturn("3");
        when(releaseModel.getParkingLotNumber("3")).thenReturn(3);
        when(releaseView.getSecurityCode()).thenReturn("code");
        when(releaseModel.parkingRelease(3, "code")).thenReturn(false);

        boolean release = releasePresenter.onRelease();

        verify(releaseView).showErrorParkingNumberSecurityCodeNotAMatch();
        Assert.assertFalse(release);
    }

    @Test
    public void onRelease_exceptionNoExistingReservations_isFalse() {

        boolean release = releasePresenter.onRelease();

        verify(releaseView).showErrorNoExistingReservations();
        Assert.assertFalse(release);
    }

    /**
     * Validator Tests: success cases
     */

    @Test
    public void  isParkingLotNumberValid_isTrue(){
        when(releaseModel.getParking()).thenReturn(new Parking(20));
        when(releaseView.getParkingLotNumber()).thenReturn("15");

        boolean valid = releasePresenter.isParkingLotNumberValid();

        Assert.assertTrue(valid);
    }

    @Test
    public void  isSecurityCodeValid_isTrue(){
        when(releaseView.getSecurityCode()).thenReturn("sth");

        boolean valid = releasePresenter.isSecurityCodeValid();

        Assert.assertTrue(valid);
    }


    /**
     * Parking Number Validator: failure cases
     */

    @Test
    public void isParkingNumberValid_InvalidNumber_isFalse(){
        when(releaseModel.getParkingLotNumber("128")).thenReturn(128);
        when(releaseView.getParkingLotNumber()).thenReturn("128");
        doThrow(new NumberFormatException()).when(releaseModel).getParkingLotNumber("128");

        boolean valid = releasePresenter.isParkingLotNumberValid();

        verify(releaseView).showInvalidNumber();
        Assert.assertFalse(valid);
    }

    @Test
    public void  isParkingNumberValid_IllegalArgument_isFalse() {
        when(releaseModel.getParkingLotNumber("0")).thenReturn(0);
        when(releaseView.getParkingLotNumber()).thenReturn("0");
        doThrow(new IllegalArgumentException()).when(releaseModel).getParkingLotNumber("0");

        boolean valid = releasePresenter.isParkingLotNumberValid();

        verify(releaseView).showZeroNotAccepted();
        Assert.assertFalse(valid);
    }

    /**
     * Security Code Validator: failure cases
     */

    @Test
    public void isSecurityCodeValid_ShorterThanThree_isFalse(){
        when(releaseView.getSecurityCode()).thenReturn("sh");

        boolean valid = releasePresenter.isSecurityCodeValid();

        verify(releaseView).showLargerThanThree();
        Assert.assertFalse(valid);
    }

    @Test
    public void isSecurityCodeValid_Null_isFalse(){
        when(releaseView.getSecurityCode()).thenReturn(null);

        boolean valid = releasePresenter.isSecurityCodeValid();

        verify(releaseView).showMissingField();
        Assert.assertFalse(valid);
    }

}
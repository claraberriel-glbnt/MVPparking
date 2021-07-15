package com.claraberriel.mvpparking.mvp.presenter;

import com.claraberriel.mvpparking.entities.Parking;
import com.claraberriel.mvpparking.entities.Reservation;
import com.claraberriel.mvpparking.mvp.model.ReservationModel;
import com.claraberriel.mvpparking.mvp.view.ReservationView;
import com.claraberriel.mvpparking.utilities.DateUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.util.Calendar;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class ReservationPresenterTest {
    private ReservationPresenter presenter;
    @Mock
    private ReservationModel model;
    @Mock
    private ReservationView view;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        presenter = new ReservationPresenter(view, model);
    }

    /**
     * Event Handlers: success cases
     */

    @Test
    public void onFromClicked_showDialog(){
        presenter.onFromClicked();

        verify(view).startDateTimeDialog();
    }

    @Test
    public void onToClicked_showDialog(){
        presenter.onToClicked();

        verify(view).endDateTimeDialog();
    }

    @Test
    public void onSchedule_validatedData_addsReservation_isTrue(){
        ReservationPresenter spyPresenter = spy(presenter);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        Date endDate = calendar.getTime();

        when(model.getParkingSize()).thenReturn(10);
        when(view.getSecurityCode()).thenReturn("sd12");
        when(view.getParkingNumber()).thenReturn("2");
        when(view.getStartDate()).thenReturn(startDate);
        when(view.getEndDate()).thenReturn(endDate);

        doReturn(true).when(spyPresenter).areDatesValid();
        doReturn(true).when(spyPresenter).isParkingNumberValid();
        doReturn(true).when(spyPresenter).isSecurityCodeValid();

        when(model.addReservation(any(Reservation.class))).thenReturn(true);

        boolean success = presenter.onSchedule();

        verify(view).showReservationSuccess();
        Assert.assertTrue(success);
    }

    /**
     * Validator Tests: success cases
     */

    @Test
    public void areDatesValid_ValidDates_isTrue() {
        // When
        Date startDate = new Date();
        Date endDate = new Date();

        when(view.getStartDate()).thenReturn(startDate);
        when(view.getEndDate()).thenReturn(endDate);
        when(DateUtils.isDateInThePast(startDate)).thenReturn(false);
        when(DateUtils.isDateInThePast(endDate)).thenReturn(false);
        when(DateUtils.isEndDateBeforeStartDate(startDate, endDate)).thenReturn(false);

        boolean valid = presenter.areDatesValid();

        // Then
        Assert.assertTrue(valid);
    }

    @Test
    public void  isParkingNumberValid_isTrue(){
        when(model.getParking()).thenReturn(new Parking(20));
        when(view.getParkingNumber()).thenReturn("15");

        boolean valid = presenter.isParkingNumberValid();

        Assert.assertTrue(valid);
    }

    @Test
    public void  isSecurityCodeValid_isTrue(){
        when(view.getSecurityCode()).thenReturn("sth");

        boolean valid = presenter.isSecurityCodeValid();

        Assert.assertTrue(valid);
    }

    /**
     * OnSchedule Event: failure cases
     */

    @Test
    public void onSchedule_invalidDates_isFalse(){
        ReservationPresenter spyPresenter = spy(presenter);

        doReturn(false).when(spyPresenter).areDatesValid();
        doReturn(true).when(spyPresenter).isParkingNumberValid();
        doReturn(true).when(spyPresenter).isSecurityCodeValid();

        boolean success = spyPresenter.onSchedule();

        Assert.assertFalse(success);
    }

    @Test
    public void onSchedule_invalidParkingNumber_isFalse(){
        ReservationPresenter spyPresenter = spy(presenter);

        doReturn(true).when(spyPresenter).areDatesValid();
        doReturn(false).when(spyPresenter).isParkingNumberValid();
        doReturn(true).when(spyPresenter).isSecurityCodeValid();

        boolean success = spyPresenter.onSchedule();

        Assert.assertFalse(success);
    }

    @Test
    public void onSchedule_invalidSecurityCode_isFalse(){
        ReservationPresenter spyPresenter = spy(presenter);

        doReturn(true).when(spyPresenter).areDatesValid();
        doReturn(true).when(spyPresenter).isParkingNumberValid();
        doReturn(false).when(spyPresenter).isSecurityCodeValid();

        boolean success = spyPresenter.onSchedule();

        Assert.assertFalse(success);
    }

    /**
     * Dates Validator: failure cases
     */

    @Test
    public void areDatesValid_NullDates_isFalse() {
        // Given
        when(view.getStartDate()).thenReturn(null);
        when(view.getEndDate()).thenReturn(null);
        boolean valid = presenter.areDatesValid();

        // Then return false and show message
        verify(view).showMissingField();
        Assert.assertFalse(valid);
    }

    @Test
    public void areDatesValid_DatesInThePast_isFalse() {
        // When
        Date startDate = new Date();
        Date endDate = new Date();

        when(view.getStartDate()).thenReturn(startDate);
        when(view.getEndDate()).thenReturn(endDate);
        when(DateUtils.isDateInThePast(startDate)).thenReturn(true);
        when(DateUtils.isDateInThePast(endDate)).thenReturn(true);

        boolean valid = presenter.areDatesValid();

        // Then
        verify(view).showErrorNoReservationInThePast();
        Assert.assertFalse(valid);
    }

    @Test
    public void areDatesValid_EndDateBeforeStartDate_isFalse() {
        // When
        Date startDate = new Date();
        Date endDate = new Date();

        when(view.getStartDate()).thenReturn(startDate);
        when(view.getEndDate()).thenReturn(endDate);

        when(DateUtils.isDateInThePast(startDate)).thenReturn(false);
        when(DateUtils.isDateInThePast(endDate)).thenReturn(false);
        when(DateUtils.isEndDateBeforeStartDate(startDate, endDate)).thenReturn(true);

        boolean valid = presenter.areDatesValid();

        // Then
        verify(view).showBackToTheFuture();
        Assert.assertFalse(valid);
    }

    /**
     * Parking Number Validator: failure cases
     */

    @Test
    public void  isParkingNumberValid_GreaterThanParkingSize_isFalse(){
        when(view.getParkingNumber()).thenReturn("25");
        when(model.getParkingNumber("25")).thenReturn(25);
        when(model.getParkingSize()).thenReturn(20);

        boolean valid = presenter.isParkingNumberValid();

        verify(view).showLargeNumber(20);
        Assert.assertFalse(valid);
    }

    @Test
    public void isParkingNumberValid_InvalidNumber_isFalse(){
        when(model.getParkingNumber("128")).thenReturn(128);
        when(view.getParkingNumber()).thenReturn("128");
        when(model.getParkingSize()).thenReturn(129);
        doThrow(new NumberFormatException()).when(model).getParkingNumber("128");

        boolean valid = presenter.isParkingNumberValid();

        verify(view).showInvalidNumber();
        Assert.assertFalse(valid);
    }

    @Test
    public void  isParkingNumberValid_IllegalArgument_isFalse() {
        when(model.getParkingNumber("0")).thenReturn(0);
        when(view.getParkingNumber()).thenReturn("0");
        when(model.getParkingSize()).thenReturn(19);
        doThrow(new IllegalArgumentException()).when(model).getParkingNumber("0");

        boolean valid = presenter.isParkingNumberValid();

        verify(view).showZeroNotAccepted();
        Assert.assertFalse(valid);
    }

    /**
     * Security Code Validator: failure cases
     */

    @Test
    public void isSecurityCodeValid_ShorterThanThree_isFalse(){
        when(view.getSecurityCode()).thenReturn("sh");

        boolean valid = presenter.isSecurityCodeValid();

        verify(view).showLargerThanThree();
        Assert.assertFalse(valid);
    }

    @Test
    public void isSecurityCodeValid_Null_isFalse(){
        when(view.getSecurityCode()).thenReturn(null);

        boolean valid = presenter.isSecurityCodeValid();

        verify(view).showMissingField();
        Assert.assertFalse(valid);
    }
}

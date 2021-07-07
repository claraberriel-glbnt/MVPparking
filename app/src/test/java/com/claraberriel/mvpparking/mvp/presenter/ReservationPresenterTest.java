package com.claraberriel.mvpparking.mvp.presenter;

import com.claraberriel.mvpparking.mvp.model.ReservationModel;
import com.claraberriel.mvpparking.mvp.view.ReservationView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.verify;

public class ReservationPresenterTest {
    ReservationPresenter presenter;
    @Mock
    ReservationModel model;
    @Mock
    ReservationView view;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        presenter = new ReservationPresenter(view, model);
    }

    /**
     * Validators Tests: success cases
     */

    @Test
    public void areDatesValid_isTrue() {
        // Given dates after today that comply with conditions in line 66
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date endDate = calendar.getTime();

        // When method is called
        presenter.areDatesValid();

        // Then return true
        Assert.assertTrue(startDate != null && endDate != null);
        Assert.assertFalse(model.isDateInThePast(startDate) || model.isDateInThePast(endDate));
        Assert.assertFalse(model.isEndDateBeforeStartDate(endDate, startDate));
    }

    /**
     * Date Validator Tests: failure cases
     */

    @Test
    public void areDatesValid_isFalse_NullDates() {
        // Given dates values null that won't pass condition in line 59
        Date startDate = null;
        Date endDate = null;

        // When method is called
        presenter.areDatesValid();

        // Then return false and show message
        verify(view).showMissingField();
        Assert.assertFalse(startDate != null && endDate != null);
    }

    @Test
    public void areDatesValid_isFalse_StartDateNullEndDateValid() {
        // Given dates values null that won't pass condition in line 59
        Date startDate = null;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date endDate = calendar.getTime();

        // When method is called
        presenter.areDatesValid();

        // Then return false and show message
        verify(view).showMissingField();
        Assert.assertNotNull(endDate); //ToDo is this needed or helpful?
        Assert.assertFalse(startDate != null && endDate != null);
    }

    @Test
    public void areDatesValid_isFalse_StartDateValidEndDateNull() {
        // Given dates values null that won't pass condition in line 59
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date startDate = calendar.getTime();
        Date endDate = null;

        // When method is called
        presenter.areDatesValid();

        // Then return false and show message
        verify(view).showMissingField();
        Assert.assertNotNull(startDate); //ToDo is this needed or helpful?
        Assert.assertFalse(startDate != null && endDate != null);
    }

    @Test
    public void areDatesValid_isFalse_DatesInThePast() {
        // Given dates of today that enter condition in line 60
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 0);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 0);
        Date endDate = calendar.getTime();

        // When method is called
        presenter.areDatesValid();

        // Then return false and show message
        verify(view).showErrorNoReservationInThePast(); //ToDo "Wanted but not invoked" Test passed if line is commented
        Assert.assertNotNull(startDate);
        Assert.assertNotNull(endDate);
        Assert.assertTrue(model.isDateInThePast(startDate)); //ToDo Assertion error
        Assert.assertTrue(model.isDateInThePast(endDate));
    }

    @Test
    public void areDatesValid_isFalse_StartDateValidEndDateInThePast() {
        // Given dates of today that enter condition in line 60
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date endDate = calendar.getTime();

        // When method is called
        presenter.areDatesValid();

        // Then return false and show message
        verify(view).showErrorNoReservationInThePast(); //ToDo "Wanted but not invoked" Test passed if line is commented
        Assert.assertNotNull(startDate);
        Assert.assertNotNull(endDate);
        Assert.assertFalse(model.isDateInThePast(startDate));
        Assert.assertTrue(model.isDateInThePast(endDate)); //ToDo Assertion Error
    }

    @Test
    public void areDatesValid_isFalse_StartDateInThePastEndDateValid() {
        // Given dates of today that enter condition in line 60
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 0);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date endDate = calendar.getTime();

        // When method is called
        presenter.areDatesValid();

        // Then return false and show message
        verify(view).showErrorNoReservationInThePast(); //ToDo "Wanted but not invoked" Test passed if line is commented
        Assert.assertNotNull(startDate);
        Assert.assertNotNull(endDate);
        Assert.assertTrue(model.isDateInThePast(startDate)); //ToDo Assertion Error
        Assert.assertFalse(model.isDateInThePast(endDate));
    }

    @Test
    public void areDatesValid_isFalse_EndDateBeforeStartDate() {
        // Given dates after today that enter condition in line 63
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date endDate = calendar.getTime();

        // When method is called
        presenter.areDatesValid();

        // Then return false and show message
        verify(view).showBackToTheFuture(); //ToDo "Wanted but not invoked" Test passed if line is commented
        Assert.assertNotNull(startDate);
        Assert.assertNotNull(endDate);
        Assert.assertFalse(model.isDateInThePast(startDate));
        Assert.assertFalse(model.isDateInThePast(endDate));
        Assert.assertTrue(model.isEndDateBeforeStartDate(endDate, startDate)); // ToDo Assertion Error
    }
}
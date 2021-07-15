package com.claraberriel.mvpparking.mvp.model;

import com.claraberriel.mvpparking.entities.Parking;
import com.claraberriel.mvpparking.entities.Reservation;
import com.claraberriel.mvpparking.utilities.DateUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class ReservationModelTest {

    private ReservationModel model;

    @Before
    public void setUp() {
        Parking parking = new Parking(10);
        model = new ReservationModel(parking);
    }

    @Test
    public void getParkingNumber_validString_Valid() {
        Assert.assertEquals(5, model.getParkingNumber("5"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getParkingNumber_invalidChar_throwIllegalArgumentException() {
        model.getParkingNumber("A");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getParkingNumber_invalidNumber_throwIllegalArgumentException() {
        model.getParkingNumber("-1");
    }

    @Test
    public void addReservation_firstReservation_isTrue() {
        Assert.assertTrue(model.addReservation(new Reservation()));
    }

    @Test
    public void addReservation_secondReservation_isFalse() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        Date endDate = calendar.getTime();

        Assert.assertTrue(model.addReservation(new Reservation(startDate.getTime(), endDate.getTime(), 2, "bla")));
        Assert.assertFalse(model.addReservation(new Reservation(startDate.getTime(), endDate.getTime(), 2, "blah")));
    }

    @Test
    public void addReservation_secondReservationDifferentParkingNumber_isTrue() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date startDateJohn = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        Date endDateJohn = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date startDateDoe = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        Date endDateDoe = calendar.getTime();

        Assert.assertTrue(model.addReservation(new Reservation(startDateJohn.getTime(), endDateJohn.getTime(), 5, "john")));
        Assert.assertTrue(model.addReservation(new Reservation(startDateDoe.getTime(), endDateDoe.getTime(), 6, "doe")));
    }

    @Test
    public void addReservation_secondReservationStartDatelessThanFirstReservationEndDate_isFalse() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date startDateJohn = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 3);
        Date endDateJohn = calendar.getTime();

        Calendar calendarDoe = Calendar.getInstance();
        calendarDoe.add(Calendar.DAY_OF_YEAR, 1);
        Date startDateDoe = calendar.getTime();
        calendarDoe.add(Calendar.DAY_OF_YEAR, 2);
        Date endDateDoe = calendar.getTime();

        Assert.assertTrue(model.addReservation(new Reservation(startDateJohn.getTime(),
                endDateJohn.getTime(), 5, "john")));
        Assert.assertFalse(model.addReservation(new Reservation(startDateDoe.getTime(),
                endDateDoe.getTime(), 5, "doe")));
    }

    @Test
    public void isDateInThePast_isTrue() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 0);
        Date date = calendar.getTime();

        Assert.assertTrue(DateUtils.isDateInThePast(date));
    }

    @Test
    public void isDateInThePast_isFalse() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date date = calendar.getTime();

        Assert.assertFalse(DateUtils.isDateInThePast(date));
    }

    @Test
    public void isEndDateBeforeStartDate_isTrue() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date endDate = calendar.getTime();

        Assert.assertTrue(DateUtils.isEndDateBeforeStartDate(startDate, endDate));
    }

    @Test
    public void isEndDateBeforeStartDate_isFalse() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 0);
        Date endDate = calendar.getTime();

        Assert.assertFalse(DateUtils.isEndDateBeforeStartDate(startDate, endDate));
    }
}
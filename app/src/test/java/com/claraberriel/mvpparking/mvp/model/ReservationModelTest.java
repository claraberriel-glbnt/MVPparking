package com.claraberriel.mvpparking.mvp.model;

import com.claraberriel.mvpparking.entities.Parking;
import com.claraberriel.mvpparking.utilities.DateUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReservationModelTest {

    private ReservationModel model;

    @Before
    public void setUp(){
        Parking parking = new Parking(10);
        DateUtils dateUtils = new DateUtils();
        model = new ReservationModel(parking, dateUtils); //ToDo override local
    }

    @Test
    public void getParkingNumber_validString_Valid() {
        Assert.assertEquals(5, model.getParkingNumber("5"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void getParkingNumber_invalidChar_throwIllegalArgumentException() {
        model.getParkingNumber("A");
    }

    @Test (expected = IllegalArgumentException.class)
    public void getParkingNumber_invalidNumber_throwIllegalArgumentException() {
        model.getParkingNumber("-1");
    }

    @Test
    public void addReservation() {
    }

    @Test
    public void validateAddReservation() {
    }

    @Test
    public void isDateInThePast() {
    }

    @Test
    public void isEndDateBeforeStartDate() {
    }

    @Test
    public void getParkingSize() {
    }

    @Test
    public void getParking() {
    }
}
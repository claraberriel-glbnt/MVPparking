package com.claraberriel.mvpparking.mvp.model;

import com.claraberriel.mvpparking.entities.Parking;
import com.claraberriel.mvpparking.entities.Reservation;
import com.claraberriel.mvpparking.exceptions.ReservationListEmptyException;
import com.claraberriel.mvpparking.exceptions.ReservationMoreThanOneMatchException;
import com.claraberriel.mvpparking.exceptions.ReservationNotFoundException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class ReleaseModelTest {

    private ReleaseModel releaseModel;

    @Before
    public void setup() {
        Parking parking = new Parking(10);
        releaseModel = new ReleaseModel(parking);
    }

    @Test
    public void getParkingLotNumber_validString_ValidInt() {
        Assert.assertEquals(5, releaseModel.getParkingLotNumber("5"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getParkingLotNumber_invalidChar_throwIllegalArgumentException() {
        releaseModel.getParkingLotNumber("A");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getParkingLotNumber_invalidNumber_throwIllegalArgumentException() {
        releaseModel.getParkingLotNumber("-1");
    }

    @Test
    public void parkingRelease_reservationExists_isTrue() throws ReservationListEmptyException, ReservationMoreThanOneMatchException, ReservationNotFoundException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        Date endDate = calendar.getTime();

        Reservation reservation = new Reservation(startDate.getTime(),
                endDate.getTime(), 3, "code");

        Parking parking = new Parking(10);
        parking.getReservations().add(reservation);
        releaseModel = new ReleaseModel(parking);

        releaseModel.parkingRelease(3, "code");

        Assert.assertTrue(parking.getReservations().isEmpty());
    }

    @Test (expected = ReservationNotFoundException.class)
    public void parkingRelease_reservationNotEqual_isFalse() throws ReservationListEmptyException, ReservationMoreThanOneMatchException, ReservationNotFoundException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        Date endDate = calendar.getTime();

        Reservation reservation = new Reservation(startDate.getTime(),
                endDate.getTime(), 3, "code");

        Parking parking = new Parking(10);
        parking.getReservations().add(reservation);
        releaseModel = new ReleaseModel(parking);

        releaseModel.parkingRelease(4, "cod");

        Assert.assertFalse(parking.getReservations().isEmpty());
    }

    @Test (expected = ReservationListEmptyException.class)
    public void parkingRelease_reservationEmpty_isFalse() throws ReservationListEmptyException, ReservationMoreThanOneMatchException, ReservationNotFoundException {
        Parking parking = new Parking(10);
        releaseModel = new ReleaseModel(parking);

        releaseModel.parkingRelease(4, "cod");

        Assert.assertTrue(parking.getReservations().isEmpty());
    }

    @Test (expected = ReservationMoreThanOneMatchException.class)
    public void parkingRelease_reservationMoreThanOneMatch_isFalse() throws ReservationListEmptyException, ReservationMoreThanOneMatchException, ReservationNotFoundException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        Date endDate = calendar.getTime();

        Reservation reservation = new Reservation(startDate.getTime(),
                endDate.getTime(), 3, "code");

        Parking parking = new Parking(10);
        parking.getReservations().add(reservation);
        parking.getReservations().add(reservation);
        releaseModel = new ReleaseModel(parking);

        releaseModel.parkingRelease(3, "code");

        Assert.assertFalse(parking.getReservations().isEmpty());
    }
}
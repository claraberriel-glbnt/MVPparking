package com.claraberriel.mvpparking.mvp.model;

import com.claraberriel.mvpparking.entities.Parking;
import com.claraberriel.mvpparking.entities.Reservation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;

import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

public class ReleaseModelTest {

    private ReleaseModel releaseModel;
    @Mock
    private ReservationModel reservationModel;
    private MockedStatic<Parking> parkingMockedStatic;

    @Before
    public void setUp() {
        Parking parking = new Parking(10);
        releaseModel = new ReleaseModel(parking);
        parkingMockedStatic = mockStatic(Parking.class);
        reservationModel = mock(ReservationModel.class);
    }

    @After
    public void tearDown() throws Exception {
        parkingMockedStatic.close();
    }


    @Test
    public void parkingRelease_reservationExists_isTrue() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        Date endDate = calendar.getTime();

        when(releaseModel.getParking()).thenReturn(new Parking(10));
        Reservation reservation = new Reservation(startDate.getTime(), endDate.getTime(), 3, "code");
        when(reservationModel.addReservation(reservation));
        // getReservation


        boolean release = releaseModel.parkingRelease(reservation);

        Assert.assertTrue(release);
    }

    @Test
    public void getParkingNumber() {
    }
}
package com.claraberriel.mvpparking.entities;

import java.util.ArrayList;
import java.util.Date;

public class Reservation {
    private int parkingNumber;
    private Date startDateTime;
    private Date endDateTime;
    private String securityCode;

    public Reservation() {
        ArrayList<Integer> reservations = new ArrayList<>();
    }

    /**
     * ---- Setters ----
     */

    // I need to make condition if less or equal to parkingSize,
    // is this done here on the model or on the presenter?
    // should I use throws IllegalNumberException?
    public void setParkingNumber(int parkingNumber) {
        this.parkingNumber = parkingNumber;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    /**
     * Draft of setReservation() structure
     *
     * takes:
     * -Start Date and Time (ideally already "concatenated" (if string) or somehow together)
     * -End Date and Time (idem)
     * -Parking number
     * -Security code
     *
     * Then we need to compare with ArrayList reservation to check
     * that it doesn't overlap with a previous reservation.
     * This probably should be done in another method?
     */

}

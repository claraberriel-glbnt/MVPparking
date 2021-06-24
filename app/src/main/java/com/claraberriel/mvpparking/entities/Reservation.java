package com.claraberriel.mvpparking.entities;

import java.util.Date;

public class Reservation {
    private int parkingNumber;
    private Date startDateTime;
    private Date endDateTime;
    private String securityCode;

    /**
     * ---- Getters ----
     */

    public int getParkingNumber() {
        return parkingNumber;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    /**
     * ---- Setters ----
     */

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

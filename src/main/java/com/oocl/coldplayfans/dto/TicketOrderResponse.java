package com.oocl.coldplayfans.dto;


public class TicketOrderResponse {

    private String seatNumber;
    private String idNumber;

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public TicketOrderResponse(String seatNumber, String idNumber) {
        this.seatNumber = seatNumber;
        this.idNumber = idNumber;
    }
}

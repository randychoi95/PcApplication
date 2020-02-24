package com.ezen.MyPcApplication.Side_Navigation.Reservation;

import com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info.PC_Reservation.ReservationDTO;
public class Reservation_CheckDTO {
    private String seat;
    private String date;

    public Reservation_CheckDTO(){}

    public Reservation_CheckDTO(String seat, String date) {
        this.seat = seat;
        this.date = date;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

package com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info.PC_Reservation;

public class ReservationDTO {

    private String pcname;
    private String id;
    private String seat;
    private String date;
    private int isEmpty;
    private String phone;

    public ReservationDTO(){}

    public ReservationDTO(String seat, String date) {
        this.seat = seat;
        this.date = date;
    }

    public ReservationDTO(String pcname, String id, String seat, String date, int isEmpty, String phone) {
        this.pcname = pcname;
        this.id = id;
        this.seat = seat;
        this.date = date;
        this.isEmpty = isEmpty;
        this.phone = phone;
    }

    public String getPcname() {
        return pcname;
    }

    public void setPcname(String pcname) {
        this.pcname = pcname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(int isEmpty) {
        this.isEmpty = isEmpty;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

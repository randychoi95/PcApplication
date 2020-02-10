package com.ezen.mission10.Side_Navigation.Reservation;

public class ReservationCheckItem {
    String loginDate;
    String reservation_content;

    public ReservationCheckItem(String loginDate, String reservation_content) {
        this.loginDate = loginDate;
        this.reservation_content = reservation_content;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public String getReservation_content() {
        return reservation_content;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public void setReservation_content(String reservation_content) {
        this.reservation_content = reservation_content;
    }
}

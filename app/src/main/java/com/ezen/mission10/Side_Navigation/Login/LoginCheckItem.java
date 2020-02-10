package com.ezen.mission10.Side_Navigation.Login;

public class LoginCheckItem {
    String loginDate;
    String pcroomName;

    public LoginCheckItem(String loginDate, String pcroomName) {
        this.loginDate = loginDate;
        this.pcroomName = pcroomName;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public String getPcroomName() {
        return pcroomName;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public void setPcroomName(String pcroomName) {
        this.pcroomName = pcroomName;
    }
}

package com.ezen.mission10.Side_Navigation.Pay;

public class PayCheckItem {
    String loginDate;
    String pay_content;

    public PayCheckItem(String loginDate, String pay_content) {
        this.loginDate = loginDate;
        this.pay_content = pay_content;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public void setPay_content(String pay_content) {
        this.pay_content = pay_content;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public String getPay_content() {
        return pay_content;
    }
}

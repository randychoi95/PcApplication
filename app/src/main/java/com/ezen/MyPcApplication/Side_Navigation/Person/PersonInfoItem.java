package com.ezen.MyPcApplication.Side_Navigation.Person;

public class PersonInfoItem {
    String kind;
    String phone;
    String uid;

    public PersonInfoItem() {
    }

    public PersonInfoItem(String kind) {
        this.kind = kind;
    }

    public PersonInfoItem(String phone, String uid) {
        this.phone = phone;
        this.uid = uid;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

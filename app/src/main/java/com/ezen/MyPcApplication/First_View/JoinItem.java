package com.ezen.MyPcApplication.First_View;

public class JoinItem {

    String id;
    String phone;
    String uid;
    String name;

    public JoinItem() {
    }

    public JoinItem(String id, String phone, String uid, String name) {
        this.id = id;
        this.phone = phone;
        this.uid = uid;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

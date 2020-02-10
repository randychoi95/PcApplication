package com.ezen.mission10.After_Main.Find_Store_Tap;

// MVC 중 M(Model, Data)
public class StoreItem {
    private String name;
    private String street;
    private String address;

    public StoreItem(String name, String street, String address) {
        this.name = name;
        this.street = street;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

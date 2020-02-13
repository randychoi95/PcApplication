package com.ezen.MyPcApplication.After_Main.Find_Store_Tap;

// MVC 중 M(Model, Data)
public class StoreItem {
    String name; // 피시방 이름
    String latitude; // 피시방 위도
    String longitude; // 피시방 경도
    String address; // 피시방 주소
    String cpu;
    String ram;
    String vga;

    public StoreItem() {
    }

    public StoreItem(String name, String latitude, String longitude, String address) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    public StoreItem(String name, String latitude, String longitude, String address, String cpu, String ram, String vga) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.cpu = cpu;
        this.ram = ram;
        this.vga = vga;
    }

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getVga() {
        return vga;
    }

    public void setVga(String vga) {
        this.vga = vga;
    }
}

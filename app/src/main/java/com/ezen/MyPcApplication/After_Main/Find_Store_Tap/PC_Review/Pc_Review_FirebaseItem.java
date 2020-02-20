package com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Review;

// MVC 중 M(Model, Data)
public class Pc_Review_FirebaseItem {
    private String name;
    private String date;
    private String comments;
    private String paName;
    private int imageId;

    public Pc_Review_FirebaseItem() {
    }

    public Pc_Review_FirebaseItem(String name, String date, String comments, String paName, int imageId) {
        this.name = name;
        this.date = date;
        this.comments = comments;
        this.paName = paName;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPaName() {
        return paName;
    }

    public void setPaName(String paName) {
        this.paName = paName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}

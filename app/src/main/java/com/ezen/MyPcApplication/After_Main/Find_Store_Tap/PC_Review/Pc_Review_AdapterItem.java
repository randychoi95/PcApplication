package com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Review;

// MVC 중 M(Model, Data)
public class Pc_Review_AdapterItem {
    private String name;
    private String date;
    private String comments;
    private int imageId;

    public Pc_Review_AdapterItem() {
    }

    public Pc_Review_AdapterItem(String name, String date, String comments, int imageId) {
        this.name = name;
        this.date = date;
        this.comments = comments;
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}

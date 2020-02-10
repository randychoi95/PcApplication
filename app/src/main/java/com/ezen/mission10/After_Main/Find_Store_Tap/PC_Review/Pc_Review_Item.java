package com.ezen.mission10.After_Main.Find_Store_Tap.PC_Review;

// MVC 중 M(Model, Data)
public class Pc_Review_Item {
    private String id;
    private String date;
    private String comments;
    private int imageId;

    public Pc_Review_Item(String id, String date, String comments, int imageId) {
        this.id = id;
        this.date = date;
        this.comments = comments;
        this.imageId = imageId;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getComments() {
        return comments;
    }

    public int getImageId() {
        return imageId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}

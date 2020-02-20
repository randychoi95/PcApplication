package com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Review;

// MVC 중 M(Model, Data)
public class Pc_Review_uidItem {
    private String uid;
    private String name;
    private String pcName;

    public Pc_Review_uidItem() {
    }

    public Pc_Review_uidItem(String uid, String name, String pcName) {
        this.uid = uid;
        this.name = name;
        this.pcName = pcName;
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

    public String getPcName() {
        return pcName;
    }

    public void setPcName(String pcName) {
        this.pcName = pcName;
    }
}

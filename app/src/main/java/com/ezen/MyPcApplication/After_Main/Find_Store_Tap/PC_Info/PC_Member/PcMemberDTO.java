package com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info.PC_Member;

public class PcMemberDTO {

    private String name;
    private String id;
    private String pw;
    private String birth;
    private String phone;
    private String pcname;

    public PcMemberDTO(){}

    public PcMemberDTO(String name, String id, String pw, String birth, String phone, String pcname) {
        this.name = name;
        this.id = id;
        this.pw = pw;
        this.birth = birth;
        this.phone = phone;
        this.pcname = pcname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPcname() {
        return pcname;
    }

    public void setPcname(String pcname) {
        this.pcname = pcname;
    }
}

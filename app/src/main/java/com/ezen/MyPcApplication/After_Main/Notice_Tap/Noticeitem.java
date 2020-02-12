package com.ezen.MyPcApplication.After_Main.Notice_Tap;

public class Noticeitem {

    String title; // 공지사항 제목
    String content; // 공지사항 내용

    public Noticeitem(){

    }

    public Noticeitem(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

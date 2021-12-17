package com.nguyenvanhoa.app_th_android.Model;

public class ThongBao {
    private String dateTime, title;

    public ThongBao(String dateTime, String title) {
        this.dateTime = dateTime;
        this.title = title;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

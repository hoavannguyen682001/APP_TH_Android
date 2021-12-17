package com.nguyenvanhoa.app_th_android.Model;

public class Giaovien {
    private String ten_gv;

    private String mk_gv;

    public Giaovien(String ten_gv, String mk_gv) {
        this.ten_gv = ten_gv;
        this.mk_gv = mk_gv;
    }

    public String getTen_gv() {
        return ten_gv;
    }

    public void setTen_gv(String ten_gv) {
        this.ten_gv = ten_gv;
    }

    public String getMk_gv() {
        return mk_gv;
    }

    public void setMk_gv(String mk_gv) {
        this.mk_gv = mk_gv;
    }
}

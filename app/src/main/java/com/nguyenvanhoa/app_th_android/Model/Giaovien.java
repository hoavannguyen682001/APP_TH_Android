package com.nguyenvanhoa.app_th_android.Model;

public class Giaovien {
    private String ten_gv;
    private String ngaysinh_gv;
    private String Khoa;

    public Giaovien(String ten_gv, String ngaysinh_gv, String khoa) {
        this.ten_gv = ten_gv;
        this.ngaysinh_gv = ngaysinh_gv;
        this.Khoa = khoa;
    }

    public String getTen_gv() {
        return ten_gv;
    }

    public void setTen_gv(String ten_gv) {
        this.ten_gv = ten_gv;
    }

    public String getNgaysinh_gv() {
        return ngaysinh_gv;
    }

    public void setNgaysinh_gv(String ngaysinh_gv) {
        this.ngaysinh_gv = ngaysinh_gv;
    }

    public String getKhoa() {
        return Khoa;
    }

    public void setKhoa(String khoa) {
        Khoa = khoa;
    }
}

package com.nguyenvanhoa.app_th_android.Model;

import androidx.annotation.NonNull;

public class Sinhvien {

    private String tk_sinhvien;
    private String mk_sinhvien;

    public Sinhvien(String tk_sinhvien, String mk_sinhvien) {
        this.tk_sinhvien = tk_sinhvien;
        this.mk_sinhvien = mk_sinhvien;
    }

    public String getTk_sinhvien() {
        return tk_sinhvien;
    }

    public void setTk_sinhvien(String tk_sinhvien) {
        this.tk_sinhvien = tk_sinhvien;
    }

    public String getMk_sinhvien() {
        return mk_sinhvien;
    }

    public void setMk_sinhvien(String mk_sinhvien) {
        this.mk_sinhvien = mk_sinhvien;
    }
}

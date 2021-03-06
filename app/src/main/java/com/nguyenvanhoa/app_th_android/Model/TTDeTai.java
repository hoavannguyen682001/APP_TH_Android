package com.nguyenvanhoa.app_th_android.Model;

public class TTDeTai {
    private String tenDT, linhvuc, tenCNDT, tenTV, tenGV, tgThucHien, tgDK;
    private String chiPhi, trangThaiDT, uid;
    private Long id;

    public TTDeTai() {
    }

    public TTDeTai(Long id, String uid,String tenDT, String linhvuc, String tenCNDT, String tenTV, String tenGV, String tgThucHien, String tgDK, String chiPhi,String trangThaiDT) {
        this.tenDT = tenDT;
        this.linhvuc = linhvuc;
        this.tenCNDT = tenCNDT;
        this.tenTV = tenTV;
        this.tenGV = tenGV;
        this.tgThucHien = tgThucHien;
        this.tgDK = tgDK;
        this.chiPhi = chiPhi;
        this.trangThaiDT = trangThaiDT;
        this.id = id;
        this.uid = uid;
    }

    public TTDeTai(String tenDT, String tenCNDT,String tgDK) {
        this.tenDT = tenDT;
        this.tenCNDT = tenCNDT;
        this.tgDK = tgDK;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTrangThaiDT() {
        return trangThaiDT;
    }

    public void setTrangThaiDT(String trangThaiDT) {
        this.trangThaiDT = trangThaiDT;
    }

    public String getTenDT() {
        return tenDT;
    }

    public void setTenDT(String tenDT) {
        this.tenDT = tenDT;
    }

    public String getLinhvuc() {
        return linhvuc;
    }

    public void setLinhvuc(String linhvuc) {
        this.linhvuc = linhvuc;
    }

    public String getTenCNDT() {
        return tenCNDT;
    }

    public void setTenCNDT(String tenCNDT) {
        this.tenCNDT = tenCNDT;
    }

    public String getTenTV() {
        return tenTV;
    }

    public void setTenTV(String tenTV) {
        this.tenTV = tenTV;
    }

    public String getTenGV() {
        return tenGV;
    }

    public void setTenGV(String tenGV) {
        this.tenGV = tenGV;
    }

    public String getTgThucHien() {
        return tgThucHien;
    }

    public void setTgThucHien(String tgThucHien) {
        this.tgThucHien = tgThucHien;
    }

    public String getTgDK() {
        return tgDK;
    }

    public void setTgDK(String tgDK) {
        this.tgDK = tgDK;
    }

    public String getChiPhi() {
        return chiPhi;
    }

    public void setChiPhi(String chiPhi) {
        this.chiPhi = chiPhi;
    }
}

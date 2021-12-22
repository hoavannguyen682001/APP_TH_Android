package com.nguyenvanhoa.app_th_android.Model;

import androidx.annotation.NonNull;

public class Sinhvien {
    private String uid, email, name, password, profileImage,
            userType,dob,gender,khoa,nganh, lopSH;

    private Long timestamp;

    public Sinhvien() {
    }

    public Sinhvien(String uid, String email, String name, String password, String profileImage, String userType, String dob, String gender, String khoa, String nganh, String lopSH, Long timestamp) {
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.password = password;
        this.profileImage = profileImage;
        this.userType = userType;
        this.dob = dob;
        this.gender = gender;
        this.khoa = khoa;
        this.nganh = nganh;
        this.lopSH = lopSH;
        this.timestamp = timestamp;
    }

    public Sinhvien(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    public String getLopSH() {
        return lopSH;
    }

    public void setLopSH(String lopSH) {
        this.lopSH = lopSH;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}

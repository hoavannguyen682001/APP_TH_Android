package com.nguyenvanhoa.app_th_android.Model;

public class Giaovien {
    private String uid, email, name, password, profileImage,
            userType,phoneNumber,dob,CMND,khoa;
    private Long timestamp;

    public Giaovien() {
    }

    public Giaovien(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public Giaovien(String uid, String email, String name, String password, String profileImage, String userType, Long timestamp, String phoneNumber, String dob, String CMND, String khoa) {
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.password = password;
        this.profileImage = profileImage;
        this.userType = userType;
        this.timestamp = timestamp;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.CMND = CMND;
        this.khoa = khoa;
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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }
}

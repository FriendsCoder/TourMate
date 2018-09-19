package com.example.sabuj.tourmate.models;

public class User {
    private String image;
    private String fullName;
    private String userName;
    private String password;
    private String contactNo;
    private String address;


    public User() {
    }

    public User(String image, String fullName, String userName, String password, String contactNo, String address) {
        this.image = image;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.contactNo = contactNo;
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

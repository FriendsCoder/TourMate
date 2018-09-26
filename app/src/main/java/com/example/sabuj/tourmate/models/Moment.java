package com.example.sabuj.tourmate.models;

public class Moment {
    private String momentDate;
    private String momentTime;
    private String momentDetails;
    private String momentPhoto;

    public Moment() {
    }

    public Moment(String momentDate, String momentTime, String momentDetails, String momentPhoto) {
        this.momentDate = momentDate;
        this.momentTime = momentTime;
        this.momentDetails = momentDetails;
        this.momentPhoto = momentPhoto;
    }

    public String getMomentDate() {
        return momentDate;
    }

    public void setMomentDate(String momentDate) {
        this.momentDate = momentDate;
    }

    public String getMomentTime() {
        return momentTime;
    }

    public void setMomentTime(String momentTime) {
        this.momentTime = momentTime;
    }

    public String getMomentDetails() {
        return momentDetails;
    }

    public void setMomentDetails(String momentDetails) {
        this.momentDetails = momentDetails;
    }

    public String getMomentPhoto() {
        return momentPhoto;
    }

    public void setMomentPhoto(String momentPhoto) {
        this.momentPhoto = momentPhoto;
    }
}

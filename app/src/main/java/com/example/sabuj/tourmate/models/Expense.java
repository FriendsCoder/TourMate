package com.example.sabuj.tourmate.models;

public class Expense {
    private String currentDate;
    private String currentTime;
    private String item;
    private String price;

    public Expense() {
    }

    public Expense(String currentDate, String currentTime, String item, String price) {
        this.currentDate = currentDate;
        this.currentTime = currentTime;
        this.item = item;
        this.price = price;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

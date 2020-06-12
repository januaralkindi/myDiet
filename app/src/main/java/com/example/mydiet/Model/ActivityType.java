package com.example.mydiet.Model;

public class ActivityType {
    private double id;
    private String text;

    public ActivityType(Double id, String text) {
        this.id = id;
        this.text = text;
    }

    public Double getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

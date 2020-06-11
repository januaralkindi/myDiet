package com.example.mydiet.Model;

public class Recipe {
    private String title;
    private  String calories;
    private  String carbs;
    private  String image;
    private  String imageType;
    private  String fat;
    private  String id;
    private  String protein;

    public Recipe(String title, String calories, String carbs, String image, String imageType, String fat, String id, String protein) {
        this.title = title;
        this.calories = calories;
        this.carbs = carbs;
        this.image = image;
        this.imageType = imageType;
        this.fat = fat;
        this.id = id;
        this.protein = protein;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

}

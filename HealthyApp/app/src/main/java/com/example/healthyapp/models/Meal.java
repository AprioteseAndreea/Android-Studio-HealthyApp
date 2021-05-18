package com.example.healthyapp.models;

public class Meal {

    private String id;
    private String day;
    private String name;
    private String preptime;
    private String calories;
    private String imagePath;
    private String ingredients;
    private String howtoprepare;

    public Meal(String id, String day, String name, String preptime, String calories, String imagePath, String ingredients, String howtoprepare) {
        this.id = id;
        this.day=day;
        this.name = name;
        this.preptime = preptime;
        this.calories = calories;
        this.imagePath = imagePath;
        this.ingredients = ingredients;
        this.howtoprepare = howtoprepare;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPreptime() {
        return preptime;
    }

    public void setPreptime(String preptime) {
        this.preptime = preptime;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getHowtoprepare() {
        return howtoprepare;
    }

    public void setHowtoprepare(String howtoprepare) {
        this.howtoprepare = howtoprepare;
    }
}

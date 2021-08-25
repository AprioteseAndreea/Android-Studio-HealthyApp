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
    private String carbs;
    private String protein;
    private String fibre;
    private String fat;
    private Boolean isFavourite;

    public Meal(String id, String day, String name, String preptime, String calories, String imagePath, String ingredients, String howtoprepare, String carbs, String protein, String fibre, String fat, Boolean isFavourite) {
        this.id = id;
        this.day = day;
        this.name = name;
        this.preptime = preptime;
        this.calories = calories;
        this.imagePath = imagePath;
        this.ingredients = ingredients;
        this.howtoprepare = howtoprepare;
        this.carbs = carbs;
        this.protein = protein;
        this.fibre = fibre;
        this.fat = fat;
        this.isFavourite = isFavourite;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getFibre() {
        return fibre;
    }

    public void setFibre(String fibre) {
        this.fibre = fibre;
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

    public Boolean getFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
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

package com.example.healthyapp.models;

public class Workout {
    private String id;
    private String day;
    private String name;
    private String time;
    private String link;

    public Workout(String id, String day, String name, String time, String link) {
        this.id = id;
        this.day = day;
        this.name = name;
        this.time = time;
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

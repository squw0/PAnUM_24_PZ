package com.example.menu;

public class Location {
    private final String name;
    private final String address;
    private final String hours;
    private final int imageResourceId;

    public Location(String name, String address, String hours, int imageResourceId) {
        this.name = name;
        this.address = address;
        this.hours = hours;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getHours() {
        return hours;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
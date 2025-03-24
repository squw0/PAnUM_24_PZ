package com.example.menu;

public class Drink {
    private final String name;
    private final String description;
    private final int imageResourceId;

    public static final Drink[] drinks = {
            new Drink("Latte", "Espresso z gorącym mlekiem", R.drawable.latte),
            new Drink("Cappuccino", "Espresso z mleczną pianką", R.drawable.cappuccino),
            new Drink("Espresso", "Mocna, czarna kawa", R.drawable.espresso)
    };

    private Drink(String name, String description, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String toString() {
        return this.name;
    }
}

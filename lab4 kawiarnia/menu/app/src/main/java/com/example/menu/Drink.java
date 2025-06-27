package com.example.menu;

public class Drink {
    private final String name;
    private final String description;
    private final String price;
    private final int imageResourceId;

//    public static final Drink[] drinks = {
//            new Drink("Latte", "Espresso z gorącym mlekiem", R.drawable.latte, "10"),
//            new Drink("Cappuccino", "Espresso z mleczną pianką", R.drawable.cappuccino, "10"),
//            new Drink("Espresso", "Mocna, czarna kawa", R.drawable.espresso, "10")
//    };

    public Drink(String name, String description, int imageResourceId, String price) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String toString() {
        return this.name;
    }
}

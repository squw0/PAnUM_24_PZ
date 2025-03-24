package com.example.menu;

public class Snack {
    private final String name;
    private final String description;
    private final int imageResourceId;

    public static final Snack[] snacks = {
            new Snack("Croissant", "Maślany i chrupiący francuski rogalik, idealny do kawy.", R.drawable.croissant),
            new Snack("Brownie", "Wilgotne, czekoladowe ciasto z chrupiącą skórką.", R.drawable.brownie),
            new Snack("Bagietka z serem", "Świeża bagietka zapiekana z serem i ziołami.", R.drawable.bagietka)
    };

    private Snack(String name, String description, int imageResourceId) {
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

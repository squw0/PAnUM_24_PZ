
package com.example.menu;

public class CartItem {
    private final String name;
    private final String description;
    private final String price;
    private final int imageResourceId;
    private final int quantity;

    public CartItem(String name, String description, String price, int imageResourceId, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResourceId = imageResourceId;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getPrice() { return price; }
    public int getImageResourceId() { return imageResourceId; }
    public int getQuantity() { return quantity; }

    public String getTotalPrice() {
        try {
            double priceValue = Double.parseDouble(price);
            return String.valueOf(priceValue * quantity);
        } catch (NumberFormatException e) {
            return "0";
        }
    }
}
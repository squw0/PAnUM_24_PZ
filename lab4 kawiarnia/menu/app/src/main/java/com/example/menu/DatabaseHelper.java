package com.example.menu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "kafeteria.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_DRINKS = "drinks";
    public static final String TABLE_SNACKS = "snacks";
    public static final String TABLE_LOCATIONS = "locations";
    public static final String TABLE_CART = "cart";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_HOURS = "hours";
    public static final String COLUMN_QUANTITY = "quantity";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDrinksTable = "CREATE TABLE " + TABLE_DRINKS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_PRICE + " TEXT, " +
                COLUMN_IMAGE + " INTEGER)";
        db.execSQL(createDrinksTable);

        String createSnacksTable = "CREATE TABLE " + TABLE_SNACKS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_PRICE + " TEXT, " +
                COLUMN_IMAGE + " INTEGER)";
        db.execSQL(createSnacksTable);

        String createLocationsTable = "CREATE TABLE " + TABLE_LOCATIONS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_HOURS + " TEXT, " +
                COLUMN_IMAGE + " INTEGER)";
        db.execSQL(createLocationsTable);

        String createCartTable = "CREATE TABLE " + TABLE_CART + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_PRICE + " TEXT, " +
                COLUMN_IMAGE + " INTEGER, " +
                COLUMN_QUANTITY + " INTEGER)";
        db.execSQL(createCartTable);

        db.execSQL("CREATE TABLE IF NOT EXISTS orders (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "customer_name TEXT, " +
                "phone TEXT, " +
                "pickup_time TEXT, " +
                "location TEXT, " +
                "total REAL, " +
                "order_date INTEGER)");

        db.execSQL("CREATE TABLE IF NOT EXISTS order_items (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "order_id INTEGER, " +
                "item_name TEXT, " +
                "price TEXT, " +
                "quantity INTEGER)");

        insertInitialData(db);
    }

    private void insertInitialData(SQLiteDatabase db) {
        ContentValues latte = new ContentValues();
        latte.put(COLUMN_NAME, "Latte");
        latte.put(COLUMN_DESCRIPTION, "Espresso z gorącym mlekiem");
        latte.put(COLUMN_PRICE, "10");
        latte.put(COLUMN_IMAGE, R.drawable.latte);
        db.insert(TABLE_DRINKS, null, latte);

        ContentValues cappuccino = new ContentValues();
        cappuccino.put(COLUMN_NAME, "Cappuccino");
        cappuccino.put(COLUMN_DESCRIPTION, "Espresso z mleczną pianką");
        cappuccino.put(COLUMN_PRICE, "10");
        cappuccino.put(COLUMN_IMAGE, R.drawable.cappuccino);
        db.insert(TABLE_DRINKS, null, cappuccino);

        ContentValues espresso = new ContentValues();
        espresso.put(COLUMN_NAME, "Espresso");
        espresso.put(COLUMN_DESCRIPTION, "Mocna, czarna kawa");
        espresso.put(COLUMN_PRICE, "10");
        espresso.put(COLUMN_IMAGE, R.drawable.espresso);
        db.insert(TABLE_DRINKS, null, espresso);

        ContentValues croissant = new ContentValues();
        croissant.put(COLUMN_NAME, "Croissant");
        croissant.put(COLUMN_DESCRIPTION, "Maślany i chrupiący francuski rogalik, idealny do kawy.");
        croissant.put(COLUMN_PRICE, "10");
        croissant.put(COLUMN_IMAGE, R.drawable.croissant);
        db.insert(TABLE_SNACKS, null, croissant);

        ContentValues brownie = new ContentValues();
        brownie.put(COLUMN_NAME, "Brownie");
        brownie.put(COLUMN_DESCRIPTION, "Wilgotne, czekoladowe ciasto z chrupiącą skórką.");
        brownie.put(COLUMN_PRICE, "10");
        brownie.put(COLUMN_IMAGE, R.drawable.brownie);
        db.insert(TABLE_SNACKS, null, brownie);

        ContentValues bagietka = new ContentValues();
        bagietka.put(COLUMN_NAME, "Bagietka z serem");
        bagietka.put(COLUMN_DESCRIPTION, "Świeża bagietka zapiekana z serem i ziołami.");
        bagietka.put(COLUMN_PRICE, "10");
        bagietka.put(COLUMN_IMAGE, R.drawable.bagietka);
        db.insert(TABLE_SNACKS, null, bagietka);

        ContentValues location = new ContentValues();
        location.put(COLUMN_NAME, "Kot Kafeteria");
        location.put(COLUMN_ADDRESS, "ANMP 4, Częstochowa");
        location.put(COLUMN_HOURS, "Pon-Pt: 8:00-20:00\nSob-Ndz: 9:00-18:00");
        location.put(COLUMN_IMAGE, R.drawable.map);
        db.insert(TABLE_LOCATIONS, null, location);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRINKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SNACKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(db);
    }

    public List<Drink> getAllDrinks() {
        List<Drink> drinks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DRINKS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Drink drink = new Drink(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IMAGE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRICE))
                );
                drinks.add(drink);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return drinks;
    }

    public List<Snack> getAllSnacks() {
        List<Snack> snacks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SNACKS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Snack snack = new Snack(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IMAGE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRICE))
                );
                snacks.add(snack);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return snacks;
    }

    public Location getLocation() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_LOCATIONS, null, null, null, null, null, null);

        Location location = null;
        if (cursor.moveToFirst()) {
            location = new Location(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HOURS)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IMAGE))
            );
        }
        cursor.close();
        return location;
    }

    public void addToCart(Drink drink, int quantity) {
        addItemToCart(drink.getName(), drink.getDescription(), drink.getPrice(), drink.getImageResourceId(), quantity);
    }

    public void addToCart(Snack snack, int quantity) {
        addItemToCart(snack.getName(), snack.getDescription(), snack.getPrice(), snack.getImageResourceId(), quantity);
    }

    private void addItemToCart(String name, String description, String price, int image, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_CART,
                new String[]{COLUMN_ID, COLUMN_QUANTITY},
                COLUMN_NAME + "=?",
                new String[]{name},
                null, null, null);

        if (cursor.moveToFirst()) {
            int existingQuantity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY));
            int newQuantity = existingQuantity + quantity;
            ContentValues values = new ContentValues();
            values.put(COLUMN_QUANTITY, newQuantity);
            db.update(TABLE_CART, values, COLUMN_NAME + "=?", new String[]{name});
        } else {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, name);
            values.put(COLUMN_DESCRIPTION, description);
            values.put(COLUMN_PRICE, price);
            values.put(COLUMN_IMAGE, image);
            values.put(COLUMN_QUANTITY, quantity);
            db.insert(TABLE_CART, null, values);
        }
        cursor.close();
    }

    public List<CartItem> getCartItems() {
        List<CartItem> cartItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CART, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                CartItem item = new CartItem(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRICE)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IMAGE)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY))
                );
                cartItems.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return cartItems;
    }

    public void clearCart() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART, null, null);
    }

    public void removeFromCart(String itemName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART, COLUMN_NAME + "=?", new String[]{itemName});
    }

    public long saveOrder(String customerName, String phone, String pickupTime, String location, double total) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("customer_name", customerName);
        values.put("phone", phone);
        values.put("pickup_time", pickupTime);
        values.put("location", location);
        values.put("total", total);
        values.put("order_date", System.currentTimeMillis());

        try {
            return db.insert("orders", null, values);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean saveOrderItem(long orderId, String itemName, String price, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("order_id", orderId);
        values.put("item_name", itemName);
        values.put("price", price);
        values.put("quantity", quantity);

        return db.insert("order_items", null, values) != -1;
    }

    public List<Location> getAllLocations() {
        List<Location> locations = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_LOCATIONS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Location location = new Location(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HOURS)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IMAGE))
                );
                locations.add(location);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return locations;
    }
}
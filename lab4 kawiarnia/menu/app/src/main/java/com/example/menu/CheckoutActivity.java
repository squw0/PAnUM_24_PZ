package com.example.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CheckoutActivity extends AppCompatActivity {

    private double total;
    private List<CartItem> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        cartItems = dbHelper.getCartItems();
        List<Location> locations = dbHelper.getAllLocations();

        EditText nameInput = findViewById(R.id.nameInput);
        EditText phoneInput = findViewById(R.id.phoneInput);
        EditText pickupTime = findViewById(R.id.pickupTime);
        TextView orderSummary = findViewById(R.id.orderSummary);
        TextView totalPrice = findViewById(R.id.totalPrice);
        Spinner locationSpinner = findViewById(R.id.locationSpinner);
        Button confirmOrderButton = findViewById(R.id.confirmOrderButton);

        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        pickupTime.setText(currentTime);

        total = calculateTotal(cartItems);

        orderSummary.setText(buildOrderSummary(cartItems));
        totalPrice.setText(String.format("Suma: %.2f zł", total));

        ArrayAdapter<Location> locationAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, locations);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);

        confirmOrderButton.setOnClickListener(v -> processOrder(
                dbHelper,
                nameInput.getText().toString().trim(),
                phoneInput.getText().toString().trim(),
                pickupTime.getText().toString().trim(),
                (Location) locationSpinner.getSelectedItem()
        ));
    }

    private double calculateTotal(List<CartItem> items) {
        double sum = 0;
        for (CartItem item : items) {
            try {
                sum += Double.parseDouble(item.getPrice()) * item.getQuantity();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }

    private String buildOrderSummary(List<CartItem> items) {
        StringBuilder summary = new StringBuilder();
        for (CartItem item : items) {
            summary.append(item.getQuantity()).append(" × ")
                    .append(item.getName()).append(" - ")
                    .append(item.getTotalPrice()).append(" zł\n");
        }
        return summary.toString();
    }

    private void processOrder(DatabaseHelper dbHelper, String name, String phone,
                              String time, Location location) {
        if (!validateInput(name, phone, time)) {
            return;
        }

        long orderId = dbHelper.saveOrder(name, phone, time, location.getName(), total);
        if (orderId == -1) {
            Toast.makeText(this, "Błąd podczas zapisywania zamówienia", Toast.LENGTH_SHORT).show();
            return;
        }

        for (CartItem item : cartItems) {
            dbHelper.saveOrderItem(orderId, item.getName(), item.getPrice(), item.getQuantity());
        }

        dbHelper.clearCart();

        String confirmation = buildConfirmation(orderId, name, phone, location.getName(), time, total);

        Intent intent = new Intent(this, OrderConfirmationActivity.class);
        intent.putExtra("CONFIRMATION_TEXT", confirmation);
        startActivity(intent);
        finish();
    }

    private String buildConfirmation(long orderId, String name, String phone,
                                     String location, String time, double total) {
        return String.format(
                "Zamówienie #%d potwierdzone!\n\n" +
                        "Dla: %s\n" +
                        "Telefon: %s\n" +
                        "Lokalizacja: %s\n" +
                        "Czas odbioru: %s\n" +
                        "Kwota: %.2f zł",
                orderId, name, phone, location, time, total
        );
    }

    private boolean validateInput(String name, String phone, String time) {
        if (name.isEmpty()) {
            Toast.makeText(this, "Wprowadź imię i nazwisko", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (phone.isEmpty()) {
            Toast.makeText(this, "Wprowadź numer telefonu", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!time.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            Toast.makeText(this, "Wprowadź poprawny czas w formacie HH:MM", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
package com.example.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private CartAdapter adapter;
    private TextView totalPriceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        dbHelper = new DatabaseHelper(this);
        totalPriceText = findViewById(R.id.totalPriceText);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        Button clearCartButton = findViewById(R.id.clearCartButton);
        clearCartButton.setOnClickListener(v -> {
            dbHelper.clearCart();
            updateCartView();
            Toast.makeText(this, "Koszyk wyczyszczony", Toast.LENGTH_SHORT).show();
        });

        Button checkoutButton = findViewById(R.id.checkoutButton);
        checkoutButton.setOnClickListener(v -> {
            if (dbHelper.getCartItems().isEmpty()) {
                Toast.makeText(this, "Koszyk jest pusty", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(this, CheckoutActivity.class));
            }
        });

        updateCartView();
    }

    private void updateCartView() {
        List<CartItem> cartItems = dbHelper.getCartItems();
        ListView cartListView = findViewById(R.id.cartListView);

        adapter = new CartAdapter(this, cartItems, dbHelper, this::updateTotalPrice);
        cartListView.setAdapter(adapter);

        if (cartItems.isEmpty()) {
            cartListView.setVisibility(View.GONE);
            findViewById(R.id.emptyCartText).setVisibility(View.VISIBLE);
            totalPriceText.setVisibility(View.GONE);
        } else {
            cartListView.setVisibility(View.VISIBLE);
            findViewById(R.id.emptyCartText).setVisibility(View.GONE);
            totalPriceText.setVisibility(View.VISIBLE);
            updateTotalPrice();
        }
    }

    public void updateTotalPrice() {
        List<CartItem> cartItems = dbHelper.getCartItems();
        double total = 0;

        for (CartItem item : cartItems) {
            try {
                total += Double.parseDouble(item.getPrice()) * item.getQuantity();
            } catch (NumberFormatException e) {
            }
        }

        totalPriceText.setText("Razem: " + String.format("%.2f", total) + " zł");
    }
}
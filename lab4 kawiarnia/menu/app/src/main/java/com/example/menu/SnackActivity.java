package com.example.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SnackActivity extends AppCompatActivity {
    public static final String EXTRA_SNACKID = "snackId";
    private DatabaseHelper dbHelper;
    private Snack snack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack);

        dbHelper = new DatabaseHelper(this);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        Button cartButton = findViewById(R.id.cartButton);
        cartButton.setOnClickListener(v -> {
            Intent intent = new Intent(SnackActivity.this, CartActivity.class);
            startActivity(intent);
        });

        int snackId = getIntent().getIntExtra(EXTRA_SNACKID, 0);
        snack = dbHelper.getAllSnacks().get(snackId);

        ImageView photo = findViewById(R.id.snack_photo);
        photo.setImageResource(snack.getImageResourceId());
        photo.setContentDescription(snack.getName());

        TextView name = findViewById(R.id.snack_name);
        name.setText(snack.getName());

        TextView price = findViewById(R.id.snack_price);
        price.setText("Cena: " + snack.getPrice() + " zł");

        TextView description = findViewById(R.id.snack_description);
        description.setText(snack.getDescription());

        Button addToCartButton = findViewById(R.id.addToCartButton);
        EditText quantityInput = findViewById(R.id.quantityInput);

        addToCartButton.setOnClickListener(v -> {
            try {
                int quantity = Integer.parseInt(quantityInput.getText().toString());
                if (quantity > 0) {
                    dbHelper.addToCart(snack, quantity);
                    Toast.makeText(this, "Dodano do koszyka: " + snack.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Ilość musi być większa niż 0", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Wprowadź poprawną ilość", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
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

public class DrinkActivity extends AppCompatActivity {
    public static final String EXTRA_DRINKID = "drinkId";
    private DatabaseHelper dbHelper;
    private Drink drink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        Button cartButton = findViewById(R.id.cartButton);
        cartButton.setOnClickListener(v -> {
            startActivity(new Intent(this, CartActivity.class));
        });

        dbHelper = new DatabaseHelper(this);
        int drinkId = getIntent().getIntExtra(EXTRA_DRINKID, 0);
        drink = dbHelper.getAllDrinks().get(drinkId);

        TextView name = findViewById(R.id.name);
        name.setText(drink.getName());

        TextView price = findViewById(R.id.price);
        price.setText("Cena: " + drink.getPrice() + " zł");

        TextView description = findViewById(R.id.description);
        description.setText(drink.getDescription());

        ImageView photo = findViewById(R.id.photo);
        photo.setImageResource(drink.getImageResourceId());

        Button addToCartButton = findViewById(R.id.addToCartButton);
        EditText quantityInput = findViewById(R.id.quantityInput);

        addToCartButton.setOnClickListener(v -> {
            try {
                int quantity = Integer.parseInt(quantityInput.getText().toString());
                if (quantity > 0) {
                    dbHelper.addToCart(drink, quantity);
                    Toast.makeText(this, "Dodano do koszyka", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Wprowadź poprawną ilość", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
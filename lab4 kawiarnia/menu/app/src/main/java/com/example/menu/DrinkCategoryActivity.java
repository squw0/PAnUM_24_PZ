package com.example.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class DrinkCategoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        Button cartButton = findViewById(R.id.cartButton);
        cartButton.setOnClickListener(v -> {
            startActivity(new Intent(this, CartActivity.class));
        });

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<Drink> drinks = dbHelper.getAllDrinks();

        ArrayAdapter<Drink> listAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                drinks);

        ListView listDrinks = findViewById(R.id.list_drinks);
        listDrinks.setAdapter(listAdapter);

        listDrinks.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(DrinkCategoryActivity.this, DrinkActivity.class);
            intent.putExtra(DrinkActivity.EXTRA_DRINKID, position);
            startActivity(intent);
        });
    }
}
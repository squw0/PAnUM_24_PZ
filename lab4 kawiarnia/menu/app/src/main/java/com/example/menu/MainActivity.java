package com.example.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        AdapterView.OnItemClickListener itemClickListener = (listView, v, position, id) -> {
            if (position == 0) {
                startActivity(new Intent(MainActivity.this, DrinkCategoryActivity.class));
            } else if (position == 1) {
                startActivity(new Intent(MainActivity.this, SnackCategoryActivity.class));
            } else if (position == 2) {
                startActivity(new Intent(MainActivity.this, LocationActivity.class));
            }
        };

        ListView listView = findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);

        Button cartButton = findViewById(R.id.cartButton);
        cartButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CartActivity.class));
        });
    }
}
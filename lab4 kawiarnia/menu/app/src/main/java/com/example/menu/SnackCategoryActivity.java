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

public class SnackCategoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_category);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        Button cartButton = findViewById(R.id.cartButton);
        cartButton.setOnClickListener(v -> {
            Intent intent = new Intent(SnackCategoryActivity.this, CartActivity.class);
            startActivity(intent);
        });

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<Snack> snacks = dbHelper.getAllSnacks();

        ArrayAdapter<Snack> listAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                snacks);

        ListView listSnacks = findViewById(R.id.list_snacks);
        listSnacks.setAdapter(listAdapter);

        listSnacks.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(SnackCategoryActivity.this, SnackActivity.class);
            intent.putExtra(SnackActivity.EXTRA_SNACKID, position);
            startActivity(intent);
        });
    }
}
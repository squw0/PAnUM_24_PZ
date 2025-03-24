package com.example.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class SnackCategoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_category);

        ArrayAdapter<Snack> listAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Snack.snacks);
        ListView listSnacks = findViewById(R.id.list_snacks);
        listSnacks.setAdapter(listAdapter2);

        listSnacks.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Intent intent = new Intent(SnackCategoryActivity.this, SnackActivity.class);
            intent.putExtra(SnackActivity.EXTRA_SNACKID, (int) id);
            startActivity(intent);
        });
    }
}

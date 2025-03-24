package com.example.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View v, int position, long id) {
                if (position == 0) {
                    startActivity(new Intent(MainActivity.this, DrinkCategoryActivity.class));
                } else if (position == 1) {
                    startActivity(new Intent(MainActivity.this, SnackCategoryActivity.class));
                } else if (position == 2) {
                    startActivity(new Intent(MainActivity.this, LocationActivity.class));
                }
            }
        };

        ListView listView = findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);

        ArrayAdapter<Drink> listAdapter = new ArrayAdapter<Drink>( this,android.R.layout.simple_list_item_1, Drink.drinks);
        ArrayAdapter<Snack> listAdapter2 = new ArrayAdapter<Snack>( this,android.R.layout.simple_list_item_1, Snack.snacks);
                // this – bieżąca aktywność
                //        simple_list_item_1 – wbudowany zasób układu. Informuje adapter, że
                //        poszczególne elementy tablicy mają być wyświetlane w pojedynczych
                //        widokach tekstowych

//        to jest lista na glownym ekranie
//        ListView listDrinks = (ListView) findViewById(R.id.list_drinks);
//        listDrinks.setAdapter(listAdapter);
    }
}

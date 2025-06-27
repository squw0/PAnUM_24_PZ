package com.example.menu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LocationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Location location = dbHelper.getLocation();

        if (location != null) {
            TextView name = findViewById(R.id.location_name);
            name.setText(location.getName());

            TextView address = findViewById(R.id.location_address);
            address.setText("Adres: " + location.getAddress());

            TextView hours = findViewById(R.id.location_hours);
            hours.setText("Godziny otwarcia:\n" + location.getHours());

            ImageView map = findViewById(R.id.map);
            map.setImageResource(location.getImageResourceId());
        }
    }
}
package com.example.menu;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SnackActivity extends AppCompatActivity {
    public static final String EXTRA_SNACKID = "snackId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack);

        int snackId = getIntent().getIntExtra(EXTRA_SNACKID, 0);
        Snack snack = Snack.snacks[snackId];

        TextView name = findViewById(R.id.snack_name);
        name.setText(snack.getName());

        TextView description = findViewById(R.id.snack_description);
        description.setText(snack.getDescription());

        ImageView photo = findViewById(R.id.snack_photo);
        photo.setImageResource(snack.getImageResourceId());
        photo.setContentDescription(snack.getName());
    }
}

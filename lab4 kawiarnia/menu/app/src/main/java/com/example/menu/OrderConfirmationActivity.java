package com.example.menu;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class OrderConfirmationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        TextView confirmationText = findViewById(R.id.confirmationText);
        Button backToMenuButton = findViewById(R.id.backToMenuButton);

        String confirmation = getIntent().getStringExtra("CONFIRMATION_TEXT");
        confirmationText.setText(confirmation);

        backToMenuButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
    }
}
package com.example.przelicznik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButton(View view) {
        Intent intent = new Intent(this, systemyLiczbowe.class);
        startActivity(intent);
    }

    public void jednostkiMiary(View view) {
        Intent intent = new Intent(this, jednostki.class);
        startActivity(intent);
    }

    public void waluty(View view) {
        Intent intent = new Intent(this, waluty.class);
        startActivity(intent);
    }
}
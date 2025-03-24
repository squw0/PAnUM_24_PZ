package com.example.tajmery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivitykolejne extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.tojestactivityinne);
    }

    public void goToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void startMinutnik1(View view) {

    }

    public void stopMinutnik1(View view) {

    }

    public void resetMinutnik1(View view) {

    }

    public void startMinutnik2(View view) {

    }

    public void stopMinutnik2(View view) {

    }

    public void resetMinutnik2(View view) {

    }
}

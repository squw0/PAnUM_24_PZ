package com.example.tajmery;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView stoper1, stoper2;
    private long startTime1 = 0, startTime2 = 0;
    private boolean running1 = false, running2 = false;
    private Handler handler = new Handler();

    private Runnable updateStoper1 = new Runnable() {
        @Override
        public void run() {
            if (running1) {
                long elapsed = System.currentTimeMillis() - startTime1;
                stoper1.setText(formatTime(elapsed));
                handler.postDelayed(this, 10);
            }
        }
    };

    private Runnable updateStoper2 = new Runnable() {
        @Override
        public void run() {
            if (running2) {
                long elapsed = System.currentTimeMillis() - startTime2;
                stoper2.setText(formatTime(elapsed));
                handler.postDelayed(this, 10);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        stoper1 = findViewById(R.id.stoper1);
        stoper2 = findViewById(R.id.stoper2);
    }

    public void startStoper1(View view) {
        if (!running1) {
            startTime1 = System.currentTimeMillis() - getTimeFromText(stoper1);
            running1 = true;
            handler.post(updateStoper1);
        }
    }

    public void stopStoper1(View view) {
        running1 = false;
    }

    public void resetStoper1(View view) {
        running1 = false;
        stoper1.setText("00:00:00.00");
    }

    public void startStoper2(View view) {
        if (!running2) {
            startTime2 = System.currentTimeMillis() - getTimeFromText(stoper2);
            running2 = true;
            handler.post(updateStoper2);
        }
    }

    public void stopStoper2(View view) {
        running2 = false;
    }

    public void resetStoper2(View view) {
        running2 = false;
        stoper2.setText("00:00:00.00");
    }

    private String formatTime(long millis) {
        long minutes = (millis / 60000) % 60;
        long seconds = (millis / 1000) % 60;
        long millisecs = (millis % 1000) / 10;
        return String.format("%02d:%02d:%02d.%02d", 0, minutes, seconds, millisecs);
    }

    private long getTimeFromText(TextView textView) {
        String[] parts = textView.getText().toString().split(":|\\.");
        return Integer.parseInt(parts[1]) * 60000 + Integer.parseInt(parts[2]) * 1000 + Integer.parseInt(parts[3]) * 10;
    }

    public void onClickButton(View view) {
        Intent intent = new Intent(this, MainActivitykolejne.class);
        startActivity(intent);
    }
}

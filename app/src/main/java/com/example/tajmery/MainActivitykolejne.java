package com.example.tajmery;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivitykolejne extends AppCompatActivity {

    private TextView minutnik1, minutnik2;
    private CountDownTimer timer1, timer2;
    private boolean running1 = false, running2 = false;
    private long timeLeft1 = 60000, timeLeft2 = 60000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.tojestactivityinne);
        // setContentView(R.layout.activity_main_kolejne);
        minutnik1 = findViewById(R.id.minutnik1);
        minutnik2 = findViewById(R.id.minutnik2);

        updateTimerText(minutnik1, timeLeft1);
        updateTimerText(minutnik2, timeLeft2);
    }

    public void startMinutnik1(View view) {
        if (!running1) {
            timer1 = new CountDownTimer(timeLeft1, 10) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeLeft1 = millisUntilFinished;
                    updateTimerText(minutnik1, timeLeft1);
                }

                @Override
                public void onFinish() {
                    running1 = false;
                }
            }.start();
            running1 = true;
        }
    }

    public void stopMinutnik1(View view) {
        if (running1) {
            timer1.cancel();
            running1 = false;
        }
    }

    public void resetMinutnik1(View view) {
        if (running1) {
            timer1.cancel();
            running1 = false;
        }
        timeLeft1 = 60000;
        updateTimerText(minutnik1, timeLeft1);
    }

    public void startMinutnik2(View view) {
        if (!running2) {
            timer2 = new CountDownTimer(timeLeft2, 10) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeLeft2 = millisUntilFinished;
                    updateTimerText(minutnik2, timeLeft2);
                }

                @Override
                public void onFinish() {
                    running2 = false;
                }
            }.start();
            running2 = true;
        }
    }

    public void stopMinutnik2(View view) {
        if (running2) {
            timer2.cancel();
            running2 = false;
        }
    }

    public void resetMinutnik2(View view) {
        if (running2) {
            timer2.cancel();
            running2 = false;
        }
        timeLeft2 = 60000;
        updateTimerText(minutnik2, timeLeft2);
    }

    private void updateTimerText(TextView textView, long millis) {
        long minutes = (millis / 60000) % 60;
        long seconds = (millis / 1000) % 60;
        long millisecs = (millis % 1000) / 10;
        textView.setText(String.format("%02d:%02d:%02d.%02d", 0, minutes, seconds, millisecs));
    }

    public void goToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

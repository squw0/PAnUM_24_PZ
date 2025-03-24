package com.example.biegstatystyki;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText paceInput;
    private EditText distanceInput;
    private TextView speedOutput;
    private TextView marathonTimeOutput;
    private TextView halfMarathonTimeOutput;
    private TextView customDistanceTimeOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paceInput = findViewById(R.id.tempoInput);
        distanceInput = findViewById(R.id.dystansInput);
        speedOutput = findViewById(R.id.predkoscOutput);
        marathonTimeOutput = findViewById(R.id.maratonOutput);
        halfMarathonTimeOutput = findViewById(R.id.polmaratonOutput);
        customDistanceTimeOutput = findViewById(R.id.dystansOutput);

        Button calculateButton = findViewById(R.id.obliczButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateStats();
            }
        });
    }

    private void calculateStats() {
        String paceStr = paceInput.getText().toString().trim();
        String distanceStr = distanceInput.getText().toString().trim();

        if (paceStr.isEmpty() || distanceStr.isEmpty()) {
            Toast.makeText(this, "Wprowadź poprawne wartości!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double pace = Double.parseDouble(paceStr);
            double distance = Double.parseDouble(distanceStr);

            // Sprawdzenie, czy wartości są dodatnie
            if (pace <= 0 || distance <= 0) {
                Toast.makeText(this, "Tempo i dystans muszą być większe od zera!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Oblicz prędkość
            double speed = Logika.obliczPredkosc(pace);
            speedOutput.setText(String.format("Prędkość: %.2f km/h", speed));

            // Oblicz czas na maraton
            String marathonTime = Logika.obliczCzasMaraton(pace);
            marathonTimeOutput.setText("Czas na maraton: " + marathonTime);

            // Oblicz czas na półmaraton
            String halfMarathonTime = Logika.obliczCzasPolmaraton(pace);
            halfMarathonTimeOutput.setText("Czas na półmaraton: " + halfMarathonTime);

            // Oblicz czas na dowolny dystans
            String customDistanceTime = Logika.obliczCzas(pace, distance);
            customDistanceTimeOutput.setText("Czas na dystans: " + customDistanceTime);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Wprowadź liczby w formacie 00.00!", Toast.LENGTH_SHORT).show();
        } catch (IllegalArgumentException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}





// tu brakuje pdpk 2 coś tam że tempo dodaje i mu oblicza dystans albo na odwrót
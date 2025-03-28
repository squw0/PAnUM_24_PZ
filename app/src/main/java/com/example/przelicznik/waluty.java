package com.example.przelicznik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class waluty extends AppCompatActivity {

    private EditText inputNumber;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waluty);

        inputNumber = findViewById(R.id.inputNumber);
        resultText = findViewById(R.id.resultText);
    }

    public void goToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void convertCurrency(View view) {
        String input = inputNumber.getText().toString();

        if (input.isEmpty()) {
            Toast.makeText(this, "Wprowadź wartość!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double amount = Double.parseDouble(input);
            double convertedValue = amount * 4.5; // Przykładowy przelicznik np. PLN na EUR
            resultText.setText("Wynik: " + convertedValue + " EUR");
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Nieprawidłowa liczba!", Toast.LENGTH_SHORT).show();
        }
    }
}

package com.example.przelicznik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class systemyLiczbowe extends AppCompatActivity {

    private EditText inputNumber;
    private Spinner fromSystemSpinner, toSystemSpinner;
    private TextView resultText;
    private Button convertButton, buttonBack;

    private final String[] systems = {"Dziesiętny", "Dwójkowy", "Czwórkowy", "Ósemkowy", "Szesnastkowy"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systemy_liczbowe);

        inputNumber = findViewById(R.id.inputNumber);
        fromSystemSpinner = findViewById(R.id.fromSystemSpinner);
        toSystemSpinner = findViewById(R.id.toSystemSpinner);
        resultText = findViewById(R.id.resultText);
        convertButton = findViewById(R.id.convertButton);
        buttonBack = findViewById(R.id.buttonBack);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, systems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSystemSpinner.setAdapter(adapter);
        toSystemSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(v -> convertNumber());
        buttonBack.setOnClickListener(v -> goToMain());
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void convertNumber() {
        String input = inputNumber.getText().toString().trim();
        if (input.isEmpty()) {
            Toast.makeText(this, "Wprowadź liczbę!", Toast.LENGTH_SHORT).show();
            return;
        }

        String fromSystem = fromSystemSpinner.getSelectedItem().toString();
        String toSystem = toSystemSpinner.getSelectedItem().toString();

        try {
            String result = convert(input, fromSystem, toSystem);
            resultText.setText("Wynik: " + result);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Nieprawidłowa liczba dla systemu " + fromSystem, Toast.LENGTH_SHORT).show();
        }
    }

    private String convert(String number, String fromSystem, String toSystem) {
        int decimalValue;

        switch (fromSystem) {
            case "Dziesiętny":
                decimalValue = Integer.parseInt(number);
                break;
            case "Dwójkowy":
                decimalValue = Integer.parseInt(number, 2);
                break;
            case "Czwórkowy":
                decimalValue = Integer.parseInt(number, 4);
                break;
            case "Ósemkowy":
                decimalValue = Integer.parseInt(number, 8);
                break;
            case "Szesnastkowy":
                decimalValue = Integer.parseInt(number, 16);
                break;
            default:
                throw new NumberFormatException();
        }

        switch (toSystem) {
            case "Dziesiętny":
                return String.valueOf(decimalValue);
            case "Dwójkowy":
                return Integer.toBinaryString(decimalValue);
            case "Czwórkowy":
                return Integer.toString(decimalValue, 4);
            case "Ósemkowy":
                return Integer.toOctalString(decimalValue);
            case "Szesnastkowy":
                return Integer.toHexString(decimalValue).toUpperCase();
            default:
                return "Błąd konwersji";
        }
    }
}
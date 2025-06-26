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
import java.util.HashMap;
import java.util.Map;

public class waluty extends AppCompatActivity {

    private EditText inputNumber;
    private Spinner fromCurrencySpinner, toCurrencySpinner;
    private TextView resultText;
    private Button convertButton, buttonBack;

    private final String[] currencies = {"PLN (złoty)", "USD (dolar amerykański)", "EUR (euro)",
            "GBP (funt brytyjski)", "JPY (jen japoński)"};

    private final Map<String, Double> exchangeRates = new HashMap<String, Double>() {{
        put("PLN (złoty)", 1.0);
        put("USD (dolar amerykański)", 3.96);
        put("EUR (euro)", 4.45);
        put("GBP (funt brytyjski)", 5.10);
        put("JPY (jen japoński)", 0.026);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waluty);

        inputNumber = findViewById(R.id.inputNumber);
        fromCurrencySpinner = findViewById(R.id.fromCurrencySpinner);
        toCurrencySpinner = findViewById(R.id.toCurrencySpinner);
        resultText = findViewById(R.id.resultText);
        convertButton = findViewById(R.id.convertButton);
        buttonBack = findViewById(R.id.buttonBack);

        // Inicjalizacja spinnerów
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromCurrencySpinner.setAdapter(adapter);
        toCurrencySpinner.setAdapter(adapter);

        convertButton.setOnClickListener(v -> convertCurrency());
        buttonBack.setOnClickListener(v -> goToMain());
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void convertCurrency() {
        String input = inputNumber.getText().toString().trim();
        if (input.isEmpty()) {
            Toast.makeText(this, "Wprowadź kwotę!", Toast.LENGTH_SHORT).show();
            return;
        }

        String fromCurrency = fromCurrencySpinner.getSelectedItem().toString();
        String toCurrency = toCurrencySpinner.getSelectedItem().toString();

        try {
            double amount = Double.parseDouble(input);
            double result = convert(amount, fromCurrency, toCurrency);

            String resultString = String.format("%.2f %s = %.2f %s",
                    amount, fromCurrency, result, toCurrency);
            resultText.setText(resultString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Nieprawidłowa kwota!", Toast.LENGTH_SHORT).show();
        }
    }

    private double convert(double amount, String fromCurrency, String toCurrency) {
        double amountInPLN = amount * exchangeRates.get(fromCurrency);
        return amountInPLN / exchangeRates.get(toCurrency);
    }
}
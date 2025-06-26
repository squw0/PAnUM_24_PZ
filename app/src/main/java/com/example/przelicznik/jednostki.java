package com.example.przelicznik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class jednostki extends AppCompatActivity {

    private EditText inputNumber;
    private Spinner fromUnitSpinner, toUnitSpinner;
    private TextView resultText;
    private Button convertButton, buttonBack;
    private RadioGroup unitTypeGroup;
    private RadioButton lengthRadio, areaRadio;

    private final String[] lengthUnits = {"mm", "cm", "m", "km", "cale", "stopy", "yardy"};
    private final double[] lengthFactors = {1, 10, 1000, 1000000, 25.4, 304.8, 914.4};

    private final String[] areaUnits = {"mm²", "cm²", "m²", "km²", "ary", "hektary"};
    private final double[] areaFactors = {1, 100, 1000000, 1000000000000L, 100000000, 10000000000L};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jednostki);

        inputNumber = findViewById(R.id.inputNumber);
        fromUnitSpinner = findViewById(R.id.fromUnitSpinner);
        toUnitSpinner = findViewById(R.id.toUnitSpinner);
        resultText = findViewById(R.id.resultText);
        convertButton = findViewById(R.id.convertButton);
        buttonBack = findViewById(R.id.buttonBack);
        unitTypeGroup = findViewById(R.id.unitTypeGroup);
        lengthRadio = findViewById(R.id.lengthRadio);
        areaRadio = findViewById(R.id.areaRadio);

        updateSpinners(true);

        unitTypeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.lengthRadio) {
                updateSpinners(true);
            } else {
                updateSpinners(false);
            }
        });

        convertButton.setOnClickListener(v -> convertUnits());
        buttonBack.setOnClickListener(v -> goToMain());
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void updateSpinners(boolean isLength) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                isLength ? lengthUnits : areaUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);
    }

    private void convertUnits() {
        String input = inputNumber.getText().toString().trim();
        if (input.isEmpty()) {
            Toast.makeText(this, "Wprowadź wartość!", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isLength = lengthRadio.isChecked();
        String fromUnit = fromUnitSpinner.getSelectedItem().toString();
        String toUnit = toUnitSpinner.getSelectedItem().toString();

        try {
            double amount = Double.parseDouble(input);
            double result = convert(amount, fromUnit, toUnit, isLength);

            String resultString = String.format("%.2f %s = %.2f %s",
                    amount, fromUnit, result, toUnit);
            resultText.setText(resultString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Nieprawidłowa wartość!", Toast.LENGTH_SHORT).show();
        }
    }

    private double convert(double value, String fromUnit, String toUnit, boolean isLength) {
        String[] units = isLength ? lengthUnits : areaUnits;
        double[] factors = isLength ? lengthFactors : areaFactors;

        int fromIndex = -1, toIndex = -1;
        for (int i = 0; i < units.length; i++) {
            if (units[i].equals(fromUnit)) fromIndex = i;
            if (units[i].equals(toUnit)) toIndex = i;
        }

        if (fromIndex == -1 || toIndex == -1) {
            return 0;
        }

        double valueInBase = value * factors[fromIndex];
        return valueInBase / factors[toIndex];
    }
}
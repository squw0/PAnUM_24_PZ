package com.example.lalala;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void OnClickNumericButton(View view) {
        TextView textViewArabic = findViewById(R.id.ScreenArabic);
        TextView textViewRoman = findViewById(R.id.ScreenRoman);
        String text = textViewArabic.getText().toString();

        if (view.getId() == R.id.Button_9)
            text += "9";
        if (view.getId() == R.id.Button_8)
            text += "8";
        if (view.getId() == R.id.Button_7)
            text += "7";
        if (view.getId() == R.id.Button_6)
            text += "6";
        if (view.getId() == R.id.Button_5)
            text += "5";
        if (view.getId() == R.id.Button_4)
            text += "4";
        if (view.getId() == R.id.Button_3)
            text += "3";
        if (view.getId() == R.id.Button_2)
            text += "2";
        if (view.getId() == R.id.Button_1)
            text += "1";

        if (view.getId() == R.id.Button_CE)
            text = "";

        if (view.getId() == R.id.Button_0)
            text += "0";

        if (view.getId() == R.id.Button_DEL && text.length() > 0) {
            text = text.substring(0, text.length() - 1);
        }

        textViewArabic.setText(text);

        if (!text.isEmpty()) {
            Converter converter = new Converter(text, this);
            String roman = converter.arabicToR();
            textViewRoman.setText(roman != null ? roman : "Błąd");
        } else {
            textViewRoman.setText("");
        }
    }

    public void OnClickRomanButton(View view) {
        TextView textViewRoman = findViewById(R.id.ScreenRoman);
        TextView textViewArabic = findViewById(R.id.ScreenArabic);
        String text = textViewRoman.getText().toString();

        if (view.getId() == R.id.Button_I)
            text += "I";
        if (view.getId() == R.id.Button_V)
            text += "V";
        if (view.getId() == R.id.Button_X)
            text += "X";
        if (view.getId() == R.id.Button_L)
            text += "L";
        if (view.getId() == R.id.Button_C)
            text += "C";
        if (view.getId() == R.id.Button_D)
            text += "D";
        if (view.getId() == R.id.Button_M)
            text += "M";

        textViewRoman.setText(text);


        if (!text.isEmpty()) {
            Converter converter = new Converter(text, this);
            int arabic = converter.romanToArabic();
            textViewArabic.setText(arabic != -1 ? String.valueOf(arabic) : "Błąd");
        } else {
            textViewArabic.setText("");
        }



    }
}

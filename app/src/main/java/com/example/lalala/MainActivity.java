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
        String text = textViewArabic.getText().toString();

        if (view.getId() == R.id.Button_9)
            text = text + "9";
        if (view.getId() == R.id.Button_8)
            text = text + "8";
        if (view.getId() == R.id.Button_7)
            text = text + "7";
        if (view.getId() == R.id.Button_6)
            text = text + "6";
        if (view.getId() == R.id.Button_5)
            text = text + "5";
        if (view.getId() == R.id.Button_4)
            text = text + "4";
        if (view.getId() == R.id.Button_3)
            text = text + "3";
        if (view.getId() == R.id.Button_2)
            text = text + "2";
        if (view.getId() == R.id.Button_1)
            text = text + "1";

        // to musi kasować wszystko
        if (view.getId() == R.id.Button_CE)
            text = "";

        if (view.getId() == R.id.Button_0)
            text = text + "0";

        // to powinno kasować jedno
        if (view.getId() == R.id.Button_DEL) {
            if (text.length() > 0) {
                text = text.substring(0, text.length() - 1);
            }
        }
        textViewArabic.setText(text1);
    }
}
public void OnClickRomanButton(View view) {
    TextView textViewRoman = findViewById(R.id.ScreenRoman);
    String text = textViewRoman.getText().toString();

        if (view.getId() == R.id.Button_I)
            text = text + "I";
        if (view.getId() == R.id.Button_V)
            text = text + "V";
        if (view.getId() == R.id.Button_X)
            text = text + "X";
        if (view.getId() == R.id.Button_L)
            text = text + "L";
        if (view.getId() == R.id.Button_C)
            text = text + "C";
        if (view.getId() == R.id.Button_D)
            text = text + "D";
        if (view.getId() == R.id.Button_M)
            text = text + "M";

        textViewRoman.setText(text);
    }
}
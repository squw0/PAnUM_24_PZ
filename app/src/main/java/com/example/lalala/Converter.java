package com.example.lalala;
import android.content.Context;
import android.widget.Toast;
import java.util.HashMap;

public class Converter {
    private final String numberStr;
    private final Context context;
    int number;

    public Converter(String numberStr, Context context) {
        this.numberStr = numberStr;
        this.context = context;
    }

    public String arabicToR() {
        try {
            number = Integer.parseInt(numberStr);
        } catch (NumberFormatException e) {
            Toast.makeText(context, "Nie prawidlowa liczba arabska", Toast.LENGTH_SHORT).show();
            return null;
        }

        if (number <= 0 || number > 3999) {
            Toast.makeText(context, "Liczba musi być w zakresie 1-3999", Toast.LENGTH_SHORT).show();
            return null;
        }

        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder romanNumber = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            int count = number / values[i];
            number %= values[i];

            while (count-- > 0) {
                romanNumber.append(romans[i]);
            }
        }

        return romanNumber.toString();
    }

    public int romanToArabic() {
        HashMap<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int total = 0;
        int prevValue = 0;

        for (int i = numberStr.length() - 1; i >= 0; i--) {
            int currentValue = romanMap.get(numberStr.charAt(i));

            if (currentValue == 0) {
                Toast.makeText(context, "Nie prawidlowy znak rzymski", Toast.LENGTH_SHORT).show();
                return -1;
            }

            if (currentValue < prevValue) {
                total -= currentValue;
            } else {
                total += currentValue;
            }
            prevValue = currentValue;
        }

        if (!isValidRoman(numberStr)) {
            Toast.makeText(context, "Nieprawidlowa liczba rzymska", Toast.LENGTH_SHORT).show();
            return -1;
        }
        return total;
    }

    private boolean isValidRoman(String roman) {
        return roman.matches("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
    }

}
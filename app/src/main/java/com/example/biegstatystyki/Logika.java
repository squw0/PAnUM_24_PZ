package com.example.biegstatystyki;

public class Logika {

    public static double obliczPredkosc(double tempo) {
        return 60.0 / tempo;
    }

    public static String obliczCzasMaraton(double tempo) {
        double czas = tempo * 42.195; // 42.195 km
        return formatCzas(czas);
    }

    public static String obliczCzasPolmaraton(double tempo) {
        double czas = tempo * 21.0975; // 21.0975 km
        return formatCzas(czas);
    }

    public static String obliczCzas(double tempo, double dystans) {
        double czas = tempo * dystans;
        return formatCzas(czas);
    }

    private static String formatCzas(double czas) {
        int godziny = (int) (czas / 60);
        int minuty = (int) (czas % 60);
        return String.format("%02d:%02d", godziny, minuty);
    }
}

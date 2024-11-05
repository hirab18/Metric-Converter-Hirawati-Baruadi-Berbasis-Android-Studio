package com.example.metricconverter2024;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputValue;
    private Spinner unitSpinner;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Menghubungkan komponen UI
        inputValue = findViewById(R.id.inputValue);
        unitSpinner = findViewById(R.id.unitSpinner);
        resultTextView = findViewById(R.id.resultTextView);
        Button convertButton = findViewById(R.id.convertButton);

        // Tombol untuk memilih besaran
        findViewById(R.id.lengthButton).setOnClickListener(v -> setupSpinner(R.array.length_units));
        findViewById(R.id.massButton).setOnClickListener(v -> setupSpinner(R.array.mass_units));
        findViewById(R.id.temperatureButton).setOnClickListener(v -> setupSpinner(R.array.temperature_units));

        // Tombol Konversi
        convertButton.setOnClickListener(v -> {
            String input = inputValue.getText().toString();
            if (input.isEmpty()) {
                Toast.makeText(this, "Masukkan nilai terlebih dahulu!", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                double value = Double.parseDouble(input);
                String selectedUnit = unitSpinner.getSelectedItem().toString();
                double result = convertValue(value, selectedUnit);
                String resultUnit = getResultUnit(selectedUnit);
                resultTextView.setText(String.format("Hasil: %.2f %s", result, resultUnit));
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Masukkan angka yang valid!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Mengatur spinner dengan satuan yang sesuai
    private void setupSpinner(int arrayResId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                arrayResId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(adapter);
    }

    private double convertValue(double value, String selectedUnit) {
        switch (selectedUnit) {
            // Konversi untuk panjang
            case "Meter":
                return value * 100; // Konversi ke sentimeter
            case "Sentimeter":
                return value / 100; // Konversi ke meter
            case "Milimeter":
                return value / 1000; // Konversi ke meter
            case "Kilometer":
                return value * 1000; // Konversi ke meter

            // Konversi untuk massa
            case "Kilogram":
                return value * 1000; // Konversi ke gram
            case "Gram":
                return value / 1000; // Konversi ke kilogram
            case "Miligram":
                return value / 1000000; // Konversi ke kilogram
            case "Ton":
                return value * 1000; // Konversi ke kilogram

            // Konversi untuk suhu
            case "Celsius":
                return (value * 9 / 5) + 32; // Konversi Celsius ke Fahrenheit
            case "Fahrenheit":
                return (value - 32) * 5 / 9; // Konversi Fahrenheit ke Celsius

            // Konversi untuk waktu
            case "Detik":
                return value / 60; // Konversi ke menit
            case "Menit":
                return value * 60; // Konversi ke detik
            case "Jam":
                return value * 3600; // Konversi ke detik

            // Konversi untuk arus listrik
            case "Ampere":
                return value * 1000; // Konversi ke miliampere
            case "Miliampere":
                return value / 1000; // Konversi ke ampere

            // Konversi untuk intensitas cahaya
            case "Lumen":
                return value * 1000; // Konversi ke mililux
            case "Mililux":
                return value / 1000; // Konversi ke lux

            // Konversi untuk jumlah zat
            case "Mol":
                return value * 1000; // Konversi ke milimol
            case "Milimol":
                return value / 1000; // Konversi ke mol

            default:
                return value; // Mengembalikan nilai asli jika konversi tidak dikenali
        }
    }

    private String getResultUnit(String selectedUnit) {
        switch (selectedUnit) {
            // Singkatan untuk panjang
            case "Meter":
                return "cm"; // Sentimeter
            case "Sentimeter":
                return "m"; // Meter
            case "Milimeter":
                return "m"; // Meter
            case "Kilometer":
                return "m"; // Meter

            // Singkatan untuk massa
            case "Kilogram":
                return "g"; // Gram
            case "Gram":
                return "kg"; // Kilogram
            case "Miligram":
                return "kg"; // Kilogram
            case "Ton":
                return "kg"; // Kilogram

            // Singkatan untuk suhu
            case "Celsius":
                return "°F"; // Fahrenheit
            case "Fahrenheit":
                return "°C"; // Celsius

            // Singkatan untuk waktu
            case "Detik":
                return "menit"; // Menit
            case "Menit":
                return "detik"; // Detik
            case "Jam":
                return "detik"; // Detik

            // Singkatan untuk arus listrik
            case "Ampere":
                return "mA"; // Miliampere
            case "Miliampere":
                return "A"; // Ampere

            // Singkatan untuk intensitas cahaya
            case "Lumen":
                return "mililux"; // Mililux
            case "Mililux":
                return "lux"; // Lux

            // Singkatan untuk jumlah zat
            case "Mol":
                return "mMol"; // Milimol
            case "Milimol":
                return "mol"; // Mol

            default:
                return ""; // Tidak ada singkatan
        }
    }
}

package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private ListView listeslesenler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listeslesenler = findViewById(R.id.listeslesenler);

        // MainActivity'den gelen plaka kodları listesini al
        ArrayList<Integer> plakaKodlari = getIntent().getIntegerArrayListExtra("plakaKodlari");

        // Plaka kodlarını şehir isimlerine dönüştür
        ArrayList<String> sehirler = new ArrayList<>();
        for (int plakaKodu : plakaKodlari) {
            sehirler.add(getSehirAdiFromPlaka(plakaKodu));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sehirler);
        listeslesenler.setAdapter(adapter);
    }

    // Plaka kodundan şehir adını döndüren yardımcı metot
    private String getSehirAdiFromPlaka(int plakaKodu) {
        switch (plakaKodu) {
            case 1:
                return "Adana";
            case 34:
                return "Ankara";
            case 35:
                return "İzmir";
            case 41:
                return "Kocaeli";
            case 69:
                return "Bayburt";
            case 25:
                return "Erzurum";
            case 61:
                return "Trabzon";
            case 53:
                return "Rize";
            case 77:
                return "Yalova";
            default:
                return "";
        }
    }
}

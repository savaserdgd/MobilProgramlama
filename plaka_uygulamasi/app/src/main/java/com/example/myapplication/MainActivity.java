package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ListView listsehirler;
    private ListView listrastgelesayilar;
    private Button buton;
    private Button buton2; // Yeni buton

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listsehirler = findViewById(R.id.listsehirler);
        listrastgelesayilar = findViewById(R.id.listrastgelesayilar);
        buton = findViewById(R.id.buton);
        buton2 = findViewById(R.id.buton2); // Buton2'nin tanımlanması

        final String[] sehirler = {"Adana", "Ankara", "İstanbul", "İzmir", "Kocaeli", "Bayburt", "Erzurum", "Trabzon", "Rize", "Yalova"};

        // Şehirlerin plaka kodlarını tutacak bir eşleme (mapping)
        final HashMap<String, Integer> plakaKodlari = new HashMap<>();
        plakaKodlari.put("Adana", 1);
        plakaKodlari.put("Ankara", 34);
        plakaKodlari.put("İzmir", 35);
        plakaKodlari.put("Kocaeli", 41);
        plakaKodlari.put("Bayburt", 69);
        plakaKodlari.put("Erzurum", 25);
        plakaKodlari.put("Trabzon", 61);
        plakaKodlari.put("Rize", 53);
        plakaKodlari.put("Yalova", 77);

        final ArrayAdapter<String> sehirlerveri = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sehirler);
        listsehirler.setAdapter(sehirlerveri);

        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> plakaKodlariUygun = new ArrayList<>();
                ArrayList<String> rastgeleSayilar = new ArrayList<>();
                Random random = new Random();

                // 1 ile 81 arasında rastgele 10 sayı üret
                for (int i = 0; i < 10; i++) {
                    int rastgeleSayi = random.nextInt(81) + 1; // 1 ile 81 arasında rastgele sayı üret
                    rastgeleSayilar.add(String.valueOf(rastgeleSayi));
                }

                // Üretilen rastgele sayıların içinde şehir plaka kodları var mı kontrol et
                for (String sehir : sehirler) {
                    Integer plakaKodu = plakaKodlari.get(sehir);
                    if (plakaKodu != null && rastgeleSayilar.contains(String.valueOf(plakaKodu.intValue()))) {
                        plakaKodlariUygun.add(plakaKodu);
                    }
                }

                // MainActivity2'ye plaka kodları listesini gönder
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putIntegerArrayListExtra("plakaKodlari", plakaKodlariUygun);
                startActivity(intent);
            }
        });

        // Buton2'ye tıklanma olayı ekleme
        buton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}

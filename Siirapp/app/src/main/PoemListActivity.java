package com.example.siirapp;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PoemListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_list);

        LinearLayout layout = findViewById(R.id.poem_list_layout);

        String[][] siirler = {
                {"Nazım Hikmet", "Kız Çocuğu", "Kuvayi Milliye", "Sevdalı Bulut"},
                {"Orhan Veli", "Anlatamıyorum", "İstanbul'u Dinliyorum", "Kitabe-i Seng-i Mezar"},
                {"Cemal Süreya", "Üvercinka", "Sizin Hiç Babanız Öldü mü", "Sevda Sözleri"},
                {"Attila İlhan", "Ben Sana Mecburum", "Aysel Git Başımdan", "Yağmur Kaçağı"},
                {"Can Yücel", "Her Şey Sende Gizli", "Hayatta Ben En Çok Babamı Sevdim", "Bağlanmayacaksın"}
        };

        for (String[] s : siirler) {
            TextView poet = new TextView(this);
            poet.setText(s[0]);
            poet.setTextSize(20f);
            poet.setPadding(0, 20, 0, 10);
            layout.addView(poet);

            for (int i = 1; i < s.length; i++) {
                TextView poem = new TextView(this);
                poem.setText("- " + s[i]);
                poem.setPadding(40, 5, 0, 5);
                layout.addView(poem);
            }
        }
    }
}
